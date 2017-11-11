package com.simoraman.ssnfinland;

import junit.framework.TestCase;

public class SsnParseTest extends TestCase {

    public SsnParseTest(String testName) {
        super(testName);
    }

    public void testParseValidMale() {
        String validMaleSsn = "090385-2751";
        Identity i = SsnFinland.parse(validMaleSsn);
        assertTrue(i.isValid);
        assertEquals("male", i.sex);
    }

    public void testParseValidFemale() {
        String validFemalSsn = "070585-848U";
        Identity i = SsnFinland.parse(validFemalSsn);
        assertTrue(i.isValid);
        assertEquals("female", i.sex);
    }

    public void testParseAge() {
        String femaleAged32 = "070585-848U";
        Identity i = SsnFinland.parse(femaleAged32);
        assertEquals(32, i.age);
    }

    public void testParseAge19thCentury() {
        String femaleAged32 = "040699+8092";
        Identity i = SsnFinland.parse(femaleAged32);
        assertEquals(118, i.age);
    }

    public void testParseAge21stCentury() {
        String femaleAged32 = "020500A080W";
        Identity i = SsnFinland.parse(femaleAged32);
        assertEquals(17, i.age);
    }

    public void testParsingInvalidSsnReturnsNullObject() {
        String invalidSsn = null;
        Identity i = SsnFinland.parse(invalidSsn);
        assertEquals(false, i.isValid);
    }

}
