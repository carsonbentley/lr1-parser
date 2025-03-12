/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 * An LR(1) item.
 *
 */
public class Item implements Comparable {

  private Rule rule;
  /**
   * Position of the stack top in the production's RHS.
   */
  private int dot;
  /**
   * The lookahead symbol
   */
  private String a;

  /**
   * Creates a set of items given the rule and lookahead, one item per potential
   * stack position placement. The number of item is the number of symbols on
   * the rhs of the rule plus one.
   *
   * @param rule
   * @param a
   * @return
   */
  static ArrayList<Item> create(Rule rule, String a) {
    ArrayList<Item> ret = new ArrayList<>();
    final ArrayList<String> rhs = rule.getRhs();
    for (int i = 0; i < rhs.size(); ++i) {
      ret.add(new Item(rule, i, a));
    }
    ret.add(new Item(rule, rhs.size(), a));
    return ret;
  }

  public Item(Rule rule, int dot, String a) {
    this.rule = rule;
    this.dot = dot;
    this.a = a;
  }

  public Rule getRule() {
    return rule;
  }

  public int getDot() {
    return dot;
  }

  /**
   * @return the lookahead symbol
   */
  public String getA() {
    return a;
  }

  public String getLookahead() {
    return getA();
  }

  /**
   * Returns the symbol following the stack pointer. Returns null if the pointer
   * is at the end of the rhs list.
   *
   * @return
   */
  public String getNextSymbol() {
    if (dot < rule.getRhs().size()) {
      return rule.getRhs().get(dot);
    }
    return null;
  }

  public String getNextNextSymbol() {
    if (dot < rule.getRhs().size() - 1) {
      return rule.getRhs().get(dot + 1);
    }
    return null;
  }

  public Item advance() {
    return new Item(rule, dot + 1, a);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(this.rule);
    hash = 37 * hash + this.dot;
    hash = 37 * hash + Objects.hashCode(this.a);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Item other = (Item) obj;
    if (!Objects.equals(this.rule, other.rule)) {
      return false;
    }
    if (this.dot != other.dot) {
      return false;
    }
    if (!Objects.equals(this.a, other.a)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String ret = "[" + rule.getLhs() + " -> ";
    final ArrayList<String> rhs = rule.getRhs();
    for (int i = 0; i < dot; ++i) {
      ret += rhs.get(i) + " ";
    }
    ret += Character.toString((char) 0x25CF);
    if (dot < rhs.size()) {
      ret += " ";
    }
    for (int i = dot; i < rhs.size(); ++i) {
      ret += rhs.get(i);
      if (i < rhs.size() - 1) {
        ret += " ";
      }
    }
    ret += ", " + a + "]";
    return ret;
  }

  @Override
  public int compareTo(Object o) {
    return toString().compareTo(o.toString());
  }

}
