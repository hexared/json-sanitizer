package com.google.json;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.junit.FuzzTest;
import com.google.json.EvalMinifier;
import com.google.json.JsonSanitizer;

public class JazzerFuzzTest {

    @FuzzTest
    public void testSanitizerWithFuzzyInputs(FuzzedDataProvider data) {
        try {
            JsonSanitizer.sanitize(data.consumeRemainingAsString(), 10);
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
    public void testMinifyWithFuzzyInputs(FuzzedDataProvider data) {
        try {
            EvalMinifier.minify(data.consumeRemainingAsString(), 10);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    @FuzzTest
    public void testMinifyWithFuzzyDepth(FuzzedDataProvider data) {
        try {
            EvalMinifier.minify(data.consumeRemainingAsString(), data.consumeInt());
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }


}
