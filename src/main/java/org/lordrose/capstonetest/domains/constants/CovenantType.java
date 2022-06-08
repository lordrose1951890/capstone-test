package org.lordrose.capstonetest.domains.constants;

public enum CovenantType {

    REPORTING("REPORTING"),
    FINANCIAL("FINANCIAL");

    public String textValue;

    CovenantType(String text) {
        textValue = text;
    }
}
