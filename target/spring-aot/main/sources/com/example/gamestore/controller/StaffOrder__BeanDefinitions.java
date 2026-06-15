package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link StaffOrder}.
 */
@Generated
public class StaffOrder__BeanDefinitions {
  /**
   * Get the bean definition for 'staffOrder'.
   */
  public static BeanDefinition getStaffOrderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(StaffOrder.class);
    InstanceSupplier<StaffOrder> instanceSupplier = InstanceSupplier.using(StaffOrder::new);
    instanceSupplier = instanceSupplier.andThen(StaffOrder__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
