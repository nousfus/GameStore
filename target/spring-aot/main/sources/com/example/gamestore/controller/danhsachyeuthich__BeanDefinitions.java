package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link danhsachyeuthich}.
 */
@Generated
public class danhsachyeuthich__BeanDefinitions {
  /**
   * Get the bean definition for 'danhsachyeuthich'.
   */
  public static BeanDefinition getDanhsachyeuthichBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(danhsachyeuthich.class);
    beanDefinition.setInstanceSupplier(danhsachyeuthich::new);
    return beanDefinition;
  }
}
