package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link UserCart}.
 */
@Generated
public class UserCart__BeanDefinitions {
  /**
   * Get the bean definition for 'userCart'.
   */
  public static BeanDefinition getUserCartBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserCart.class);
    InstanceSupplier<UserCart> instanceSupplier = InstanceSupplier.using(UserCart::new);
    instanceSupplier = instanceSupplier.andThen(UserCart__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
