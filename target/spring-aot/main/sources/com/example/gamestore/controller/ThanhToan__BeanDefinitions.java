package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ThanhToan}.
 */
@Generated
public class ThanhToan__BeanDefinitions {
  /**
   * Get the bean definition for 'thanhToan'.
   */
  public static BeanDefinition getThanhToanBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ThanhToan.class);
    InstanceSupplier<ThanhToan> instanceSupplier = InstanceSupplier.using(ThanhToan::new);
    instanceSupplier = instanceSupplier.andThen(ThanhToan__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
