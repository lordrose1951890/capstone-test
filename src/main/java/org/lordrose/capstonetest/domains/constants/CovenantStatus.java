package org.lordrose.capstonetest.domains.constants;

public enum CovenantStatus {

    DRAFT("DRAFT"),
    ACTIVE("ACTIVE"),
    CEASED("CEASED");

    public String textValue;

    CovenantStatus(String text) {
        textValue = text;
    }
}
