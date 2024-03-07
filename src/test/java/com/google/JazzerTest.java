package com.google;
import static org.junit.Assume.assumeTrue;

import com.code_intelligence.jazzer.junit.FuzzTest;

public class JazzerTest {

    private static class IntHolder {
    private final int i;

    IntHolder(int i) {
      this.i = i;
    }

    public int getI() {
      return i;
    }
  }

  @FuzzTest(maxDuration = "5m")
  void autofuzz(String str, IntHolder holder) {
    assumeTrue(holder != null);
    if (holder.getI() == 1234 && str != null && str.contains("jazzer")) {
      throw new RuntimeException();
    }