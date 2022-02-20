package io.github.alen_alex.messageframework.enums;

public enum ActionType {

    TITLE("[TITLE]"),
    BOSS_BAR("[BOSSBAR]"),
    ACTION_BAR("[ACTIONBAR]"),
    DELAY("[DELAY]"),
    MESSAGE("[MESSAGE]"),
    BROADCAST("[BROADCAST]"),
    ;

    private String actionTag;

    ActionType(String actionTag) {
        this.actionTag = actionTag;
    }

    public String getActionTag() {
        return actionTag;
    }
}
