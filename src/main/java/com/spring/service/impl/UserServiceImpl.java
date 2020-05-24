package com.spring.service.impl;

import com.spring.dao.DealerDao;
import com.spring.dao.PieceDao;
import com.spring.dao.SupplierDao;
import com.spring.dao.UserDao;
import com.spring.dao.WareDao;
import com.spring.entity.PageBean;
import com.spring.entity.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private WareDao wareDao;
    @Autowired
    private PieceDao pieceDao;
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private DealerDao dealerDao;

    @Override
    public User selectUserByIdentityId(String identityID) {

        return userDao.selectUserByIdentityId(identityID);
    }


    @Override
    public void insertWebUser(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public void updateUser(User user){
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void setManageName(String manageName,int ware_id) {
        userDao.setManageName(Arrays.stream(manageName.split(",")).collect(Collectors.toList()), ware_id);
    }

    @Override
    public Map<String, Integer> count() {
        Map<String,Integer> map = new HashMap<>();
        int countByPiece = pieceDao.selectCountByPiece();
        int countByUser = userDao.selectCountByUser();
        int countByWare = wareDao.selectCountByWare();
        int countBySupplier = supplierDao.selectCountBySupplier();
        int countByDealer = dealerDao.selectCountByDealer();
        map.put("user",countByUser);
        map.put("ware",countByWare);
        map.put("piece",countByPiece);
        map.put("supplier",countBySupplier);
        map.put("dealer",countByDealer);
        return map;
    }


    @Override
    public void updateUserInfoByID(User user) {
        userDao.updateUserInfoByID(user);
    }

    //
    @Override
    public void updateInfoByMyself(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }
//
//    @Override
//    public User selectUserById(String id) {
//        return userDao.selectUserById(id);
//    }

    @Override
    public PageBean<User> selectUserByPage(int size, int page, String sort, String asc,String keyWord) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<User> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);


        //封装总记录数
        int totalCount = userDao.countUserWithKey(keyWord);
        pageBean.setTotalCount(totalCount);
        //封装总页数
        double num = Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);

        map.put("PageStart", (page - 1) * size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);
        map.put("keyWord", keyWord == null ? "": keyWord);
        //封装每页显示的数据
        List<User> lists = userDao.selectUserByPage(map);
        pageBean.setLists(lists);
        return pageBean;
    }

    @Override
    public int selectCountByUser() {
        return userDao.selectCountByUser();
    }

    @Override
    public void deleteUSerByIdentityID(String identityID) {
        userDao.deleteUSerByIdentityID(identityID);
    }


}