package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link quanlykhuyenmai}.
 */
@Generated
public class quanlykhuyenmai__BeanDefinitions {
  /**
   * Get the bean definition for 'quanlykhuyenmai'.
   */
  public static BeanDefinition getQuanlykhuyenmaiBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(quanlykhuyenmai.class);
    beanDefinition.setInstanceSupplier(quanlykhuyenmai::new);
    return beanDefinition;
  }
}
