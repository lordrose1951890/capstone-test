package org.lordrose.capstonetest.domains.constants;

public enum ReviewPeriodStatus {

    UPCOMING("UPCOMING"),
    DUE("DUE"),
    OVERDUE("OVERDUE");

    public String textValue;

    ReviewPeriodStatus(String text) {
        textValue = text;
    }
}
