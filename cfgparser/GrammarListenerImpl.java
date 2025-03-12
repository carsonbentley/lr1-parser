/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfgparser;

import cfgparser.GrammarBaseListener;
import cfgparser.GrammarParser;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.ErrorNode;
import parser.Rule;

/**
 *
 * @author edwajohn
 */
public class GrammarListenerImpl extends GrammarBaseListener {

  String name;
  String curLhs;
  Rule curRule;
  ArrayList<Rule> rules;
  String startSymbol;

  public GrammarListenerImpl() {
    rules = new ArrayList<>();
    startSymbol = null;
  }

  public void print() {
    for (Rule rule : rules) {
      System.out.println(rule);
    }
  }

  @Override
  public void enterName(GrammarParser.NameContext ctx) {
    name = ctx.getText();
  }

  @Override
  public void enterLhs(GrammarParser.LhsContext ctx) {
    curLhs = ctx.getText();
    if (startSymbol == null) {
      startSymbol = curLhs;
    }
  }

  @Override
  public void enterRhs(GrammarParser.RhsContext ctx) {
    curRule = new Rule(curLhs);
  }

  @Override
  public void exitRhs(GrammarParser.RhsContext ctx) {
    curRule.setName(rules.size());
    rules.add(curRule);
  }

  @Override
  public void enterSymbol(GrammarParser.SymbolContext ctx) {
    curRule.addRhs(ctx.getText());
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
    System.out.println("Error!");
  }

}
