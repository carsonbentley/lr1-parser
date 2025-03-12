/*
 * Do not modify this file.
 */
package parser;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 */
public class Rule {

  private int name;
  private final String lhs;
  private ArrayList<String> rhs;

  public Rule(String lhs) {
    this.name = 0;
    this.lhs = lhs;
    rhs = new ArrayList<>();
  }

  public void setName(int name) {
    this.name = name;
  }

  public int getName() {
    return this.name;
  }

  @Override
  public String toString() {
    String ret = "R" + name + " " + lhs + " -> ";
    for (String symbol : getRhs()) {
      ret = ret + symbol + " ";
    }
    return ret;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(this.lhs);
    hash = 37 * hash + this.name;
    for (int i = 0; i < this.rhs.size(); ++i) {
      hash = 37 * hash + Objects.hashCode(this.rhs.get(i));
    }
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
    final Rule other = (Rule) obj;
    if (!this.lhs.equals(other.lhs)) {
      return false;
    }
    if (this.name != other.name) {
      return false;
    }
    if (this.rhs.size() != other.rhs.size()) {
      return false;
    }
    for (int i = 0; i < this.rhs.size(); ++i) {
      if (!this.rhs.get(i).equals(other.rhs.get(i))) {
        return false;
      }
    }
    return true;
  }

  public String getLhs() {
    return lhs;
  }

  public ArrayList<String> getRhs() {
    if (rhs.isEmpty()) {
      rhs.add("EPSILON");
    }
    return rhs;
  }

  public void addRhs(String symbol) {
    rhs.add(symbol);
  }

}
