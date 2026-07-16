package com.example.gamestore.dao;

import com.example.gamestore.entity.OrderDetails;
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
   * AOT generated implementation of {@link OrderDetailsDao#findByGameId(java.lang.String)}.
   */
  public List<OrderDetails> findByGameId(@Param("id") String id) {
    String queryString = "select o from OrderDetails o where o.game.game_id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return (List<OrderDetails>) query.getResultList();
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

  /**
   * AOT generated implementation of {@link OrderDetailsDao#revenueByDate(java.lang.String,java.time.LocalDate,java.time.LocalDate)}.
   */
  public List<Object[]> revenueByDate(@Param("developerId") String developerId,
      @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate) {
    String queryString = "SELECT\n"
            + "    CAST(o.order_date AS DATE) AS revenueDate,\n"
            + "    SUM(od.price - od.discount_amount) AS revenue\n"
            + "FROM Orders o\n"
            + "JOIN OrderDetails od ON o.order_id = od.order_id\n"
            + "JOIN Game g ON g.game_id = od.game_id\n"
            + "WHERE g.developer_id = :developerId\n"
            + "    AND o.status = 'Paid'\n"
            + "    AND o.order_date BETWEEN :startDate AND :endDate\n"
            + "GROUP BY CAST(o.order_date AS DATE)\n"
            + "ORDER BY revenueDate\n";
    Query query = this.entityManager.createNativeQuery(queryString);
    query.setParameter("developerId", developerId);
    query.setParameter("startDate", startDate);
    query.setParameter("endDate", endDate);

    return (List<Object[]>) convertMany(query.getResultList(), true, Object[].class);
  }
}
