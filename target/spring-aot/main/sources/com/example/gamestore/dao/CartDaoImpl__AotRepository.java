package com.example.gamestore.dao;

import com.example.gamestore.entity.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link CartDao}.
 */
@Generated
public class CartDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public CartDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link CartDao#findByUsername(java.lang.String)}.
   */
  public List<Cart> findByUsername(String username) {
    String queryString = "SELECT c FROM Cart c WHERE c.username = :username";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);

    return (List<Cart>) query.getResultList();
  }
}
