package com.deepexi.ai.mapper;

import com.deepexi.ai.domain.eo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    List<User> findUserPage();

    User findById(@Param("id") int id);

    void insertBatch(@Param("list") List<User> list);
}
