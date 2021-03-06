package com.simoraman.ssnfinland;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Hashtable;
import java.util.Random;

public class SsnFinland {
    public static boolean isValidSsn(String ssn) {
        if (ssn == null || ssn.isEmpty()) return false;
        if (ssn.length() != 11) return false;
        if (!hasValidCentury(extractCenturyMark(ssn))) return false;
        if (!hasValidDate(extractDatePart(ssn))) return false;

        return extractCheckSum(ssn) == calculateCheckSum(extractDatePart(ssn), extractIndividualNumber(ssn));
    }

    public static Identity parse(String ssn) {
        boolean isValid = isValidSsn(ssn);
        if (isValid) {
            int sexBit = Integer.parseInt(ssn.substring(9, 10));
            String sex = sexBit % 2 == 0 ? "female" : "male";
            LocalDate birthDate = parseDate(ssn);
            int age = calculateAge(birthDate);
            return new Identity(true, sex, age);
        }
        return new Identity(false, null, 0);
    }

    public static String generateWithAge(int age) {
        LocalDate birthDate = LocalDate.now().minusYears(age);
        String birthDatePart = birthDate.format(DateTimeFormatter.ofPattern("ddMMyy"));
        String individualNumberPart = generateIndividualNumber();
        char checkSum = calculateCheckSum(birthDatePart, individualNumberPart);
        String centuryMark = "-";
        if (birthDate.getYear() > 1999) {
            centuryMark = "A";
        } else if (birthDate.getYear() < 1900) {
            centuryMark = "+";
        }
        return birthDatePart + centuryMark + individualNumberPart + checkSum;
    }

    private static String generateIndividualNumber() {
        int randomIndividualNumber = new Random().nextInt(897) + 2;
        return String.format("%03d", randomIndividualNumber);
    }

    private static LocalDate parseDate(String ssn) {
        char centuryMark = extractCenturyMark(ssn);
        int century = 1900;
        if (centuryMark == '+') {
            century = 1800;
        } else if (centuryMark == 'A') {
            century = 2000;
        }
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("ddMM")
                .appendValueReduced(ChronoField.YEAR, 2, 2, century)
                .toFormatter();

        String datePart = extractDatePart(ssn);
        LocalDate parsedDate = LocalDate.parse(datePart, formatter);
        return parsedDate;
    }

    private static int calculateAge(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        return Period.between(birthDate, now).getYears();
    }

    private static boolean hasValidCentury(char centuryMark) {
        String whitelist = "+-A";
        return whitelist.indexOf(centuryMark) >= 0;
    }

    private static char extractCenturyMark(String ssn) {
        return ssn.charAt(6);
    }

    private static boolean hasValidDate(String datePart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        try {
            LocalDate.parse(datePart, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static String extractDatePart(String ssn) {
        return ssn.substring(0, 6);
    }

    private static char calculateCheckSum(String birthDayPart, String individualNumber) {
        int ddmmyyzzz = Integer.parseInt(birthDayPart + individualNumber);
        return getModuloMap().get(ddmmyyzzz % 31);
    }

    private static String extractIndividualNumber(String ssn) {
        return ssn.substring(7, 10);
    }

    private static char extractCheckSum(String ssn) {
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
