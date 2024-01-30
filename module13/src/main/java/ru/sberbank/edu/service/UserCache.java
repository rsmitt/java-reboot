package ru.sberbank.edu.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserCache {

    private Map<String,UserInfo> cache = new HashMap<>();

    public UserInfo get(String id) {
        return cache.get(id);
    }
    private void create(UserInfo info) {
        cache.put(info.getLogin(), info);
    }

    @PostConstruct
    public void init() {
        create(new UserInfo("admin", "123456", "Dmitry Ivanov", "ADMIN"));
        create(new UserInfo("denis", "denis", "Denis Petrov", "MANAGER"));
    }
}
