package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link demo}.
 */
@Generated
public class demo__BeanDefinitions {
  /**
   * Get the bean definition for 'demo'.
   */
  public static BeanDefinition getDemoBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(demo.class);
    beanDefinition.setInstanceSupplier(demo::new);
    return beanDefinition;
  }
}
