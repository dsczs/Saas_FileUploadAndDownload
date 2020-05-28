package com.deepexi.ai.service;

import com.deepexi.ai.domain.eo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    User findById(int id);

    void insertBatch();

    PageInfo<User> findUserPage(int page, int pageSize);
}
