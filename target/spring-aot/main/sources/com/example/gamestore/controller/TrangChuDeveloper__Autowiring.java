package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TrangChuDeveloper}.
 */
@Generated
public class TrangChuDeveloper__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TrangChuDeveloper apply(RegisteredBean registeredBean, TrangChuDeveloper instance) {
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    instance.developerdao = AutowiredFieldValueResolver.forRequiredField("developerdao").resolve(registeredBean);
    instance.categorydao = AutowiredFieldValueResolver.forRequiredField("categorydao").resolve(registeredBean);
    instance.userroledao = AutowiredFieldValueResolver.forRequiredField("userroledao").resolve(registeredBean);
    instance.roledao = AutowiredFieldValueResolver.forRequiredField("roledao").resolve(registeredBean);
    instance.gamecategorydao = AutowiredFieldValueResolver.forRequiredField("gamecategorydao").resolve(registeredBean);
    instance.gameimagedao = AutowiredFieldValueResolver.forRequiredField("gameimagedao").resolve(registeredBean);
    instance.orderdao = AutowiredFieldValueResolver.forRequiredField("orderdao").resolve(registeredBean);
    instance.orderdetaildao = AutowiredFieldValueResolver.forRequiredField("orderdetaildao").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("minioClient").resolveAndSet(registeredBean, instance);
    instance.minioService = AutowiredFieldValueResolver.forRequiredField("minioService").resolve(registeredBean);
    return instance;
  }
}
