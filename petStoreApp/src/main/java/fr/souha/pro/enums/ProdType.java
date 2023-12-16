package fr.souha.pro.enums;

public enum ProdType {
    FOOD("Food"),
    ACCESSORY("Accessory"),
    CLEANING("Cleaning");

    private final String typeValue;

    ProdType(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public static ProdType fromString(String text) {
        for (ProdType prodType : ProdType.values()) {
            if (prodType.typeValue.equalsIgnoreCase(text)) {
                return prodType;
            }
        }
        return null;
    }
}