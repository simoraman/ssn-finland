package com.simoraman.ssnfinland;

import junit.framework.TestCase;

public class SsnGenerationTest extends TestCase {
    public SsnGenerationTest(String testName) {
        super(testName);
    }

    public void testGenerateSsn() {
        String generatedSsn = SsnFinland.generateWithAge(18);
        Identity i = SsnFinland.parse(generatedSsn);
        assertEquals(18, i.age);
    }

    public void testGenerateSsn21stCentury() {
        String generatedSsn = SsnFinland.generateWithAge(1);
        Identity i = SsnFinland.parse(generatedSsn);
        assertEquals(1, i.age);
    }

    public void testGenerateSsn19thCentury() {
        String generatedSsn = SsnFinland.generateWithAge(120);
        Identity i = SsnFinland.parse(generatedSsn);
        assertEquals(120, i.age);
    }
}
