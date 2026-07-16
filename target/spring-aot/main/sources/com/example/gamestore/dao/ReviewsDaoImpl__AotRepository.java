package com.example.gamestore.dao;

import com.example.gamestore.entity.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link ReviewsDao}.
 */
@Generated
public class ReviewsDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ReviewsDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ReviewsDao#findByGameid(java.lang.String)}.
   */
  public List<Reviews> findByGameid(String gameid) {
    String queryString = "SELECT r FROM Reviews r WHERE r.gameid = :gameid";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("gameid", gameid);

    return (List<Reviews>) query.getResultList();
  }
}
