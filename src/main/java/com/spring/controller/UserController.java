package com.spring.controller;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.entity.PageBean;
import com.spring.entity.User;
import com.spring.interceptor.LoginInterceptor;
import com.spring.service.UserService;
import com.spring.util.JwtToken;
import com.spring.util.MyUtil;
import com.spring.util.ResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.stream.Collectors;

import static com.spring.util.MyUtil.*;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 网页登陆接口
     *
     * @param identityID
     * @param password
     * @param httpSession
     * @return
     * @throws UnsupportedEncodingException
     * @throws JsonProcessingException
     */
    @PostMapping("/web/login")
    @ResponseBody
    public ResponseEntity login(@RequestParam String identityID,
                                @RequestParam String password, HttpSession httpSession)
            throws UnsupportedEncodingException, JsonProcessingException {
        ResponseEntity responseEntity = ResponseEntity.ok();
        User userFromBase = userService.selectUserByIdentityId(identityID);
        if (userFromBase.getIdentityID().equals(identityID) &&
            userFromBase.getPassword().equals(password)) {
            //给用户jwt加密生成token
            String token = JwtToken.sign(userFromBase, 60L * 1000L * 30L);//sign()方法是static修饰的，所以直接用类名调用，不需要创建对象
            //封装成对象返回给客户端
            responseEntity.putDataValue("userId", userFromBase
                    .getIdentityID());//调用ResponseEntity类的putDataValue方法将userID和token放进map中然后返回
            responseEntity.putDataValue("token", token);
            responseEntity.putDataValue("userRole", userFromBase.getAuthority());
            /* BS架构中,客户端与服务器一连接在服务端就会自动创建一来个session对象.session.setAttribute("username",username);
这句话的意思就是自说,当客户端执行了某个操作后(刚一登陆,或其他操作)服务百端就会在session对象中存储一个名称为username的参数这个相当于hashMap,
"username" 相当于key username就是key对应的值(但注意这个值度必须是一个对象知).这样以后你可以通过session.getAttribute("username")的方法获得这个对象.
比如说,当用户道已登录系统后你就在session中存储了一个用户信息对象,此后你可以随时从session中将这个对象取出来进行一些操作,比如进行身份验证等等.
             */
        } else {
            responseEntity = ResponseEntity.customerError();
            responseEntity.putDataValue("msg", "账号密码错误");
        }
        return responseEntity;
    }

    /**
     * web端注册用户
     *
     * @param addUser
     * @return
     * @throws IOException
     * @throws TokenExpiredException
     */
    @PostMapping("/web/register")
    @ResponseBody
    public ResponseEntity register(@RequestBody User addUser) throws IOException, TokenExpiredException {
        ResponseEntity responseEntity = null;
        if (authority()) {
            responseEntity = ResponseEntity.badRequest();
            responseEntity.putDataValue("msg", "权限错误");
            responseEntity.putDataValue("timestamp", MyUtil.getTime());
            return responseEntity;
        }

        User userFromBase = userService.selectUserByIdentityId(addUser.getIdentityID());
        if (userFromBase == null) {
            responseEntity = ResponseEntity.ok();
            addUser.setTime(MyUtil.getStringID());
            userService.insertWebUser(addUser);
            responseEntity.putDataValue("msg", "注册成功");
        } else {
            responseEntity = ResponseEntity.badRequest();
            responseEntity.putDataValue("msg", "注册失败");
        }
        return responseEntity;
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/web/userInfo")
    @ResponseBody
    public ResponseEntity preHandle() throws Exception {
        ResponseEntity responseData;
        responseData = ResponseEntity.ok();
        User user = JwtToken.unsign(LoginInterceptor.globalToken,User.class);
        //解密token后的userId与用户传来的userId不一致，大多是因为token过期
        if (user != null) {
            responseData.putDataValue("user", user);
        }
        return responseData;
    }

    /**
     * 根据identityID查询用户信息
     *
     * @param identityID
     * @return
     * @throws Exception
     */
    @GetMapping("/web/selectUserByIdentityID")
    @ResponseBody
    public ResponseEntity selectUserByIdentityID(@RequestParam(value = "identityID") String identityID)
            throws Exception {
        ResponseEntity responseData;
        //token不存在
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        User userFromBase = userService.selectUserByIdentityId(identityID);
        responseData.putDataValue("user", userFromBase);
        return responseData;

    }


    /**
     * 根据identityID修改用户密码
     *
     * @param password
     * @param identityID
     * @return
     */
    @PostMapping("/web/update_info_id")
    @ResponseBody
    public ResponseEntity updateUserInfoById(@RequestParam(required = false) String password,
                                             @RequestParam(required = false) String identityID) throws IOException {

        ResponseEntity responseData;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        //通过查询id来判断此用户是否存在
        User user1 = userService.selectUserByIdentityId(identityID);
        if (user1 == null) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "没有该用户");
        } else {
            responseData = ResponseEntity.ok();
            user1.setPassword(password);
            //修改信息
            userService.updateUserInfoByID(user1);
            responseData.putDataValue("password", user1.getPassword());
            System.out.println("密码修改成功");
        }

        return responseData;


    }

    //    /**
//     * 修改自己信息接口
//     * @param password
//     * @param avatar
//     * @param phone
//     * @param nickName
//     * @param identityID
//     * @param username
//     * @param Authorization
//     * @param response
//     * @return
//     * @throws IOException
//     */
    @PostMapping("/web/update_info")
    @ResponseBody
    public ResponseEntity updateUserInfoByMyself(@RequestBody User updateUser) throws IOException {

        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        userService.updateInfoByMyself(updateUser);
        responseData.putDataValue("identityId", updateUser.getIdentityID());
        return responseData;
    }

    /**
     * 删除用户
     *
     * @param identityID
     * @return
     * @throws IOException
     */
    @GetMapping("/web/delete")
    @ResponseBody
    public ResponseEntity deleteUser(@RequestParam(required = true) String identityID) throws IOException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        userService.deleteUSerByIdentityID(identityID);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("msg", "删除数据成功");
        return responseData;
    }

    /**
     * 用户列表接口
     *
     * @param size
     * @param page
     * @param sort
     * @param asc
     * @return
     * @throws IOException
     */
    @GetMapping("/web/user_list")
    @ResponseBody
    public ResponseEntity selectUser(@RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                     @RequestParam(value = "sort", required = false) String sort,
                                     @RequestParam(value = "keyWord", required = false) String keyWord,
                                     @RequestParam(value = "asc", required = false) String asc
    ) throws IOException {
        ResponseEntity responseData;
        PageBean<User> pagemsg = userService.selectUserByPage(size, page, sort, asc);
        if (StringUtils.isNotBlank(keyWord)) pagemsg.setLists(
                pagemsg.getLists().stream().filter(x -> x.getUsername().contains(keyWord))
                       .collect(Collectors.toList()));
        responseData = ResponseEntity.ok();
        responseData.putDataValue("records", pagemsg);
        return responseData;
    }




    @GetMapping("/web/count")
    @ResponseBody
    public ResponseEntity count() throws IOException {
        ResponseEntity responseData;
        Map<String, Integer> count = userService.count();
        responseData = ResponseEntity.ok();
        responseData.putDataValue("count", count);
        return responseData;
    }



}
