package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link Quanlykhuyenmai}.
 */
@Generated
public class Quanlykhuyenmai__BeanDefinitions {
  /**
   * Get the bean definition for 'quanlykhuyenmai'.
   */
  public static BeanDefinition getQuanlykhuyenmaiBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Quanlykhuyenmai.class);
    InstanceSupplier<Quanlykhuyenmai> instanceSupplier = InstanceSupplier.using(Quanlykhuyenmai::new);
    instanceSupplier = instanceSupplier.andThen(Quanlykhuyenmai__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
