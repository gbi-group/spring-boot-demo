/**
 * @Company:
 * @Author: lancer
 * @Date: 15-2-5 下午4:32
 * @Version: 1.0
 */
package com.spring.demo.controller;

import com.spring.demo.entity.UserInfo;
import com.spring.demo.entity.UserRole;
import com.spring.demo.service.UserRepository;
import com.spring.demo.service.UserRoleRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>DataController</b>
 * <p><b>详细说明：</b></p>
 * 在这里添加详细说明
 * <p><b>修改列表：</b></p>
 * <table width="" cellspacing=1 cellpadding=3 border=1>
 * <tr bgcolor="#CCCCFF"><td>序号</td><td>作者</td><td>修改日期</td><td>修改内容</td></tr>
 * <!--在此添加修改列表，参考第一行内容-->
 * <tr bgcolor="#CCCCFF"><td>1</td><td>lancer</td><td>15-2-5 下午4:32</td><td>新建内容</td></tr>
 */

@EnableAutoConfiguration
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepositoy roleRepositoy;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserInfo view(@PathVariable("id") int id) {
//        UserInfo ui = new UserInfo();
//        ui.setId(3);
//        ui.setUsername("zhang");
//        ui.setUserpass("zhang");
//        return ui;

        UserInfo ui = userRepository.findOne(id);
        return ui;
    }

    @RequestMapping(value="/list")
    List<UserRole> role() {
        List<UserRole> list = roleRepositoy.findAll();
        return list;
    }

    @RequestMapping(value="/nlist")
    ModelAndView list() {
        List<UserRole> list = roleRepositoy.findAll();
        return new ModelAndView("messages/list", "messages", list);
    }

}
