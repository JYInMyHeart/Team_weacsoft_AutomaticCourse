package com.spring.service;

import com.spring.entity.Dealer;
import com.spring.entity.PageBean;
import com.spring.entity.Supplier;

public interface SupplierService {
    void insertSupplier(Supplier supplier);
    void deleteSupplier(Integer id);
    void updateSupplier(Supplier supplier);
    Supplier selectSupplier(Integer id);
    Supplier selectSupplierById(Integer id);
    PageBean<Supplier> selectSupplierByPage(int size, int page, String sort, String asc,String keyWord);
    int selectCountBySupplier();


    Supplier selectSupplierByName(String s);
}
