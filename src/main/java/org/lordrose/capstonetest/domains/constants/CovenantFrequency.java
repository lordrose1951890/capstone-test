package org.lordrose.capstonetest.domains.constants;

public enum CovenantFrequency {

    MONTHLY("MONTHLY"),
    QUARTERLY("QUARTERLY"),
    ANNUALLY("ANNUALLY");

    public String textValue;

    CovenantFrequency(String text) {
        textValue = text;
    }
}
