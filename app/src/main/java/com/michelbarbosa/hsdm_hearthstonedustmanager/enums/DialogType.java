package com.michelbarbosa.hsdm_hearthstonedustmanager.enums;

public enum DialogType {
    INFO, ALERT, ERROR;

    public static boolean isDialogType(String dialogType) {
        switch (dialogType) {
            case "INFO":
            case "ALERT":
            case "ERROR":
                return true;
            default:
                return false;
        }
    }
}