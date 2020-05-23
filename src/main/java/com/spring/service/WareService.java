package com.spring.service;

import com.spring.entity.PageBean;
import com.spring.entity.User;
import com.spring.entity.Ware;
import com.spring.entity.WareVO;

import java.util.List;

public interface WareService {

    void deleteWareById(String id);
    void insertWare(Ware ware);
    Ware selectWare(String id);
    Ware selectWareByName(String name);
    List<Ware> selectAllWare();
    PageBean<WareVO> selectWareByPage(int size, int page, String sort, String asc);
    int selectCountByWare();
    void updateWare(Ware ware);
    Ware selectWareById(String id);

    List<User> wareLeader(String id);

    List<String> selectWareByUserName(String username);
}
