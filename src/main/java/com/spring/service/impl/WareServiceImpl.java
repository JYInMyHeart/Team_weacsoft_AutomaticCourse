package com.spring.service.impl;

import com.spring.dao.UserDao;
import com.spring.dao.WareDao;
import com.spring.entity.PageBean;
import com.spring.entity.User;
import com.spring.entity.Ware;
import com.spring.entity.WareVO;
import com.spring.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("WareService")
@Transactional
public class WareServiceImpl implements WareService {
    @Autowired
    WareDao wareDao;
    @Autowired
    UserDao userDao;

    @Override
    public void insertWare(Ware ware) {
        wareDao.insertSelective(ware);
    }

    @Override
    public void deleteWareById(String id) {
        userDao.updateUserByWareId(id);
        wareDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateWare(Ware ware) {
        wareDao.updateByPrimaryKeySelective(ware);
    }

    @Override
    public Ware selectWareById(String id) {
        return wareDao.selectWareById(id);
    }

    @Override
    public List<User> wareLeader(String id) {
        return wareDao.wareLeader(id);
    }

    @Override
    public List<String> selectWareByUserName(String username) {
        return wareDao.selectWareByUserName(username);
    }

    @Override
    public Ware selectWare(String id) {
        return wareDao.selectWare(id);
    }

    @Override
    public Ware selectWareByName(String name) {
        return wareDao.selectWareByName(name);
    }

    @Override
    public List<Ware> selectAllWare() {
        return wareDao.selectAllWare();
    }
    @Override
    public PageBean<WareVO> selectWareByPage(int size, int page, String sort, String asc,String keyWord) {
        HashMap<String,Object> map = new HashMap<>();
        PageBean<WareVO> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);

        //封装总记录数
        int totalCount = wareDao.countWareWithKey(keyWord);
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double num =Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);

        map.put("PageStart",(page-1)*size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);

        //封装每页显示的数据
        List<Ware> lists = wareDao.selectWareByPage(map);
        List<WareVO> wareVOList = lists.stream().map(x -> {
            WareVO wareVO = new WareVO();
            wareVO.setId(x.getId());
            wareVO.setArea(x.getArea());
            wareVO.setName(x.getName());
            wareVO.setRun_date(x.getRun_date());
            wareVO.setSite(x.getSite());
            wareVO.setWare_type(x.getWare_type());
            wareVO.setState(x.getState());
            String manageName = wareLeader(x.getId()).stream().map(User::getUsername)
                                           .collect(Collectors.joining(","));
            wareVO.setManageName(manageName);
            return wareVO;
        })
                                    .collect(Collectors.toList());


        pageBean.setLists(wareVOList);
        return pageBean;
    }

    @Override
    public int selectCountByWare() {
        return wareDao.selectCountByWare();
    }
}
