package com.example.gamestore.dao;

import com.example.gamestore.entity.OrderDetails;
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
 * AOT generated JPA repository implementation for {@link OrderDetailsDao}.
 */
@Generated
public class OrderDetailsDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public OrderDetailsDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link OrderDetailsDao#findByOrderID(java.lang.String)}.
   */
  public List<OrderDetails> findByOrderID(@Param("id") String id) {
    String queryString = "select o from OrderDetails o where o.order.order_id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return (List<OrderDetails>) query.getResultList();
  }
}
