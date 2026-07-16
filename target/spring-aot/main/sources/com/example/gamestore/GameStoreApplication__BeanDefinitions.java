package com.example.gamestore;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GameStoreApplication}.
 */
@Generated
public class GameStoreApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'gameStoreApplication'.
   */
  public static BeanDefinition getGameStoreApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GameStoreApplication.class);
    beanDefinition.setInstanceSupplier(GameStoreApplication::new);
    return beanDefinition;
  }
}
