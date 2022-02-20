package io.github.alen_alex.messageframework.enums;

public enum ActionType {

    TITLE("[TITLE]",false),
    BOSS_BAR("[BOSSBAR]",false),
    ACTION_BAR("[ACTIONBAR]",false),
    DELAY("[DELAY]",true),
    MESSAGE("[MESSAGE]",false),
    BROADCAST("[BROADCAST]",true),
    ;

    private String actionTag;
    private boolean isPlayerOptional;

    ActionType(String actionTag, boolean isPlayerOptional) {
        this.actionTag = actionTag;
        this.isPlayerOptional = isPlayerOptional;
    }

    public boolean isPlayerOptional() {
        return isPlayerOptional;
    }

    public String getActionTag() {
        return actionTag;
    }
}
