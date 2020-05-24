package com.spring.service.impl;

import com.spring.dao.DealerDao;
import com.spring.entity.Dealer;
import com.spring.entity.PageBean;
import com.spring.entity.Supplier;
import com.spring.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("DealerService")
@Transactional
public class DealerServiceImpl implements DealerService {
    @Autowired
    DealerDao dealerDao;

    @Override
    public void insertDealer(Dealer dealer) {
        dealerDao.insertSelective(dealer);
    }

    @Override
    public void deleteDealer(Integer id) {
         dealerDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateDealer(Dealer dealer) {
        dealerDao.updateByPrimaryKeySelective(dealer);
    }

    @Override
    public Dealer selectDealer(Integer id) {
        return dealerDao.selectDealer(id);
    }

    @Override
    public Dealer selectDealerById(Integer id) {
        return dealerDao.selectDealerById(id);
    }

    @Override
    public PageBean<Dealer> selectDealerByPage(int size, int page, String sort, String asc,String keyWord) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        PageBean<Dealer> pageBean = new PageBean<Dealer>();
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
        List<Dealer> lists = dealerDao.selectDealerByPage(map);
        //封装总记录数
        int totalCount = dealerDao.countDealerWithKey(keyWord);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double num =Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public int selectCountByDealer() {
        return dealerDao.selectCountByDealer();
    }

    @Override
    public Dealer selectDealerNyName(String s) {
        return dealerDao.selectDealerNyName(s);
    }
}
