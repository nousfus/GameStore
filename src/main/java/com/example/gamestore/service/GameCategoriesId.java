package com.example.gamestore.service;

import java.io.Serializable;
import java.util.Objects;

public class GameCategoriesId implements Serializable {

    private String gameid;
    private String category_id;

    public GameCategoriesId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameCategoriesId)) return false;

        GameCategoriesId that = (GameCategoriesId) o;

        return Objects.equals(gameid, that.gameid)
            && Objects.equals(category_id, that.category_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameid, category_id);
    }
}