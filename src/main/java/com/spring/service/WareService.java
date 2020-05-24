package com.spring.service;

import com.spring.entity.PageBean;
import com.spring.entity.User;
import com.spring.entity.Ware;
import com.spring.entity.WareVO;

import java.util.List;

public interface WareService {

    void deleteWareById(int id);
    void insertWare(Ware ware);
    Ware selectWare(int id);
    Ware selectWareByName(String name);
    List<Ware> selectAllWare();
    PageBean<WareVO> selectWareByPage(int size, int page, String sort, String asc,String keyWord,User user);
    int selectCountByWare();
    void updateWare(Ware ware);
    Ware selectWareById(int id);

    List<User> wareLeader(int id);

    List<String> selectWareByUserName(String username);
}
