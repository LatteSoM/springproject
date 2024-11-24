package ru.mpt.springproject.component;

import ru.mpt.springproject.entity.User;

public interface AccountProvider {

    User findByUsername(String username);
}
