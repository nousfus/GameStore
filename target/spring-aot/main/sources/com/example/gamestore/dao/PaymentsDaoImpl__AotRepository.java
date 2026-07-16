package com.example.gamestore.dao;

import com.example.gamestore.entity.Payments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link PaymentsDao}.
 */
@Generated
public class PaymentsDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public PaymentsDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link PaymentsDao#findByOrderid(java.lang.String)}.
   */
  public Payments findByOrderid(String orderid) {
    String queryString = "SELECT p FROM Payments p WHERE p.orderid = :orderid";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("orderid", orderid);

    return (Payments) convertOne(query.getSingleResultOrNull(), false, Payments.class);
  }
}
