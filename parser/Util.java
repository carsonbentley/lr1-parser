/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author edwajohn
 */
public class Util {

  public static final String EPSILON = "EPSILON";
  public static final String EOF = "$";

  public static HashMap<String, HashSet<String>> computeFirst(ArrayList<String> symbols, HashSet<String> terminals, ArrayList<Rule> rules) {
    HashMap<String, HashSet<String>> first = new HashMap<>();
    for (String symbol : symbols) {
      first.put(symbol, new HashSet<>());
    }
    for (String terminal : terminals) {
      first.get(terminal).add(terminal);
    }
    // Algorithm 3.7
    boolean changed = true;
    while (changed) {
      changed = false;
      for (Rule rule : rules) {
        ArrayList<String> rhsSymbols = rule.getRhs();
        HashSet<String> rhs = new HashSet<>();
        final String B0 = rhsSymbols.get(0);
        rhs.addAll(noEpsilon(first.get(B0)));
        boolean stop = !first.get(B0).contains(EPSILON);
        for (int i = 0; !stop && i < rhsSymbols.size() - 1; ++i) {
          final String B = rhsSymbols.get(i + 1);
          rhs.addAll(noEpsilon(first.get(B0)));
          stop = !first.get(B).contains(EPSILON);
        }
        if (!stop) {
          rhs.add(EPSILON);
        }
        final String A = rule.getLhs();
        if (!first.get(A).containsAll(rhs)) {
          first.get(A).addAll(rhs);
          changed = true;
        }
      }
    }
//    System.out.println("\n");
//    for (String symbol : symbols) {
//      final String A = symbol;
//      System.out.println("FIRST(" + A + ") = " + first.get(A));
//    }
    return first;
  }

  public static HashMap<String, HashSet<String>> computeFollow(
          ArrayList<String> symbols, HashSet<String> terminals, ArrayList<Rule> rules,
          HashMap<String, HashSet<String>> first) {
    HashMap<String, HashSet<String>> follow = new HashMap<>();
    for (String symbol : symbols) {
      follow.put(symbol, new HashSet<>());
    }
    follow.get(rules.get(0).getLhs()).add(EOF);
    boolean changed = true;
    while (changed) {
      changed = false;
      for (Rule rule : rules) {
        HashSet<String> trailer = new HashSet<>(follow.get(rule.getLhs()));
        final ArrayList<String> rhsSymbols = rule.getRhs();
        for (int i = rhsSymbols.size() - 1; i >= 0; --i) {
          final String Bi = rhsSymbols.get(i);
          if (!terminals.contains(Bi)) {
            if (!follow.get(Bi).containsAll(trailer)) {
              follow.get(Bi).addAll(trailer);
              changed = true;
            }
            if (first.get(Bi).contains(EPSILON)) {
              trailer.addAll(noEpsilon(first.get(Bi)));
            } else {
              trailer = first.get(Bi);
            }
          } else {
            trailer = first.get(Bi);
          }
        }
      }
    }
//    System.out.println("\n");
//    for (String symbol : symbols) {
//      if (!terminals.contains(symbol)) {
//        System.out.println("FOLLOW(" + symbol + ") = " + follow.get(symbol));
//      }
//    }
    return follow;
  }

  public static HashMap<Rule, HashSet<String>> computeFirstPlus(
          ArrayList<String> symbols, HashSet<String> terminals, ArrayList<Rule> rules,
          HashMap<String, HashSet<String>> first, HashMap<String, HashSet<String>> follow) {
    HashMap<Rule, HashSet<String>> firstPlus = new HashMap<>();
    for (Rule rule : rules) {
      final String A = rule.getLhs();
      final String B = rule.getRhs().get(0);
      HashSet<String> firstPlusA = new HashSet<>();
      if (!first.get(B).contains(EPSILON)) {
        firstPlusA.addAll(first.get(B));
      } else {
        firstPlusA.addAll(first.get(B));
        firstPlusA.addAll(follow.get(A));
      }
      firstPlus.put(rule, firstPlusA);
    }
//    System.out.println("\n");
//    for (Rule rule : rules) {
//      System.out.println("FIRST+(" + rule + ") = " + firstPlus.get(rule));
//    }
    return firstPlus;
  }

  private static HashSet<String> noEpsilon(final HashSet<String> set) {
    HashSet<String> s = new HashSet<>(set);
    s.remove(EPSILON);
    return s;
  }
}
