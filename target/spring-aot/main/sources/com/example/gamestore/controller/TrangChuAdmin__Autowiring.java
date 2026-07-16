package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TrangChuAdmin}.
 */
@Generated
public class TrangChuAdmin__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TrangChuAdmin apply(RegisteredBean registeredBean, TrangChuAdmin instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.userroledao = AutowiredFieldValueResolver.forRequiredField("userroledao").resolve(registeredBean);
    instance.roledao = AutowiredFieldValueResolver.forRequiredField("roledao").resolve(registeredBean);
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    return instance;
  }
}
