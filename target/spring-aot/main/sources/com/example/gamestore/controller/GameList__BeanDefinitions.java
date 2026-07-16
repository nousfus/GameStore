package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GameList}.
 */
@Generated
public class GameList__BeanDefinitions {
  /**
   * Get the bean definition for 'gameList'.
   */
  public static BeanDefinition getGameListBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GameList.class);
    InstanceSupplier<GameList> instanceSupplier = InstanceSupplier.using(GameList::new);
    instanceSupplier = instanceSupplier.andThen(GameList__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
