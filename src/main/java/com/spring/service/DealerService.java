package com.spring.service;

import com.spring.entity.Dealer;
import com.spring.entity.PageBean;

public interface DealerService {
    void insertDealer(Dealer dealer);
    void deleteDealer(Integer id);
    void updateDealer(Dealer dealer);
    Dealer selectDealer(Integer id);
    Dealer selectDealerById(Integer id);
    PageBean<Dealer> selectDealerByPage(int size, int page, String sort, String asc,String keyWord);
    int selectCountByDealer();

    Dealer selectDealerNyName(String s);
}
