package com.spring.dao;

import com.spring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("UserDao")
@Mapper
public interface UserDao {

    User selectUserByIdentityId(String identityID);

    void updateUserInfoByID(User user);

    void updateInfoByMyself(User user);

    List<User> selectUserByPage(HashMap<String, Object> map);

    int selectCountByUser();

    void deleteUSerByIdentityID(String identityID);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void setManageName(@Param("manageName") List<String> manageName,@Param("ware_id") String ware_id);
}