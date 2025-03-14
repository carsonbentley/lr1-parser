package parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class Main {
  static final String dot = Character.toString((char) 0x25CF);

  /**
   * @param args the command line arguments
   * @throws java.io.IOException
   */
  public static void main(String[] args) throws IOException {
    Tests tests = new Tests();
    testClosure(tests);
    testStates(tests);
    testTables(tests);
    testParser(tests);

    //------------------------------------------------------------
    // Output number of tests that succeeded
    //------------------------------------------------------------
    System.out.println(tests.getSuccesses() + "/" + tests.getN()
            + " tests succeeded");
//    return tests.getFailures();

  }

  public static void testClosure(Tests tests) throws FileNotFoundException, IOException {
    // TODO: uncomment tests as you develop code
    {
//      // TODO: Become familiar with the Grammar class. You will use it a lot.
      Grammar grammar = new Grammar("data/Simple.cfg");


      //Find the closure of [N -> ● X, $]
       Rule rule = grammar.findRule("N -> X");

     State state = Parser.computeClosure(new Item(rule, 0, Util.EOF), grammar);
      state.setName(0);
      tests.test(state, "0: [[N -> ● X, $]]");
//
//
//      // Find the closure of [N -> ● N X, $]
      rule = grammar.findRule("N -> N X");
      state = Parser.computeClosure(new Item(rule, 0, Util.EOF), grammar);
      state.setName(0);
//      // [[N -> ● N X, $], [N -> ● N X, X], [N -> ● X, X]]
        System.out.println(state.toString());
      tests.test(state.getSize(), 3);
//
//      // Find the start state
      Item head = new Item(grammar.startRule, 0, Util.EOF);
      state = Parser.computeClosure(head, grammar);
        System.out.println(state.toString());
      // [[G -> ● N, $], [N -> ● N X, $], [N -> ● N X, X], [N -> ● X, X], [N -> ● X, $]]
      tests.test(state.getSize(), 5);
    }
//    {
      Grammar grammar = new Grammar("data/Paren.cfg");
//
//      // Find the closure of [list -> list ● pair, $]
//      Rule rule = grammar.findRule("list -> list pair");
//      State state = Parser.computeClosure(new Item(rule, 1, Util.EOF), grammar);
//      // [[list -> list ● pair, $], [pair -> ● OPAREN list CPAREN, $], [pair -> ● OPAREN CPAREN, $]]
//      tests.test(state.size(), 3);
//
//      // Find the closure of [pair -> OPAREN ● list CPAREN, $]
//      rule = grammar.findRule("pair -> OPAREN list CPAREN");
//      state = Parser.computeClosure(new Item(rule, 1, Util.EOF), grammar);
//      // [[pair -> OPAREN ● list CPAREN, $], [list -> ● list pair, CPAREN]
//      //  [list -> ● list pair, OPAREN], [list -> ● pair, OPAREN], [pair -> ● OPAREN list CPAREN, OPAREN]
//      //  [pair -> ● OPAREN CPAREN, OPAREN], [list -> ● pair, CPAREN], [pair -> ● OPAREN list CPAREN, CPAREN]
//      //  [pair -> ● OPAREN CPAREN, CPAREN]]
//      tests.test(state.size(), 9);
//
//      // Find the start state
//      Item head = new Item(grammar.startRule, 0, Util.EOF);
//      state = Parser.computeClosure(head, grammar);
//      // [[goal -> ● list, $], [list -> ● list pair, $], [list -> ● list pair, OPAREN]
//      //  [list -> ● pair, OPAREN], [pair -> ● OPAREN list CPAREN, OPAREN], [pair -> ● OPAREN CPAREN, OPAREN]
//      //  [list -> ● pair, $], [pair -> ● OPAREN list CPAREN, $], [pair -> ● OPAREN CPAREN, $]]
//      tests.test(state.size(), 9);
//    }
//    {
//      Grammar grammar = new Grammar("data/Expr.cfg");
//
//      // Find the start state
//      Item head = new Item(grammar.startRule, 0, Util.EOF);
//      State state = Parser.computeClosure(head, grammar);
//      // [[goal -> ● expr, $], [expr -> ● expr PLUS term, $], [expr -> ● expr PLUS term, PLUS], [expr -> ● expr MINUS term, PLUS], [expr -> ● expr PLUS term, MINUS], [expr -> ● term, PLUS], [term -> ● term MULTIPLY factor, PLUS], [term -> ● term MULTIPLY factor, MULTIPLY], [term -> ● term DIVIDE factor, MULTIPLY], [term -> ● term MULTIPLY factor, DIVIDE], [term -> ● factor, MULTIPLY], [factor -> ● OPAREN expr CPAREN, MULTIPLY], [factor -> ● INT, MULTIPLY], [factor -> ● FLOAT, MULTIPLY], [factor -> ● IDENTIFIER, MULTIPLY], [term -> ● term DIVIDE factor, DIVIDE], [term -> ● factor, DIVIDE], [factor -> ● OPAREN expr CPAREN, DIVIDE], [factor -> ● INT, DIVIDE], [factor -> ● FLOAT, DIVIDE], [factor -> ● IDENTIFIER, DIVIDE], [term -> ● term DIVIDE factor, PLUS], [term -> ● factor, PLUS], [factor -> ● OPAREN expr CPAREN, PLUS], [factor -> ● INT, PLUS], [factor -> ● FLOAT, PLUS], [factor -> ● IDENTIFIER, PLUS], [expr -> ● expr MINUS term, MINUS], [expr -> ● term, MINUS], [term -> ● term MULTIPLY factor, MINUS], [term -> ● term DIVIDE factor, MINUS], [term -> ● factor, MINUS], [factor -> ● OPAREN expr CPAREN, MINUS], [factor -> ● INT, MINUS], [factor -> ● FLOAT, MINUS], [factor -> ● IDENTIFIER, MINUS], [expr -> ● expr MINUS term, $], [expr -> ● term, $], [term -> ● term MULTIPLY factor, $], [term -> ● term DIVIDE factor, $], [term -> ● factor, $], [factor -> ● OPAREN expr CPAREN, $], [factor -> ● INT, $], [factor -> ● FLOAT, $], [factor -> ● IDENTIFIER, $]]
//      tests.test(state.size(), 45);
//    }
  }

  public static void testStates(Tests tests) throws FileNotFoundException, IOException {
//    {
//      Parser parser = new Parser("data/Simple.cfg");
//      States states = parser.getStates();
//      tests.test(states.size(), 4);
//    }
//    {
//      Parser parser = new Parser("data/Paren.cfg");
//      States states = parser.getStates();
//      tests.test(states.size(), 14);
//    }
//    {
//      Parser parser = new Parser("data/Expr.cfg");
//      States states = parser.getStates();
//      tests.test(states.size(), 34);
//    }
  }

  public static void testTables(Tests tests) throws FileNotFoundException, IOException {
//    {
//      Parser parser = new Parser("data/Simple.cfg");
//      String actionTable = parser.actionTableToString();
//      tests.test(countMatches(actionTable, 'S'), 2); // 2 shifts
//      tests.test(countMatches(actionTable, 'R'), 4); // 4 reduces
//      tests.test(countMatches(actionTable, "acc"), 1); // 1 accept
//    }
//    {
//      Parser parser = new Parser("data/Paren.cfg");
//      String actionTable = parser.actionTableToString();
//      tests.test(countMatches(actionTable, 'S'), 10); // 10 shifts
//      tests.test(countMatches(actionTable, 'R'), 18); // 18 reduces (2 of the Rs are in the header)
//      tests.test(countMatches(actionTable, "acc"), 1); // 1 accept
//    }
//    {
//      Parser parser = new Parser("data/Expr.cfg");
//      String actionTable = parser.actionTableToString();
//      tests.test(countMatches(actionTable, 'S'), 66);
//      tests.test(countMatches(actionTable, 'R'), 91);
//      tests.test(countMatches(actionTable, "acc"), 1);
//    }
  }

  public static void testParser(Tests tests) throws FileNotFoundException, IOException {
//    {
//      Parser parser = new Parser("data/Simple.cfg");
//
//      String s = "xxx";
//      try {
//        List<Action> actions = parser.parseFromString(s);
//        tests.test(countMatches(actions.toString(), 'S'), 3); // 3 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 3); // 3 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + s + ": " + e.getMessage());
//      }
//
//      s = "xxxxx";
//      try {
//        List<Action> actions = parser.parseFromString(s);
//        tests.test(countMatches(actions.toString(), 'S'), 5); // 5 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 5); // 5 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + s + ": " + e.getMessage());
//      }
//
//      s = "";
//      try {
//        List<Action> actions = parser.parseFromString(s);
//        tests.addFailure("Should have failed to parse");
//      } catch (ParserException e) {
//      }
//    }
//
//    {
//      Parser parser = new Parser("data/Paren.cfg");
//
//      String fn = "data/paren0.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 2); // 2 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 2); // 2 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/paren1.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 20); // 20 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 20); // 20 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/paren2.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//      fn = "data/paren3.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//      fn = "data/paren4.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 10); // 10 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 10); // 10 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//    }
//    {
//      Parser parser = new Parser("data/Expr.cfg");
//
//      String fn = "data/expr1.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 7); // 7 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 10); // 10 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/expr2.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//      fn = "data/expr3.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 1); // 1 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 3); // 3 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/expr4.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 15); // 15 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 22); // 22 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/expr5.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//      fn = "data/expr6.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//    }
//
//    {
//      // TODO: To pass these tests you must implement data/Tiny.cfg
//      //   according to the grammar given in data/tinyGrammar.pdf.
//      //   See notes in data/Tiny.cfg (be sure to read all of them!).
//      Parser parser = new Parser("data/Tiny.cfg");
//
//      String fn = "data/tiny1.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 11); // 11 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 25); // 25 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/tiny2.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.test(countMatches(actions.toString(), 'S'), 27); // 27 shift actions
//        tests.test(countMatches(actions.toString(), 'R'), 55); // 55 reduce actions
//      } catch (ParserException e) {
//        tests.addFailure("Failed to parse " + fn + ": " + e.getMessage());
//      }
//
//      fn = "data/tiny3.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//      fn = "data/tiny4.dat";
//      try {
//        List<Action> actions = parser.parseFromFile(fn);
//        tests.addFailure("Should have failed to parse " + fn);
//      } catch (ParserException e) {
//      }
//
//    }
  }

  private static int countMatches(String s, char c) {
    return (int) s.chars().filter(ch -> ch == c).count();
  }

  private static int countMatches(String s, String c) {
    return s.split(c, -1).length - 1;
  }
}
