package com.deepexi.ai.service.impl;

import com.deepexi.ai.mapper.UserMapper;
import com.deepexi.ai.domain.eo.User;
import com.deepexi.ai.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * TODO 如果连接数据库的话 删除(required=false)
     */
    @Autowired(required=false)
    UserMapper userMapper;

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public void insertBatch() {
        List<User> dataList = new ArrayList<>();
        User user1 = new User();
        user1.setName("a");
        User user2 = new User();
        user2.setName("a");
        User user3 = new User();
        user3.setName("a");
        dataList.add(user1);
        dataList.add(user2);
        dataList.add(user3);

        if (null != dataList && dataList.size() > 0) {
            //限制条数,【修改这里】
            int pointsDataLimit = 1000;
            Integer size = dataList.size();
            //判断是否有必要分批
            if (pointsDataLimit < size) {
                //分批数
                int part = size / pointsDataLimit;
                System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
                for (int i = 0; i < part; i++) {
                    List<User> listPage = dataList.subList(0, pointsDataLimit);
                    System.out.println("第" + (i + 1) + "次,执行处理:" + listPage);
                    //处理数据插入
                    userMapper.insertBatch(listPage);

                    dataList.subList(0, pointsDataLimit).clear();
                }
                if (!dataList.isEmpty()) {
                    //表示最后剩下的数据
                    System.out.println("最后一批数据,执行处理:" + dataList);
                    //处理数据插入
                    userMapper.insertBatch(dataList);
                }
            } else {
                System.out.println("不需要分批,执行处理:" + dataList);
                //处理数据插入
                userMapper.insertBatch(dataList);
            }
        } else {
            System.out.println("没有数据!!!");
        }
    }

    @Override
    public PageInfo<User> findUserPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        //这里是查询数据库搜索而来
        List<User> list = userMapper.findUserPage();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
