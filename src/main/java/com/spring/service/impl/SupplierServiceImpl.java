package com.spring.service.impl;

import com.spring.dao.SupplierDao;
import com.spring.entity.Dealer;
import com.spring.entity.PageBean;
import com.spring.entity.Supplier;
import com.spring.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("SupplierService")
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierDao supplierDao;
    @Override
    public void insertSupplier(Supplier supplier) {
        supplierDao.insertSelective(supplier);
    }

    @Override
    public void deleteSupplier(Integer id) {
        supplierDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierDao.updateByPrimaryKeySelective(supplier);
    }

    @Override
    public Supplier selectSupplier(Integer id) {
        return supplierDao.selectSupplier(id);
    }

    @Override
    public Supplier selectSupplierById(Integer id) {
        return supplierDao.selectSupplierById(id);
    }

    @Override
    public PageBean<Supplier> selectSupplierByPage(int size, int page, String sort, String asc,String keyWord) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Supplier> pageBean = new PageBean<Supplier>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);




        map.put("PageStart",(page-1)*size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);
        map.put("keyWord", keyWord == null ? "": keyWord);

        //封装每页显示的数据
        List<Supplier> lists = supplierDao.selectSupplierByPage(map);
        //封装总记录数
        int totalCount = supplierDao.countSupplierWithKey(keyWord);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double num =Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public int selectCountBySupplier() {
        return supplierDao.selectCountBySupplier();
    }

    @Override
    public Supplier selectSupplierByName(String s) {
        return supplierDao.selectSupplierByName(s);
    }


}
