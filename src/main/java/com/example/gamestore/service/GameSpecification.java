package com.example.gamestore.service;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.GameCategories;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class GameSpecification {

    public static Specification<Game> hasRam(String ram) {
        return (root, query, cb) -> {
            if (ram == null || ram.isEmpty()) {
                return null;
            }
            return cb.equal(root.get("ram"), ram);
        };
    }

    public static Specification<Game> hasStorage(String storage) {
        return (root, query, cb) -> {
            if (storage == null || storage.isEmpty()) {
                return null;
            }
            return cb.equal(root.get("storage"), storage);
        };
    }

    public static Specification<Game> priceLessThan(Double price) {
        return (root, query, cb) -> {
            if (price == null) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get("price"), price);
        };
    }
    public static Specification<Game> hasCategory(List<String> categories) {

        return (root, query, cb) -> {

            if(categories == null || categories.isEmpty()) {
                return cb.conjunction();
            }

            Subquery<String> subquery =
                    query.subquery(String.class);

            Root<GameCategories> gc =
                    subquery.from(GameCategories.class);

            subquery.select(gc.get("gameid"))
                    .where(gc.get("category_id").in(categories));

            return root.get("game_id").in(subquery);
        };
    }
    public static Specification<Game> sortBy(String sort) {
        return (root, query, cb) -> {

            if (sort == null || sort.isEmpty()) {
                return cb.conjunction();
            }

            switch (sort) {
                case "newest":
                    query.orderBy(cb.desc(root.get("release_date")));
                    break;

                case "oldest":
                    query.orderBy(cb.asc(root.get("release_date")));
                    break;

                case "priceAsc":
                    query.orderBy(cb.asc(root.get("price")));
                    break;
 
                case "priceDesc":
                    query.orderBy(cb.desc(root.get("price")));
                    break;

                case "rating":
                    query.orderBy(cb.desc(root.get("rating")));
                    break;
            }

            return cb.conjunction();
        };
    }
}