package com.example.gamestore.controller;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link GameAdd}.
 */
@Generated
public class GameAdd__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static GameAdd apply(RegisteredBean registeredBean, GameAdd instance) {
    instance.gamedao = AutowiredFieldValueResolver.forRequiredField("gamedao").resolve(registeredBean);
    instance.gameversiondao = AutowiredFieldValueResolver.forRequiredField("gameversiondao").resolve(registeredBean);
    instance.gamerequirementdao = AutowiredFieldValueResolver.forRequiredField("gamerequirementdao").resolve(registeredBean);
    instance.gameimagedao = AutowiredFieldValueResolver.forRequiredField("gameimagedao").resolve(registeredBean);
    instance.gamecategorydao = AutowiredFieldValueResolver.forRequiredField("gamecategorydao").resolve(registeredBean);
    instance.categorydao = AutowiredFieldValueResolver.forRequiredField("categorydao").resolve(registeredBean);
    return instance;
  }
}
