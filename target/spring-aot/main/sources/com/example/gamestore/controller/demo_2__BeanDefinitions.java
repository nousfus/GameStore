package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link demo_2}.
 */
@Generated
public class demo_2__BeanDefinitions {
  /**
   * Get the bean definition for 'demo_2'.
   */
  public static BeanDefinition getDemoBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(demo_2.class);
    InstanceSupplier<demo_2> instanceSupplier = InstanceSupplier.using(demo_2::new);
    instanceSupplier = instanceSupplier.andThen(demo_2__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
