package com.example.gamestore.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameCategoriesId implements Serializable {

    private String game_id;
    private String category_id;

    public GameCategoriesId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameCategoriesId)) return false;

        GameCategoriesId that = (GameCategoriesId) o;

        return Objects.equals(game_id, that.game_id)
            && Objects.equals(category_id, that.category_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game_id, category_id);
    }
}