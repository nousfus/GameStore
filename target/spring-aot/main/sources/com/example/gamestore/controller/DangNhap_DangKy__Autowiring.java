package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link DangNhap_DangKy}.
 */
@Generated
public class DangNhap_DangKy__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static DangNhap_DangKy apply(RegisteredBean registeredBean, DangNhap_DangKy instance) {
    instance.udao = AutowiredFieldValueResolver.forRequiredField("udao").resolve(registeredBean);
    instance.userroledao = AutowiredFieldValueResolver.forRequiredField("userroledao").resolve(registeredBean);
    instance.roledao = AutowiredFieldValueResolver.forRequiredField("roledao").resolve(registeredBean);
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.cartdao = AutowiredFieldValueResolver.forRequiredField("cartdao").resolve(registeredBean);
    instance.devdao = AutowiredFieldValueResolver.forRequiredField("devdao").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("emailService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
