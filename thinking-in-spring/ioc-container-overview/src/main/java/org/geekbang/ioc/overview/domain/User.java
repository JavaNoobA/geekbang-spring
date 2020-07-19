package org.geekbang.ioc.overview.domain;

/**
 * 用户类
 * Created by eru on 2020/7/19.
 */
public class User {
    private String name;

    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("erudev");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
