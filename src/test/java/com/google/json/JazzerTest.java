package com.google.json;
import static org.junit.Assume.assumeTrue;

import com.code_intelligence.jazzer.junit.FuzzTest;
import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.google.json.JsonSanitizer;

public class JazzerTest {

    @FuzzTest
    void fuzzerSanitize(FuzzedDataProvider data){
        try {
            JsonSanitizer.sanitize(data.consumeRemainingAsString(), 10);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

}
