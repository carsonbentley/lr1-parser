/*
 * Look for TODO comments in this file for suggestions on how to implement
 * your parser.
 */
package parser;

import java.io.IOException;
import java.util.*;

import lexer.ExprLexer;
import lexer.ParenLexer;
import lexer.SimpleLexer;
import lexer.TinyLexer;
import org.antlr.v4.runtime.*;

/**
 *
 */
public class Parser {

  final Grammar grammar;

  /**
   * All states in the parser.
   */
  private final States states;

  /**
   * Action table for bottom-up parsing. Accessed as
   * actionTable.get(state).get(terminal). You may replace
   * the Integer with a State class if you choose.
   */
  private HashMap<Integer, HashMap<String, Action>> actionTable;
  /**
   * Goto table for bottom-up parsing. Accessed as gotoTable.get(state).get(nonterminal).
   * You may replace the Integers with State classes if you choose.
   */
  private  HashMap<Integer, HashMap<String, Integer>> gotoTable;

  public Parser(String grammarFilename) throws IOException {
    actionTable = new HashMap<>();
    gotoTable = new HashMap<>();
    grammar = new Grammar(grammarFilename);
    states = getStates();
  }

  // Function creating the set of all states and transitions in a grammar
  public States getStates() {
    States states = new States();
    Rule startRule = null;
    for (Rule rule : grammar.rules) {
      if (rule.getLhs().equals(grammar.startSymbol)) {
        startRule = rule;
        break;
      }
    }
    if (startRule == null) {
      throw new RuntimeException("Start rule not found in grammar");
    }
    Item startItem = new Item(startRule, 0, Util.EOF);
    State closure = computeClosure(startItem, grammar);
    int initialStateId = states.addState(closure);
    Queue<Integer> workList = new LinkedList<>();
    workList.add(initialStateId);
    while (!workList.isEmpty()) {
      int stateId = workList.poll();
      State state = states.getState(stateId);
      Set<String> symbols = new HashSet<>();
      for (Item item : state.getItemList()) {
        if(item.getNextSymbol() != null){
          String nextSymbol = item.getNextSymbol();
          if (nextSymbol != null && !nextSymbol.isEmpty()) {
            symbols.add(nextSymbol);
          }
        }
      }
      for (String symbol : symbols) {
        State gotoState = GOTO(state, symbol, grammar);
        if (gotoState != null && !gotoState.getItems().isEmpty()) {
          int existingId = states.findState(gotoState);
          if (existingId == -1) {
            int newStateId = states.addState(gotoState);
            states.addTransition(stateId, symbol, newStateId);
            workList.add(newStateId);
          } else {
            states.addTransition(stateId, symbol, existingId);
          }
        }
      }
    }
    return states;
  }
  /**
   * Creates a state containing all possible items out of Item I,
   * using rules in grammar
   * @param I: Initial closure item
   * @param grammar: grammar for closure
   * @return
   * State with set of all possible items out of Item I
   *
   */
  static public State computeClosure(Item I, Grammar grammar) {
    State closure = new State();
    closure.addItem(I);
    Queue<Item> queue = new LinkedList<>();
    queue.add(I);
    while (!queue.isEmpty()) {
      Item item = queue.poll();
      String nextSymbol = item.getNextSymbol();
      if(nextSymbol != null){
        if (nextSymbol.isEmpty() || grammar.isTerminal(nextSymbol)) {
          continue;
        }
        for (Rule rule : grammar.rules) {
          if (rule.getLhs().equals(nextSymbol)) {
            Set<String> lookaheads = new HashSet<>();
            List<String> beta = new ArrayList<>();
            Rule itemRule = item.getRule();
            int dotPos = item.getDot();
            if (dotPos + 1 < itemRule.getRhs().size()) {
              beta = itemRule.getRhs().subList(dotPos + 1, itemRule.getRhs().size());
            }
            HashMap<String, HashSet<String>> firstSets = Util.computeFirst(
                    grammar.symbols, grammar.terminals, grammar.rules);
            Set<String> firstBeta = new HashSet<>();
            boolean allDeriveEmpty = true;
            for (String symbol : beta) {
              HashSet<String> firstOfSymbol = firstSets.get(symbol);
              for (String term : firstOfSymbol) {
                if (!term.equals("")) {
                  firstBeta.add(term);
                }
              }
              if (!firstOfSymbol.contains("")) {
                allDeriveEmpty = false;
                break;
              }
            }
            if (allDeriveEmpty || beta.isEmpty()) {
              lookaheads.add(item.getLookahead());
            } else {
              lookaheads.addAll(firstBeta);
            }
            for (String lookahead : lookaheads) {
              Item newItem = new Item(rule, 0, lookahead);
              if (closure.addItem(newItem)) {
                queue.add(newItem);
              }
            }
          }
        }
      }
    }
    return closure;
  }

