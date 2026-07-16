package com.example.gamestore.dao;

import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Game;
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
 * AOT generated JPA repository implementation for {@link CartItemsDao}.
 */
@Generated
public class CartItemsDaoImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public CartItemsDaoImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link CartItemsDao#findByCartId(java.lang.String)}.
   */
  public List<CartItems> findByCartId(String cartId) {
    String queryString = "SELECT c FROM CartItems c WHERE c.cartId = :cartId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("cartId", cartId);

    return (List<CartItems>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link CartItemsDao#findByCartIdAndGame(java.lang.String,com.example.gamestore.entity.Game)}.
   */
  public CartItems findByCartIdAndGame(@Param("cartId") String cartId, @Param("game") Game game) {
    String queryString = "SELECT c\n"
            + "FROM CartItems c\n"
            + "WHERE c.cartId = :cartId\n"
            + "AND c.game = :game\n";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("cartId", cartId);
    query.setParameter("game", game);

    return (CartItems) convertOne(query.getSingleResultOrNull(), false, CartItems.class);
  }

  /**
   * AOT generated implementation of {@link CartItemsDao#findByGame(com.example.gamestore.entity.Game)}.
   */
  public CartItems findByGame(Game game) {
    String queryString = "SELECT c FROM CartItems c WHERE c.game = :game";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("game", game);

    return (CartItems) convertOne(query.getSingleResultOrNull(), false, CartItems.class);
  }
}
