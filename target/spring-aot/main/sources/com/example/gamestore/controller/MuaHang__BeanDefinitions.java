package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link MuaHang}.
 */
@Generated
public class MuaHang__BeanDefinitions {
  /**
   * Get the bean definition for 'muaHang'.
   */
  public static BeanDefinition getMuaHangBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MuaHang.class);
    InstanceSupplier<MuaHang> instanceSupplier = InstanceSupplier.using(MuaHang::new);
    instanceSupplier = instanceSupplier.andThen(MuaHang__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
