package com.example.gamestore.dao;

import com.example.gamestore.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link UserDao}.
 */
@Generated
public class UserDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public UserDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link UserDao#findByEmail(java.lang.String)}.
   */
  public Users findByEmail(String email) {
    String queryString = "SELECT u FROM Users u WHERE u.email = :email";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("email", email);

    return (Users) convertOne(query.getSingleResultOrNull(), false, Users.class);
  }
}
