package com.example.gamestore.dao;

import com.example.gamestore.entity.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link UserRolesDao}.
 */
@Generated
public class UserRolesDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public UserRolesDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link UserRolesDao#findByUsername(java.lang.String)}.
   */
  public List<Roles> findByUsername(String username) {
    String queryString = "    SELECT r\n"
            + "    FROM Roles r\n"
            + "    WHERE r.role_id IN (\n"
            + "        SELECT u.role_id\n"
            + "        FROM UserRoles u\n"
            + "        WHERE u.username = :username\n"
            + "    )\n";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);

    return (List<Roles>) convertMany(query.getResultList(), false, Roles.class);
  }
}
