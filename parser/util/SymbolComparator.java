/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.util;

import java.util.Comparator;
import java.util.HashSet;

/**
 *
 * @author edwajohn
 */
public class SymbolComparator implements Comparator<String> {

  private final HashSet<String> terminals;

  public SymbolComparator(HashSet<String> terminals) {
    this.terminals = terminals;
  }

  @Override
  public int compare(String o1, String o2) {
    // Slightly less efficient code for clarity.
    if (terminals.contains(o1) && terminals.contains(o2)) {
      return o1.compareTo(o2);
    }
    if (terminals.contains(o1)) {
      return 1;
    }
    if (terminals.contains(o2)) {
      return -1;
    }
    return o1.compareTo(o2);
  }

}
