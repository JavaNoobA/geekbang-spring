package org.geekbang.spring.bean.factory;

import org.geekbang.ioc.overview.domain.User;

/**
 * Created by eru on 2020/7/19.
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }
}
