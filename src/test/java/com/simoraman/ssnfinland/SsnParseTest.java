package com.simoraman.ssnfinland;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SsnParseTest extends TestCase {
    private SsnFinland ssn;

    public SsnParseTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( SsnValidityTest.class );
    }

    protected void setUp() {
        ssn = new SsnFinland();
    }

    public void testParseValidMale() {
        String validMaleSsn = "090385-2751";
        Identity i = ssn.parse(validMaleSsn);
        assertTrue(i.isValid);
        assertEquals("male", i.sex);
    }


    public void testParseValidFemale() {
        String validMaleSsn = "070585-848U";
        Identity i = ssn.parse(validMaleSsn);
        assertTrue(i.isValid);
        assertEquals("female", i.sex);
    }
}
