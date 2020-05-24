package com.spring.dao;

import com.spring.entity.User;
import com.spring.entity.Ware;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("WareDao")
@Mapper
public interface WareDao {

    void deleteWareById(Integer id);
    void insertWare(Ware ware);
    Ware selectWare(Integer id);
    List<Ware> selectAllWare();
    List<Ware> selectWareByPage(HashMap<String,Object> map);
    int selectCountByWare();
   void updateWare(Ware ware);
   Ware selectWareById(Integer id);
    int deleteByPrimaryKey(Integer id);

    int insert(Ware record);

    int insertSelective(Ware record);

    Ware selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ware record);

    int updateByPrimaryKey(Ware record);

    List<User> wareLeader(Integer id);

    Ware selectWareByName(String name);

    List<String> selectWareByUserName(String username);

    int countWareWithKey(@Param("keyWord") String keyWord,@Param("username") String username,@Param("userRole") Integer userRole);
}
