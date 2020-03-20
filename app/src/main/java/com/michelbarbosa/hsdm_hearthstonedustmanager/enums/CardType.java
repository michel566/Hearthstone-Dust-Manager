package com.michelbarbosa.hsdm_hearthstonedustmanager.enums;

public enum CardType {
    BASIC,
    NEUTRAL,
    CLASS;

    public static CardType toCardType(String dialogType) {
        CardType type;
        switch (dialogType) {
            case "BASIC": type = BASIC; break;
            case "NEUTRAL":  type = NEUTRAL; break;
            case "CLASS":  type = CLASS; break;
            default:
                throw new IllegalStateException("Unexpected value: " + dialogType);
        }
        return type;
    }

}
