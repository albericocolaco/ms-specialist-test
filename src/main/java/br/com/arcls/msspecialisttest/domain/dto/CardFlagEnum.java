package br.com.arcls.msspecialisttest.domain.dto;

public enum CardFlagEnum {
    VISA,
    MASTERCARD,
    ELO,
    AMERICANEXPRESS,
    NA;

    public static CardFlagEnum fromString(String text) {
        for (CardFlagEnum cfe : CardFlagEnum.values()) {
            if (cfe.toString().equalsIgnoreCase(text)) {
                return cfe;
            }
        }
        return NA;
    }

}
