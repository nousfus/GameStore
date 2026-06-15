package com.example.gamestore.dao;

import com.example.gamestore.entity.Orders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.Param;

/**
 * AOT generated JPA repository implementation for {@link OrdersDao}.
 */
@Generated
public class OrdersDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public OrdersDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link OrdersDao#findByUsername(java.lang.String)}.
   */
  public List<Orders> findByUsername(String username) {
    String queryString = "SELECT o FROM Orders o WHERE o.username = :username";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);

    return (List<Orders>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link OrdersDao#findByUsernameAndStatus(java.lang.String,java.lang.String)}.
   */
  public List<Orders> findByUsernameAndStatus(@Param("username") String username,
      @Param("status") String status) {
    String queryString = "select o from Orders o where o.username = :username and o.status = :status";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);
    query.setParameter("status", status);

    return (List<Orders>) query.getResultList();
  }
}
