package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link Quanlykhuyenmai}.
 */
@Generated
public class Quanlykhuyenmai__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static Quanlykhuyenmai apply(RegisteredBean registeredBean, Quanlykhuyenmai instance) {
    instance.discountdao = AutowiredFieldValueResolver.forRequiredField("discountdao").resolve(registeredBean);
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    return instance;
  }
}
