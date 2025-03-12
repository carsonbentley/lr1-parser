/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;
import java.util.*;

import parser.util.SymbolComparator;

/**
 *
 * @author edwajohn
 */
public class Grammar {

  /**
   * Name of the grammar from the grammar file. This is computed for you.
   */
  final String grammarName;
  /**
   * The list of rules found in the input grammar file. This is computed for
   * you.
   */
  final ArrayList<Rule> rules;
  /**
   * The list of all symbols found in the input grammar file. This is computed
   * for you.
   */
  final ArrayList<String> symbols;
  /**
   * The set of all terminal symbols. To see if a symbol is terminal or not:
   * terminals.contains(symbol). This is computed for you.
   */
  final HashSet<String> terminals;
  /**
   * The set of all nonterminal symbols. To see if a symbol is nonterminal:
   * nonterminals.contains(symbol). This is computed for you.
   */
  final HashSet<String> nonterminals;
  /**
   * Start symbol that is provided for you.
   */
  final String startSymbol;
  /**
   * Start production rule that is provided for you.
   */
  final Rule startRule;
  /**
   * Maps non-terminals to all rules that have the non-terminal on the lhs.
   */
  HashMap<String, ArrayList<Rule>> nt2rules;

  final HashMap<String, HashSet<String>> first;
  final HashMap<String, HashSet<String>> follow;
  final HashMap<Rule, HashSet<String>> firstPlus;

  public Grammar(String grammarFilename) throws IOException {
    System.out.println("Reading grammar " + grammarFilename);
    cfgparser.Grammar grammar = new cfgparser.Grammar(grammarFilename);

    this.grammarName = grammar.getName();
    this.rules = grammar.getRules();
    this.symbols = grammar.getSymbols();
    this.startSymbol = grammar.getStartSymbol();
    this.terminals = new HashSet<>(symbols);
    this.nonterminals = new HashSet<>();

    Rule start = null;
    for (Rule rule : this.rules) {
      if (rule.getLhs().equals(this.startSymbol)) {
        start = rule;
      }
    }
    if (start == null) {
      throw new RuntimeException("Unexpected null start production rule");
    }
    this.startRule = start;

    nt2rules = new HashMap<>();
    System.out.println("\nRules");
    for (Rule rule : rules) {
      System.out.println(rule.toString());
      final String lhs = rule.getLhs();
      terminals.remove(lhs);
      nonterminals.add(lhs);
      if (!nt2rules.containsKey(lhs)) {
        nt2rules.put(lhs, new ArrayList<>());
      }
      nt2rules.get(lhs).add(rule);
    }

    // Sort the list of symbols for easy reading of output.
    symbols.sort(new SymbolComparator(terminals));

    // Compute first, follow and firstPlus sets.
    first = Util.computeFirst(symbols, terminals, rules);
    follow = Util.computeFollow(symbols, terminals, rules, first);
    firstPlus = Util.computeFirstPlus(symbols, terminals, rules, first, follow);
  }

  public boolean isTerminal(String symbol) {
    return terminals.contains(symbol);
  }

  public boolean isNonterminal(String symbol) {
    return nonterminals.contains(symbol);
  }

  public Rule findRule(String lhs, List<String> rhs) {
    for (Rule rule : rules) {
      if (rule.getLhs().equals(lhs) && rule.getRhs().equals(rhs)) {
        return rule;
      }
    }
    return null;
  }

  public Rule findRule(String s) {
    List<String> symbols = Arrays.asList(s.split(" "));
    return findRule(symbols.get(0), symbols.subList(2, symbols.size()));
  }
}
