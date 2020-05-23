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

    void deleteWareById(String id);
    void insertWare(Ware ware);
    Ware selectWare(String id);
    List<Ware> selectAllWare();
    List<Ware> selectWareByPage(HashMap<String,Object> map);
    int selectCountByWare();
   void updateWare(Ware ware);
   Ware selectWareById(String id);
    int deleteByPrimaryKey(String id);

    int insert(Ware record);

    int insertSelective(Ware record);

    Ware selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Ware record);

    int updateByPrimaryKey(Ware record);

    List<User> wareLeader(String id);

    Ware selectWareByName(String name);

    List<String> selectWareByUserName(String username);

    int countWareWithKey(@Param("keyWord") String keyWord);
}
