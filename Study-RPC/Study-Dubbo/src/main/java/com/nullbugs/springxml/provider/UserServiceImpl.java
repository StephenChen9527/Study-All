package com.nullbugs.springxml.provider;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService{
    @Override
    public List<String> getUser() {
        List<String> user = new ArrayList<>();
        user.add("1");
        user.add("2");
        return user;
    }
}
