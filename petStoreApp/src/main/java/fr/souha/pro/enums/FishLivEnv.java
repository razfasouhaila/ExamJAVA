package fr.souha.pro.enums;

public enum FishLivEnv {
    FRESH_WATER("Fresh Water"),
    SEA_WATER("Sea Water");

    private final String fishLivValue;

    FishLivEnv(String fishLivValue) {
        this.fishLivValue = fishLivValue;
    }

    public String getFishLivValue() {
        return fishLivValue;
    }

    public static FishLivEnv fromString(String text) {
        for (FishLivEnv fishLivType : FishLivEnv.values()) {
            if (fishLivType.fishLivValue.equalsIgnoreCase(text)) {
                return fishLivType;
            }
        }
        return null;
    }
}
