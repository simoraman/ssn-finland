package com.simoraman.ssnfinland;

import junit.framework.TestCase;

public class SsnGenerationTest extends TestCase {
    private SsnFinland ssn;
    public SsnGenerationTest(String testName) {
        super(testName);
    }
    protected void setUp() {
        ssn = new SsnFinland();
    }

    public void testGenerateSsn() {
        String generatedSsn = ssn.generateWithAge(18);
        Identity i = ssn.parse(generatedSsn);
        assertEquals(18, i.age);
    }

    public void testGenerateSsn21stCentury() {
        String generatedSsn = ssn.generateWithAge(1);
        Identity i = ssn.parse(generatedSsn);
        assertEquals(1, i.age);
    }

    public void testGenerateSsn19thCentury() {
        String generatedSsn = ssn.generateWithAge(120);
        Identity i = ssn.parse(generatedSsn);
        assertEquals(120, i.age);
    }
}
