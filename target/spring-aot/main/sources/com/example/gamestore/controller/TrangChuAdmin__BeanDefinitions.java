package com.example.gamestore.controller;

import com.example.gamestore.dao.CartDao;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TrangChuAdmin}.
 */
@Generated
public class TrangChuAdmin__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'trangChuAdmin'.
   */
  private static BeanInstanceSupplier<TrangChuAdmin> getTrangChuAdminInstanceSupplier() {
    return BeanInstanceSupplier.<TrangChuAdmin>forConstructor(CartDao.class)
            .withGenerator((registeredBean, args) -> new TrangChuAdmin(args.get(0)));
  }

  /**
   * Get the bean definition for 'trangChuAdmin'.
   */
  public static BeanDefinition getTrangChuAdminBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TrangChuAdmin.class);
    InstanceSupplier<TrangChuAdmin> instanceSupplier = getTrangChuAdminInstanceSupplier();
    instanceSupplier = instanceSupplier.andThen(TrangChuAdmin__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
