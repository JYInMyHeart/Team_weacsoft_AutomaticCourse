package com.spring.service;

import com.spring.entity.PageBean;
import com.spring.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    User selectUserByIdentityId(String identityID);
//    List<User> selectAllUser();
//    User selectUserByUsername(String username);
//    User selectUserByOpenid(String openid);
//    void insertUser(User user);
      void insertWebUser(User user);
//    void insertWXUser(User user);
      void updateUserInfoByID(User user);
      void updateInfoByMyself(User user);
//      User selectUserById(String id);
      PageBean<User> selectUserByPage(int size, int page, String sort, String asc,String keyWord);
       int selectCountByUser();
      void deleteUSerByIdentityID(String identityID);
      void updateUser(User user);

    void setManageName(String manageName,int ware_id);

    Map<String,Integer> count();
}
