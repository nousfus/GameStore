package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DangNhap_DangKy}.
 */
@Generated
public class DangNhap_DangKy__BeanDefinitions {
  /**
   * Get the bean definition for 'dangNhap_DangKy'.
   */
  public static BeanDefinition getDangNhapDangKyBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DangNhap_DangKy.class);
    InstanceSupplier<DangNhap_DangKy> instanceSupplier = InstanceSupplier.using(DangNhap_DangKy::new);
    instanceSupplier = instanceSupplier.andThen(DangNhap_DangKy__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
