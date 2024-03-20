package com.google.json;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import com.code_intelligence.jazzer.api.FuzzerSecurityIssueHigh;
import com.code_intelligence.jazzer.api.FuzzerSecurityIssueMedium;

public class JazzerFuzzTest {

    @FuzzTest
    public void testSanitizerWithFuzzyInputs(FuzzedDataProvider data) {
        try {
            JsonSanitizer.sanitize(data.consumeRemainingAsString(), 10);
            JsonSanitizer.sanitize(data.consumeString(4096));
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    @FuzzTest
    public void testSanitizerWithFuzzyDepth(FuzzedDataProvider data) {
        try {
            JsonSanitizer.sanitize(data.consumeRemainingAsString(), data.consumeInt());
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    @FuzzTest
    public void testForbidden(FuzzedDataProvider data) {
    String input = data.consumeRemainingAsString();
    String validJson;
    try {
      validJson = JsonSanitizer.sanitize(input, 10);
    } catch (Exception e) {
      return;
    }

    // Check for forbidden substrings. As these would enable Cross-Site Scripting, treat every
    // finding as a high severity vulnerability.
    assert !validJson.contains("</script")
        : new FuzzerSecurityIssueHigh("Output contains </script");
    assert !validJson.contains("]]>") : new FuzzerSecurityIssueHigh("Output contains ]]>");

    // Check for more forbidden substrings. As these would not directly enable Cross-Site Scripting
    // in general, but may impact script execution on the embedding page, treat each finding as a
    // medium severity vulnerability.
    assert !validJson.contains("<script")
        : new FuzzerSecurityIssueMedium("Output contains <script");
    assert !validJson.contains("<!--") : new FuzzerSecurityIssueMedium("Output contains <!--");
    }

    @FuzzTest
    public void testDoubleSanitize(FuzzedDataProvider data) {
    String input = data.consumeRemainingAsString();
    String validJson;
    try {
      validJson = JsonSanitizer.sanitize(input, 10);
    } catch (Exception e) {
      return;
    }

    // Ensure that sanitizing twice does not give different output (idempotence). Since failure to
    // be idempotent is not a security issue in itself, fail with a regular AssertionError.
    assert JsonSanitizer.sanitize(validJson).equals(validJson) : "Not idempotent";
  }



}
