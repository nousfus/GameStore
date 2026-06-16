package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GameAdd}.
 */
@Generated
public class GameAdd__BeanDefinitions {
  /**
   * Get the bean definition for 'gameAdd'.
   */
  public static BeanDefinition getGameAddBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GameAdd.class);
    InstanceSupplier<GameAdd> instanceSupplier = InstanceSupplier.using(GameAdd::new);
    instanceSupplier = instanceSupplier.andThen(GameAdd__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
