package com.spring.service;

import com.spring.entity.Dealer;
import com.spring.entity.PageBean;

public interface DealerService {
    void insertDealer(Dealer dealer);
    void deleteDealer(String id);
    void updateDealer(Dealer dealer);
    Dealer selectDealer(String id);
    Dealer selectDealerById(String id);
    PageBean<Dealer> selectDealerByPage(int size, int page, String sort, String asc);
    int selectCountByDealer();

    Dealer selectDealerNyName(String s);
}
