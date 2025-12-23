package com.api.framework.model;

public class PlayerGetByPlayerIdRequest {

    public Integer playerId;

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public PlayerGetByPlayerIdRequest(Integer playerId) {
        this.playerId = playerId;
    }
}
