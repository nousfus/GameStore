package com.example.gamestore.dao;

import com.example.gamestore.entity.GameImages;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link GameImagesDao}.
 */
@Generated
public class GameImagesDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public GameImagesDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link GameImagesDao#thumbnail(java.lang.String)}.
   */
  public GameImages thumbnail(String id) {
    String queryString = "SELECT g FROM GameImages g WHERE g.gameId = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return (GameImages) convertOne(query.getSingleResultOrNull(), false, GameImages.class);
  }
}
