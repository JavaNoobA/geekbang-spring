package org.geekbang.ioc.overview.dependency.domain;

import org.geekbang.ioc.overview.dependency.annotation.Super;

/**
 * Created by eru on 2020/7/19.
 */
@Super
public class SuperUser extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
