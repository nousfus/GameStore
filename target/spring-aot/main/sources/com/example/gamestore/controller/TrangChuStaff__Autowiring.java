package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TrangChuStaff}.
 */
@Generated
public class TrangChuStaff__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TrangChuStaff apply(RegisteredBean registeredBean, TrangChuStaff instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.userroledao = AutowiredFieldValueResolver.forRequiredField("userroledao").resolve(registeredBean);
    instance.roledao = AutowiredFieldValueResolver.forRequiredField("roledao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    instance.paymentdao = AutowiredFieldValueResolver.forRequiredField("paymentdao").resolve(registeredBean);
    instance.notidao = AutowiredFieldValueResolver.forRequiredField("notidao").resolve(registeredBean);
    return instance;
  }
}
