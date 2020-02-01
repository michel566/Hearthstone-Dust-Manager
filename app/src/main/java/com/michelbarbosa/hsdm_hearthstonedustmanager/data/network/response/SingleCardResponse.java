package com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response;

public class SingleCardResponse {

    private String cardId;
    private String name;
    private String cardSet;
    private String type;
    private String faction;
    private String rarity;
    private String cost;
    private String attack;
    private String health;
    private String text;
    private String flavor;
    private String artist;
    private String collectible;
    private String elite;
    private String race;
    private String img;
    private String imgGold;
    private String locale;

    public SingleCardResponse(String cardId, String name, String cardSet, String type, String faction, String rarity, String cost, String attack, String health, String text, String flavor, String artist, String collectible, String elite, String race, String img, String imgGold, String locale) {
        this.cardId = cardId;
        this.name = name;
        this.cardSet = cardSet;
        this.type = type;
        this.faction = faction;
        this.rarity = rarity;
        this.cost = cost;
        this.attack = attack;
        this.health = health;
        this.text = text;
        this.flavor = flavor;
        this.artist = artist;
        this.collectible = collectible;
        this.elite = elite;
        this.race = race;
        this.img = img;
        this.imgGold = imgGold;
        this.locale = locale;
    }

    public String getCardId() {
        return cardId;
    }

    public String getName() {
        return name;
    }

    public String getCardSet() {
        return cardSet;
    }

    public String getType() {
        return type;
    }

    public String getFaction() {
        return faction;
    }

    public String getRarity() {
        return rarity;
    }

    public String getCost() {
        return cost;
    }

    public String getAttack() {
        return attack;
    }

    public String getHealth() {
        return health;
    }

    public String getText() {
        return text;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getArtist() {
        return artist;
    }

    public String getCollectible() {
        return collectible;
    }

    public String getElite() {
        return elite;
    }

    public String getRace() {
        return race;
    }

    public String getImg() {
        return img;
    }

    public String getImgGold() {
        return imgGold;
    }

    public String getLocale() {
        return locale;
    }
}
