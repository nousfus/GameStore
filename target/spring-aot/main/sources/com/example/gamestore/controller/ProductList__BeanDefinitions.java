package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ProductList}.
 */
@Generated
public class ProductList__BeanDefinitions {
  /**
   * Get the bean definition for 'productList'.
   */
  public static BeanDefinition getProductListBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProductList.class);
    beanDefinition.setInstanceSupplier(ProductList::new);
    return beanDefinition;
  }
}
