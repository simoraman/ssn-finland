package com.simoraman.ssnfinland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Hashtable;

public class SsnFinland
{
    public boolean isValidSsn(String ssn) {
        if(ssn == null || ssn.isEmpty()) return false;
        if(ssn.length() != 11) return false;
        if(!hasValidCentury(ssn)) return false;
        if(!hasValidDate(ssn)) return false;

        return extractCheckSum(ssn) == calculateCheckSum(ssn);
    }

    private boolean hasValidCentury(String ssn) {
        String whitelist = "+-A";
        char centuryMark = ssn.charAt(6);
        return whitelist.indexOf(centuryMark) >= 0;
    }

    private boolean hasValidDate(String ssn) {
        String datePart = ssn.substring(0,6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        try {
            LocalDate.parse(datePart, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private char calculateCheckSum(String ssn) {
        String birthDayPart = ssn.substring(0,6);
        String individualNumber = ssn.substring(7, 10);

        int ddmmyyzzz = Integer.parseInt(birthDayPart + individualNumber);

        return getModuloMap().get(ddmmyyzzz % 31);
    }

    private char extractCheckSum(String ssn) {
        int checkSumIndex = ssn.length() - 1;
        return ssn.charAt(checkSumIndex);
    }

    private static Hashtable<Integer, Character> getModuloMap() {
        Hashtable<Integer, Character> moduloTable = new Hashtable<Integer, Character>();
        moduloTable.put(0, '0');
        moduloTable.put(1, '1');
        moduloTable.put(2, '2');
        moduloTable.put(3, '3');
        moduloTable.put(4, '4');
        moduloTable.put(5, '5');
        moduloTable.put(6, '6');
        moduloTable.put(7, '7');
        moduloTable.put(8, '8');
        moduloTable.put(9, '9');
        moduloTable.put(10, 'A');
        moduloTable.put(11, 'B');
        moduloTable.put(12, 'C');
        moduloTable.put(13, 'D');
        moduloTable.put(14, 'E');
        moduloTable.put(15, 'F');
        moduloTable.put(16, 'H');
        moduloTable.put(17, 'J');
        moduloTable.put(18, 'K');
        moduloTable.put(19, 'L');
        moduloTable.put(20, 'M');
        moduloTable.put(21, 'N');
        moduloTable.put(22, 'P');
        moduloTable.put(23, 'R');
        moduloTable.put(24, 'S');
        moduloTable.put(25, 'T');
        moduloTable.put(26, 'U');
        moduloTable.put(27, 'V');
        moduloTable.put(28, 'W');
        moduloTable.put(29, 'X');
        moduloTable.put(30, 'Y');
        return moduloTable;
    }
}
