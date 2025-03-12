/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfgparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parser.Rule;

/**
 *
 * @author edwajohn
 */
public class Grammar {

  private String name;
  private ArrayList<Rule> rules;
  private String startSymbol;

  public Grammar(String filename) throws IOException {
    GrammarLexer cfglexer = new GrammarLexer(new ANTLRFileStream(filename));
    GrammarParser cfgparser = new GrammarParser(new BufferedTokenStream(cfglexer));
    ParserRuleContext tree = cfgparser.getContext(); // parse

    GrammarParser.GoalContext context = cfgparser.goal();

    // Walk it and attach our listener
    GrammarListenerImpl listener = new GrammarListenerImpl();
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(listener, context);
    name = listener.name;
    rules = listener.rules;
    startSymbol = listener.startSymbol;
  }

  public ArrayList<Rule> getRules() {
    return rules;
  }

  public String getName() {
    return name;
  }

  public ArrayList<String> getSymbols() {
    HashSet<String> symbols = new HashSet<>();
    for (Rule rule : rules) {
      symbols.add(rule.getLhs());
      for (String symbol : rule.getRhs()) {
        symbols.add(symbol);
      }
    }
    return new ArrayList<>(symbols);
  }

  public String getStartSymbol() {
    return startSymbol;
  }
}
