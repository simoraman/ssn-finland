package com.simoraman.ssnfinland;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SsnValidityTest
        extends TestCase {

    public SsnValidityTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SsnValidityTest.class);
        suite.addTestSuite(SsnParseTest.class);
        suite.addTestSuite(SsnGenerationTest.class);
        return suite;
    }

    public void testValidSsn() {
        String validSsn = "090385-2751";
        assertTrue(SsnFinland.isValidSsn(validSsn));
    }

    public void testEmptyStringIsInvalid() {
        String inValidSsn = "";
        assertFalse(SsnFinland.isValidSsn(inValidSsn));
    }

    public void testNullIsInvalid() {
        String lol = null;
        assertFalse(SsnFinland.isValidSsn(lol));
    }

    public void testOnlyDateOfBirthIsInvalid() {
        String dateOfBirthOnly = "121289";
        assertFalse(SsnFinland.isValidSsn(dateOfBirthOnly));
    }

    public void testCalculateCheckSum() {
        String invalidCheckSum = "090385-2752";
        assertFalse(SsnFinland.isValidSsn(invalidCheckSum));
    }

    public void testDatePartMustBeValidDate() {
        String invalidDay = "001185-111C";
        assertFalse(SsnFinland.isValidSsn(invalidDay));

        invalidDay = "321185-1111";
        assertFalse(SsnFinland.isValidSsn(invalidDay));

        invalidDay = "101385-111E";
        assertFalse(SsnFinland.isValidSsn(invalidDay));

        invalidDay = "100085-111Y";
        assertFalse(SsnFinland.isValidSsn(invalidDay));
    }

    public void testAllowedCenturyMarks() {
        String validSsn = "090385+2751";
        assertTrue(SsnFinland.isValidSsn(validSsn));

        validSsn = "090385A2751";
        assertTrue(SsnFinland.isValidSsn(validSsn));

        String invalidCentury = "090385B2751";
        assertFalse(SsnFinland.isValidSsn(invalidCentury));
    }

}
