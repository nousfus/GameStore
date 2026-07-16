package com.example.gamestore.dao;

import com.example.gamestore.entity.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.function.LongSupplier;
import org.springframework.aot.generate.Generated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.DeclaredQuery;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.Param;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * AOT generated JPA repository implementation for {@link GameDao}.
 */
@Generated
public class GameDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public GameDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link GameDao#findAllGame(org.springframework.data.domain.Pageable)}.
   */
  public Page<Game> findAllGame(Pageable pageable) {
    String queryString = "SELECT g FROM Game g";
    String countQueryString = "SELECT count(g) FROM Game g";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Game.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Game>) query.getResultList(), pageable_1, countAll);
  }

  /**
   * AOT generated implementation of {@link GameDao#findByDeveloper_Developerid(java.lang.String)}.
   */
  public List<Game> findByDeveloper_Developerid(String id) {
    String queryString = "SELECT g FROM Game g WHERE g.developer.developerid = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return (List<Game>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link GameDao#findByGameNameContainingIgnoreCase(java.lang.String,org.springframework.data.domain.Pageable)}.
   */
  public Page<Game> findByGameNameContainingIgnoreCase(@Param("keyword") String keyword,
      Pageable pageable) {
    String queryString = "SELECT g FROM Game g WHERE LOWER(g.GameName) LIKE LOWER(CONCAT('%', :keyword, '%'))";
    String countQueryString = "SELECT count(g) FROM Game g WHERE LOWER(g.GameName) LIKE LOWER(CONCAT('%', :keyword, '%'))";
    Pageable pageable_1 = pageable != null ? pageable : Pageable.unpaged();
    if (pageable_1.getSort().isSorted()) {
      DeclaredQuery declaredQuery = DeclaredQuery.jpqlQuery(queryString);
      queryString = rewriteQuery(declaredQuery, pageable_1.getSort(), Game.class);
    }
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("keyword", keyword);
    if (pageable_1.isPaged()) {
      query.setFirstResult(Long.valueOf(pageable_1.getOffset()).intValue());
      query.setMaxResults(pageable_1.getPageSize());
    }
    LongSupplier countAll = () -> {
      Query countQuery = this.entityManager.createQuery(countQueryString);
      countQuery.setParameter("keyword", keyword);
      return getCount(countQuery);
    };

    return PageableExecutionUtils.getPage((List<Game>) query.getResultList(), pageable_1, countAll);
  }

  /**
   * AOT generated implementation of {@link GameDao#findMostExpensiveGames()}.
   */
  public List<Game> findMostExpensiveGames() {
    String queryString = "SELECT g FROM Game g WHERE g.price = (SELECT MAX(g2.price) FROM Game g2)";
    Query query = this.entityManager.createQuery(queryString);

    return (List<Game>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link GameDao#findTop3ByRating(int)}.
   */
  public List<Game> findTop3ByRating(int rating) {
    String queryString = "SELECT g FROM Game g WHERE g.rating = :rating";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("rating", rating);
    if (query.getMaxResults() != Integer.MAX_VALUE) {
      if (query.getMaxResults() > 3 && query.getFirstResult() > 0) {
        query.setFirstResult(query.getFirstResult() - (query.getMaxResults() - 3));
      }
    }
    query.setMaxResults(3);

    return (List<Game>) query.getResultList();
  }
}
