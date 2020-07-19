package org.geekbang.spring.bean.factory;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by eru on 2020/7/19.
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
