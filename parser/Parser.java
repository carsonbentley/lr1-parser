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
  private final HashMap<Integer, HashMap<String, Action>> actionTable;
  /**
   * Goto table for bottom-up parsing. Accessed as gotoTable.get(state).get(nonterminal).
   * You may replace the Integers with State classes if you choose.
   */
  private final HashMap<Integer, HashMap<String, Integer>> gotoTable;

  public Parser(String grammarFilename) throws IOException {
    actionTable = new HashMap<>();
    gotoTable = new HashMap<>();

    grammar = new Grammar(grammarFilename);

    states = new States();

    // TODO: Call methods to compute the states and parsing tables here.
  }

  public States getStates() {
    return states;
  }

  static public State computeClosure(Item I, Grammar grammar) {
    State closure = new State();
    closure.addItem(I);
    Queue<Item> queue = new LinkedList<>();
    queue.add(I);
    while (!queue.isEmpty()) {
      Item item = queue.poll();
      String nextSymbol = item.getNextSymbol();
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

    return closure;
  }
  /*// TODO: Implement this method.
  static public State computeClosure(Item I, Grammar grammar) {
    State closure = new State();
    rClosure(closure, I, grammar);
   *//* for (Rule rule : rules) {
      if(rule.getLhs().startsWith(next)){

      }
    }*//*
    return closure;
  }
  static public void rClosure(State state, Item I, Grammar grammar) {
      if(!state.addItem(I)){
        return;
      }
      state.addItem(I);
      ArrayList<Rule> rules = grammar.rules;
      String next = I.getNextSymbol();
      Rule itemRule = I.getRule();
      HashMap<String, HashSet<String>> first = Util.computeFirst(grammar.symbols, grammar.terminals, rules);
      List<String> firstBeta = (itemRule.getRhs().subList(I.getDot(), itemRule.getRhs().size()));
      for (Rule rule : rules) {
        if (rule.getLhs().startsWith(next)) {
          boolean addedFirst = false;
            while(!addedFirst){
              if(I.getLookahead().equals(Util.EOF)){
                state.addItem(new Item(rule, 0, I.getLookahead()));
                rClosure(state, new Item(rule, 0, I.getLookahead()), grammar);
                addedFirst = true;
              }
              for (String symbol : firstBeta) {
                if(!first.get(symbol).isEmpty()){
                  for (String firstSymbol : first.get(symbol) ) {
                    if(grammar.isTerminal(firstSymbol)){
                      rClosure(state, new Item(rule, 0, firstSymbol), grammar);
                      state.addItem(new Item(rule, 0, firstSymbol));
                    }
                  }

                  addedFirst = true;
                  break;
                }

                }
              if(first.get(grammar.startSymbol).toString().equals(I.getLookahead())){

                rClosure(state, new Item(rule, 0, Util.EOF), grammar);
                state.addItem(new Item(rule, 0, Util.EOF));
                addedFirst = true;
              }
              }
        }
       *//* if (rule.getLhs().startsWith(next)) {
          System.out.println("starts with");
          HashSet<String> firstBeta = first.get(next);
          if (firstBeta.isEmpty()) {
            firstBeta = first.get(I.getLookahead());
            if (I.getLookahead().equals("$")) {
              System.out.println("$ lookahead");

              rClosure(state, new Item(rule, 0, "$"), grammar);
              return;
            }
          }
          for (String terminal : firstBeta) {
            if (grammar.isTerminal(terminal)) {
              System.out.println("bye");
             rClosure(state, new Item(rule, 0, terminal), grammar);
             return;
            }
          }
        }
        else{
          return;
        }
*//*
      }
    }
*/

  // TODO: Implement this method.
  //   This returns a new state that represents the transition from
  //   the given state on the symbol X.
  // @PARAMS

  static public State GOTO(State state, String X, Grammar grammar) {
    State ret = new State();
    List<Item> itemSet = state.getItemList();
    for(int i = 0; i <= itemSet.size(); i ++) {
      Item item = itemSet.get(i);
      // TODO: Might need to do more here
      if(item.getNextSymbol().equals(X))
      {
        if (item.advance() != null) {
          ret.addItem(item.advance());
          State closure = computeClosure(item.advance(), grammar);
          closure.getItems().forEach(ret::addItem);
        }
      }

    }
    return ret;
  }

  // TODO: Implement this method
  // You will want to use StringBuilder. Another useful method will be String.format: for
  // printing a value in the table, use
  //   String.format("%8s", value)
  // How much whitespace you have shouldn't matter with regard to the tests, but it will
  // help you debug if you can format it nicely.
  public String actionTableToString() {
    StringBuilder builder = new StringBuilder();
    return builder.toString();
  }

  // TODO: Implement this method
  // You will want to use StringBuilder. Another useful method will be String.format: for
  // printing a value in the table, use
  //   String.format("%8s", value)
  // How much whitespace you have shouldn't matter with regard to the tests, but it will
  // help you debug if you can format it nicely.
  public String gotoTableToString() {
    StringBuilder builder = new StringBuilder();
    return builder.toString();
  }

  // TODO: Implement this method
  // You should return a list of the actions taken.
  public List<Action> parse(Lexer scanner) throws ParserException {
    // tokens is the output from the scanner. It is the list of tokens
    // scanned from the input file.
    // To get the token type: v.getSymbolicName(t.getType())
    // To get the token lexeme: t.getText()
    ArrayList<? extends Token> tokens = new ArrayList<>(scanner.getAllTokens());
    Vocabulary v = scanner.getVocabulary();

    Stack<String> input = new Stack<>();
    Collections.reverse(tokens);
    input.add(Util.EOF);
    for (Token t : tokens) {
      input.push(v.getSymbolicName(t.getType()));
    }
    Collections.reverse(tokens);
//    System.out.println(input);

    // TODO: Parse the tokens. On an error, throw a ParseException, like so:
    //    throw ParserException.create(tokens, i)
    List<Action> actions = new ArrayList<>();
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
