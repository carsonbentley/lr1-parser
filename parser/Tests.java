package parser;

/**
 *
 * @author edwajohn
 */
public class Tests {

  private int N = 0;
  private int failures = 0;

  public void test(boolean b) {
    N++;
    if (!b) {
      System.err.println("Failed test " + N);
      failures++;
    }
  }

  public void test(boolean b, String message) {
    test(b);
    if (!b) {
      System.err.println(message);
    }
  }

  public void test(Object a, Object target) {
    N++;
    if (a == null && target == null) {
      return;
    }
    if (a == null || target == null || !a.equals(target)) {
      System.err.println("Failed test " + N);
      System.err.println("  " + a + " not equal to target " + target);
      failures++;
    }
  }

  public void test(Object a, String target) {
    N++;
    if (a == null && target == null) {
      return;
    }
    if (a == null || target == null || !a.toString().equals(target)) {
      System.err.println("Failed test " + N);
      System.err.println("  " + a + " not equal to target " + target);
      failures++;
    }
  }

  public void addFailure(String msg) {
    N++;
    System.err.println("Failed test " + N);
    System.err.println(msg);
    failures++;
  }

  public int getN() {
    return N;
  }

  public int getSuccesses() {
    return N - failures;
  }

  public int getFailures() {
    return failures;
  }
}
