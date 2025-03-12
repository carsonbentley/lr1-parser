/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author edwajohn
 */
public class Action {

  enum Act {

    SHIFT, REDUCE, ACCEPT
  };
  private final Act act;
  private final Integer state;
  private final Rule rule;

  static Action createShift(Integer state) {
    return new Action(Act.SHIFT, state, null);
  }

  static Action createReduce(Rule rule) {
    return new Action(Act.REDUCE, null, rule);
  }

  static Action createAccept() {
    return new Action(Act.ACCEPT, null, null);
  }

  private Action(Act act, Integer state, Rule rule) {
    this.act = act;
    this.state = state;
    this.rule = rule;
  }

  public boolean isShift() {
    return act == Act.SHIFT;
  }

  public boolean isReduce() {
    return act == Act.REDUCE;
  }

  public boolean isAccept() {
    return act == Act.ACCEPT;
  }

  public Integer getState() {
    return state;
  }

  public Rule getRule() {
    return rule;
  }

  @Override
  public String toString() {
    if (isShift()) {
      return "S" + state;
    }
    if (isReduce()) {
      return "R" + rule.getName();
    }
    return "acc";
  }

}
