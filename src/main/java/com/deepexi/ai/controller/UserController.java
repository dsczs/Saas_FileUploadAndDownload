package com.deepexi.ai.controller;

import com.deepexi.ai.domain.eo.User;
import com.deepexi.ai.service.UserService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/findUserById",method = RequestMethod.GET)
    @ResponseBody
    public Payload findUserById(int id){
        return new Payload(userService.findById(id));
    }

    /**
     * 分页
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findUserPage",method = RequestMethod.GET)
    @ResponseBody
    public Payload<PageInfo<User>> findUserPage(int page, int pageSize){
        return new Payload<>(userService.findUserPage(page,pageSize));
    }

    /**
     * 批量插入数据
     */
    @RequestMapping(value = "/insertBatch",method = RequestMethod.GET)
    @ResponseBody
    public Payload insertBatch(){
        userService.insertBatch();
        return new Payload();
    }
}
