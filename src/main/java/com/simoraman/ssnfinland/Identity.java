package com.simoraman.ssnfinland;

public class Identity {
    public final boolean isValid;
    public final String sex;
    public final int age;

    public Identity(boolean isValid, String sex, int age) {
        this.isValid = isValid;
        this.sex = sex;
        this.age = age;
    }

}
