package com.michelbarbosa.hsdm_hearthstonedustmanager.data.network.response;

import java.util.List;

public class InfoResponse {

    private String patch;
    private List<String> classes;
    private List<String> sets;
    private List<String> standard;
    private List<String> wild;
    private List<String> types;
    private List<String> factions;
    private List<String> qualities;
    private List<String> races;
    private List<String> locales;

    public InfoResponse(String patch, List<String> classes, List<String> sets, List<String> standard,
                        List<String> wild, List<String> types, List<String> factions,
                        List<String> qualities, List<String> races, List<String> locales) {
        this.patch = patch;
        this.classes = classes;
        this.sets = sets;
        this.standard = standard;
        this.wild = wild;
        this.types = types;
        this.factions = factions;
        this.qualities = qualities;
        this.races = races;
        this.locales = locales;
    }

    public String getPatch() {
        return patch;
    }

    public List<String> getClasses() {
        return classes;
    }

    public List<String> getSets() {
        return sets;
    }

    public List<String> getStandard() {
        return standard;
    }

    public List<String> getWild() {
        return wild;
    }

    public List<String> getTypes() {
        return types;
    }

    public List<String> getFactions() {
        return factions;
    }

    public List<String> getQualities() {
        return qualities;
    }

    public List<String> getRaces() {
        return races;
    }

    public List<String> getLocales() {
        return locales;
    }
}
