package com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain;

import com.michelbarbosa.hsdm_hearthstonedustmanager.enums.CardType;

public class TypeWeight {

    private int index;
    private CardType type;
    private int weight;

    public TypeWeight(int index, CardType type, int weight) {
        this.index = index;
        this.type = type;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public CardType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}


