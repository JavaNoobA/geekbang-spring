package org.geekbang.dependency.injection;

import org.geekbang.ioc.overview.domain.User;

/**
 * {@link org.geekbang.ioc.overview.domain.User} holder 类
 * Created by eru on 2020/7/21.
 */
public class UserHolder {

    private User user;

    public UserHolder(){

    }

    public UserHolder(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
