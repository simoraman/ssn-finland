package com.simoraman.ssnfinland;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple SsnFinland.
 */
public class SsnValidityTest
    extends TestCase
{
    private SsnFinland ssn;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SsnValidityTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite( SsnValidityTest.class );
        suite.addTestSuite(SsnParseTest.class);
        return suite;
    }

    protected void setUp() {
        ssn = new SsnFinland();
    }
    public void testValidSsn() {
        String validSsn = "090385-2751";
        assertTrue(ssn.isValidSsn(validSsn));
    }

    public void testEmptyStringIsInvalid() {
        String inValidSsn = "";
        assertFalse(ssn.isValidSsn(inValidSsn));
    }

    public void testNullIsInvalid() {
        String lol = null;
        assertFalse(ssn.isValidSsn(lol));
    }

    public void testOnlyDateOfBirthIsInvalid() {
        String dateOfBirthOnly = "121289";
        assertFalse(ssn.isValidSsn(dateOfBirthOnly));
    }

    public void testCalculateCheckSum() {
        String invalidCheckSum = "090385-2752";
        assertFalse(ssn.isValidSsn(invalidCheckSum));
    }

    public void testDatePartMustBeValidDate() {
        String invalidDay = "001185-111C";
        assertFalse(ssn.isValidSsn(invalidDay));

        invalidDay = "321185-1111";
        assertFalse(ssn.isValidSsn(invalidDay));

        invalidDay = "101385-111E";
        assertFalse(ssn.isValidSsn(invalidDay));

        invalidDay = "100085-111Y";
        assertFalse(ssn.isValidSsn(invalidDay));
    }

    public void testAllowedCenturyMarks() {
        String validSsn = "090385+2751";
        assertTrue(ssn.isValidSsn(validSsn));

        validSsn = "090385A2751";
        assertTrue(ssn.isValidSsn(validSsn));

        String invalidCentury = "090385B2751";
        assertFalse(ssn.isValidSsn(invalidCentury));
    }

}
