package com.spring.dao;

import com.spring.entity.Dealer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("DealerDao")
@Mapper
public interface DealerDao {
    void insertDealer(Dealer dealer);

    void deleteDealer(Integer id);

    void updateDealer(Dealer dealer);

    Dealer selectDealer(Integer id);

    Dealer selectDealerById(Integer id);

    List<Dealer> selectDealerByPage(HashMap<String, Object> map);

    int selectCountByDealer();

    Dealer selectDealerNyName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Dealer record);

    int insertSelective(Dealer record);

    Dealer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dealer record);

    int updateByPrimaryKey(Dealer record);

    int countDealerWithKey(@Param("keyWord")String keyWord);
}
