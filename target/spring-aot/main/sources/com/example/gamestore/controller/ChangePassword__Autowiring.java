package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ChangePassword}.
 */
@Generated
public class ChangePassword__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ChangePassword apply(RegisteredBean registeredBean, ChangePassword instance) {
    instance.userdao = AutowiredFieldValueResolver.forRequiredField("userdao").resolve(registeredBean);
    instance.session = AutowiredFieldValueResolver.forRequiredField("session").resolve(registeredBean);
    AutowiredFieldValueResolver.forRequiredField("emailService").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
