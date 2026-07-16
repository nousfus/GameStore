package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DanhSachYeuThich}.
 */
@Generated
public class DanhSachYeuThich__BeanDefinitions {
  /**
   * Get the bean definition for 'danhSachYeuThich'.
   */
  public static BeanDefinition getDanhSachYeuThichBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DanhSachYeuThich.class);
    InstanceSupplier<DanhSachYeuThich> instanceSupplier = InstanceSupplier.using(DanhSachYeuThich::new);
    instanceSupplier = instanceSupplier.andThen(DanhSachYeuThich__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
