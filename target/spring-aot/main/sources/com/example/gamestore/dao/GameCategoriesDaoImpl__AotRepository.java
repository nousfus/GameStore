package com.example.gamestore.dao;

import com.example.gamestore.entity.GameCategories;
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
 * AOT generated JPA repository implementation for {@link GameCategoriesDao}.
 */
@Generated
public class GameCategoriesDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public GameCategoriesDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link GameCategoriesDao#deleteByGameid(java.lang.String)}.
   */
  public void deleteByGameid(@Param("gameid") String gameid) {
    String queryString = "DELETE FROM GameCategories g WHERE g.gameid = :gameid";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("gameid", gameid);

    query.executeUpdate();
  }

  /**
   * AOT generated implementation of {@link GameCategoriesDao#findByGameid(java.lang.String)}.
   */
  public List<GameCategories> findByGameid(String gameid) {
    String queryString = "SELECT g FROM GameCategories g WHERE g.gameid = :gameid";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("gameid", gameid);

    return (List<GameCategories>) query.getResultList();
  }
}
