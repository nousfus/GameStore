package com.example.gamestore.dao;

import com.example.gamestore.entity.DeveloperProfiles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link DeveloperProfilesDao}.
 */
@Generated
public class DeveloperProfilesDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public DeveloperProfilesDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link DeveloperProfilesDao#findByUsername(java.lang.String)}.
   */
  public DeveloperProfiles findByUsername(String username) {
    String queryString = "SELECT d FROM DeveloperProfiles d WHERE d.username = :username";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);

    return (DeveloperProfiles) convertOne(query.getSingleResultOrNull(), false, DeveloperProfiles.class);
  }
}
