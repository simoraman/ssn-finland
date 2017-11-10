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
}
