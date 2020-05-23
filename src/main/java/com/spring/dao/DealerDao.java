package com.spring.dao;

import com.spring.entity.Dealer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("DealerDao")
@Mapper
public interface DealerDao {
    void insertDealer(Dealer dealer);
    void deleteDealer(String id);
    void updateDealer(Dealer dealer);
    Dealer selectDealer(String id);
    Dealer selectDealerById(String id);
    List<Dealer> selectDealerByPage(HashMap<String, Object> map);
    int selectCountByDealer();

    Dealer selectDealerNyName(String name);
    int deleteByPrimaryKey(String id);

    int insert(Dealer record);

    int insertSelective(Dealer record);

    Dealer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dealer record);

    int updateByPrimaryKey(Dealer record);
}
