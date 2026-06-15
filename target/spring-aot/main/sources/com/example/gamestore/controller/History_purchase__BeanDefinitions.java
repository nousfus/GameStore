package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link History_purchase}.
 */
@Generated
public class History_purchase__BeanDefinitions {
  /**
   * Get the bean definition for 'history_purchase'.
   */
  public static BeanDefinition getHistorypurchaseBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(History_purchase.class);
    InstanceSupplier<History_purchase> instanceSupplier = InstanceSupplier.using(History_purchase::new);
    instanceSupplier = instanceSupplier.andThen(History_purchase__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
