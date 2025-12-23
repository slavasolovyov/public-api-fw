package com.api.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerDeleteRequest {

    @JsonProperty("playerId")
    private Integer playerId;

    public PlayerDeleteRequest(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}


