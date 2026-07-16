package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TrangChuDeveloper}.
 */
@Generated
public class TrangChuDeveloper__BeanDefinitions {
  /**
   * Get the bean definition for 'trangChuDeveloper'.
   */
  public static BeanDefinition getTrangChuDeveloperBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TrangChuDeveloper.class);
    InstanceSupplier<TrangChuDeveloper> instanceSupplier = InstanceSupplier.using(TrangChuDeveloper::new);
    instanceSupplier = instanceSupplier.andThen(TrangChuDeveloper__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
