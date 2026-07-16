package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TrangChuStaff}.
 */
@Generated
public class TrangChuStaff__BeanDefinitions {
  /**
   * Get the bean definition for 'trangChuStaff'.
   */
  public static BeanDefinition getTrangChuStaffBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TrangChuStaff.class);
    InstanceSupplier<TrangChuStaff> instanceSupplier = InstanceSupplier.using(TrangChuStaff::new);
    instanceSupplier = instanceSupplier.andThen(TrangChuStaff__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
