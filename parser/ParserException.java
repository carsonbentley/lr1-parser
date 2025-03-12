/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import org.antlr.v4.runtime.Token;

/**
 *
 * @author edwajohn
 */
public class ParserException extends Exception {

  /**
   * Create a ParserException. This is a convenience factory method to easily
   * format your output to meet project specification.
   *
   * @param words the words being parsed
   * @param i the index to the current word being parsed
   * @return
   */
  public static ParserException create(final ArrayList<? extends Token> words, final int i) {
    String msg;
    if (i < words.size()) {
      msg = "Error parsing token \"" + words.get(i).getText() + "\" in \n    ";
      int preLen = 4;
      for (int j = 0; j < i; ++j) {
        Token token = words.get(j);
        msg = msg + token.getText() + " ";
        preLen += token.getText().length() + 1;
      }
      final int curLen = words.get(i).getText().length();
      msg = msg + words.get(i).getText();
      if (i < words.size() - 1) {
        msg = msg + " ";
      }
      for (int j = i + 1; j < words.size(); ++j) {
        Token token = words.get(j);
        msg = msg + token.getText();
        if (j < words.size() - 1) {
          msg = msg + " ";
        }
      }

      msg = msg + "\n";
      for (int j = 0; j < preLen; ++j) {
        msg += " ";
      }
      for (int j = 0; j < curLen; ++j) {
        msg += "^";
      }
    } else {
      msg = "Unexpectedly reached end of file in\n";
      for (int j = 0; j < words.size(); ++j) {
        Token token = words.get(j);
        msg = msg + token.getText();
        if (j < words.size() - 1) {
          msg = msg + " ";
        }
      }

    }
    return new ParserException(msg);
  }

  /**
   * Constructor. You will most likely NOT use this constructor directly, but
   * rather use the convenience factory method create(). You may choose to use
   * this constructor, however, if your data structures are set up differently
   * than what the factory method expects.
   *
   * @param msg
   */
  public ParserException(String msg) {
    super(msg);
  }

}
