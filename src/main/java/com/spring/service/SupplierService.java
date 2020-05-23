package com.spring.service;

import com.spring.entity.Dealer;
import com.spring.entity.PageBean;
import com.spring.entity.Supplier;

public interface SupplierService {
    void insertSupplier(Supplier supplier);
    void deleteSupplier(String id);
    void updateSupplier(Supplier supplier);
    Supplier selectSupplier(String id);
    Supplier selectSupplierById(String id);
    PageBean<Supplier> selectSupplierByPage(int size, int page, String sort, String asc);
    int selectCountBySupplier();


    Supplier selectSupplierByName(String s);
}
