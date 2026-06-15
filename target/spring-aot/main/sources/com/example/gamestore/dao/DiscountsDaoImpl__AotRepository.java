package com.example.gamestore.dao;

import com.example.gamestore.entity.Discounts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link DiscountsDao}.
 */
@Generated
public class DiscountsDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public DiscountsDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link DiscountsDao#findByGameId(java.lang.String)}.
   */
  public Discounts findByGameId(String game_id) {
    String queryString = "SELECT d FROM Discounts d WHERE d.gameId = :game_id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("game_id", game_id);

    return (Discounts) convertOne(query.getSingleResultOrNull(), false, Discounts.class);
  }
}
