package com.example.gamestore;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GameStore1Application}.
 */
@Generated
public class GameStore1Application__BeanDefinitions {
  /**
   * Get the bean definition for 'gameStore1Application'.
   */
  public static BeanDefinition getGameStoreApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GameStore1Application.class);
    beanDefinition.setInstanceSupplier(GameStore1Application::new);
    return beanDefinition;
  }
}
