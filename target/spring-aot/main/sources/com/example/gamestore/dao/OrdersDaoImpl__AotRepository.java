package com.example.gamestore.dao;

import com.example.gamestore.entity.Orders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Object;
import java.lang.String;
import java.time.LocalDate;
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
   * AOT generated implementation of {@link OrdersDao#findByDate(java.time.LocalDate,java.time.LocalDate)}.
   */
  public List<Orders> findByDate(@Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate) {
    String queryString = "select o from Orders o where order_date between :startDate and :endDate";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);

    return (List<Orders>) query.getResultList();
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

  /**
   * AOT generated implementation of {@link OrdersDao#revenueByDate(java.time.LocalDate,java.time.LocalDate)}.
   */
  public List<Object[]> revenueByDate(@Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate) {
    String queryString = "SELECT\n"
            + "    order_date AS revenueDate,\n"
            + "    SUM(total_amount) AS revenue\n"
            + "FROM Orders\n"
            + "WHERE status = 'Paid'\n"
            + "  AND order_date BETWEEN :startDate AND :endDate\n"
            + "GROUP BY order_date\n"
            + "ORDER BY order_date\n";
    Query query = this.entityManager.createNativeQuery(queryString);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);

    return (List<Object[]>) convertMany(query.getResultList(), true, Object[].class);
  }
}
