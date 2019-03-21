package br.com.domain;

public enum HeaderColumEnum {
    NAME("NAME"),
    LATITUDE("LATITUDE"),
    LONGITUDE("LONGITUDE");

    private String value;

    HeaderColumEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
