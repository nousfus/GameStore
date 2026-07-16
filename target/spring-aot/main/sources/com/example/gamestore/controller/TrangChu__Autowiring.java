package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TrangChu}.
 */
@Generated
public class TrangChu__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TrangChu apply(RegisteredBean registeredBean, TrangChu instance) {
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.userdao = AutowiredFieldValueResolver.forRequiredField("userdao").resolve(registeredBean);
    instance.cartdao = AutowiredFieldValueResolver.forRequiredField("cartdao").resolve(registeredBean);
    instance.cartitemdao = AutowiredFieldValueResolver.forRequiredField("cartitemdao").resolve(registeredBean);
    instance.userroledao = AutowiredFieldValueResolver.forRequiredField("userroledao").resolve(registeredBean);
    instance.roledao = AutowiredFieldValueResolver.forRequiredField("roledao").resolve(registeredBean);
    instance.categorydao = AutowiredFieldValueResolver.forRequiredField("categorydao").resolve(registeredBean);
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.gamecategorydao = AutowiredFieldValueResolver.forRequiredField("gamecategorydao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("minioClient").resolveAndSet(registeredBean, instance);
    instance.minioService = AutowiredFieldValueResolver.forRequiredField("minioService").resolve(registeredBean);
    return instance;
  }
}