  /**
   * Creates and returns a state containing all items in the goto[state, x] set
   */
  static public State GOTO(State state, String X, Grammar grammar) {
    State result = new State();

    for (Item item : state.getItemList()) {
      String nextSymbol = item.getNextSymbol();
      if(nextSymbol != null){
        if (nextSymbol.equals(X)) {
          Item advanced = item.advance();
          if (advanced != null) {
            State closure = computeClosure(advanced, grammar);
            for (Item closureItem : closure.getItemList()) {
              result.addItem(closureItem);
            }
          }
        }
      }
    }
    return result;
  }
  public void buildTables() {
    for (int i = 0; i < states.size(); i++) {
      actionTable.put(i, new HashMap<>());
      gotoTable.put(i, new HashMap<>());
    }
    for (int i = 0; i < states.size(); i++) {
      State state = states.getState(i);
      for (Item item : state.getItemList()) {
        String nextSymbol = item.getNextSymbol();

        if (nextSymbol == null || nextSymbol.isEmpty()) {
          if (item.getRule().getLhs().equals(grammar.startSymbol) &&
                  item.getLookahead().equals(Util.EOF)) {
            actionTable.get(i).put(Util.EOF, Action.createAccept());
          } else {
            actionTable.get(i).put(item.getLookahead(),
                    Action.createReduce(item.getRule()));
          }
        } else {
          String symbol = nextSymbol;
          int nextState = -1;
          for (Map.Entry<String, Integer> transition : states.getTransitions().get(i).entrySet()) {
            if (transition.getKey().equals(symbol)) {
              nextState = transition.getValue();
              break;
            }
          }
          if (nextState != -1) {
            if (grammar.isTerminal(symbol)) {
              actionTable.get(i).put(symbol,Action.createShift(nextState));
            } else {
              gotoTable.get(i).put(symbol, nextState);
            }
          }
        }
      }
    }
  }
  public String actionTableToString() {
    buildTables();
    StringBuilder builder = new StringBuilder();
    Set<String> terminals = new HashSet<>(grammar.terminals);
    terminals.add(Util.EOF);
    List<String> terminalList = new ArrayList<>(terminals);
    Collections.sort(terminalList);
    builder.append(String.format("%8s", "#"));
    for (String terminal : terminalList) {
      builder.append(String.format("%8s", terminal));
    }
    builder.append("\n");
    for (int i = 0; i < states.size(); i++) {
      builder.append(String.format("%8d", i));
      for (String terminal : terminalList) {
        Action action = actionTable.get(i).get(terminal);
        String actionStr = "";
        if (action != null) {
          if (action.isShift()) {
            actionStr = "S " + action.getState();
          } else if (action.isReduce()) {
            actionStr = action.getRule().toString().split(" ")[0];
          } else if (action.isAccept()) {
            actionStr = "acc";
          }
        }
        builder.append(String.format("%8s", actionStr));
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  public String gotoTableToString() {
    StringBuilder builder = new StringBuilder();
    return builder.toString();
  }
  public List<Action> parse(Lexer scanner) throws ParserException {
    Stack<Integer> stateStack = new Stack<>();
    Stack<String> symbolStack = new Stack<>();
    stateStack.push(0);
    List<Action> actions = new ArrayList<>();
    buildTables();
    ArrayList<? extends Token> tokens = new ArrayList<>(scanner.getAllTokens());
    Vocabulary v = scanner.getVocabulary();
    Stack<String> input = new Stack<>();
    Collections.reverse(tokens);
    input.add(Util.EOF);
    for (Token t : tokens) {
      input.push(v.getSymbolicName(t.getType()));
    }

    while (!input.isEmpty()) {
      String lookahead = input.peek();
      int currentState = stateStack.peek();

      Action action = actionTable.get(currentState).get(lookahead);

      if (action == null) {
        throw new ParserException("Unexpected symbol: " + lookahead + " at state " + currentState);
      }

      if (action.isShift()) {
        stateStack.push(action.getState());
        symbolStack.push(lookahead);
        input.pop();
        actions.add(action);
      } else if (action.isReduce()) {
        Rule rule = action.getRule();
        int rhsLength = rule.getRhs().size();
        for (int i = 0; i < rhsLength; i++) {
          stateStack.pop();
          symbolStack.pop();
        }
        String lhs = rule.getLhs();
        int nextState = gotoTable.get(stateStack.peek()).get(lhs);
        stateStack.push(nextState);
        symbolStack.push(lhs);
        actions.add(action);
      } else if (action.isAccept()) {
        actions.add(action);
        break;
      }
    }

    if (input.isEmpty()) {
      throw new ParserException("Unexpected end of input");
    }
    return actions;
  }

  //-------------------------------------------------------------------
  // Convenience functions
  //-------------------------------------------------------------------

  public List<Action> parseFromFile(String filename) throws IOException, ParserException {
//    System.out.println("\nReading input file " + filename + "\n");
    final CharStream charStream = CharStreams.fromFileName(filename);
    Lexer scanner = scanFile(charStream);
    return parse(scanner);
  }

  public List<Action> parseFromString(String program) throws ParserException {
    Lexer scanner = scanFile(CharStreams.fromString(program));
    return parse(scanner);
  }

  private Lexer scanFile(CharStream charStream) {
    // We use ANTLR's scanner (lexer) to produce the tokens.
    Lexer scanner = null;
    switch (grammar.grammarName) {
      case "Simple":
        scanner = new SimpleLexer(charStream);
        break;
      case "Paren":
        scanner = new ParenLexer(charStream);
        break;
      case "Expr":
        scanner = new ExprLexer(charStream);
        break;
      case "Tiny":
        scanner = new TinyLexer(charStream);
        break;
      default:
        System.out.println("Unknown scanner");
        break;
    }

    return scanner;
  }

}
