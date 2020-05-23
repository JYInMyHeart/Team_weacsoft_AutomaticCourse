package com.spring.service.impl;

import com.spring.dao.PieceDao;
import com.spring.dao.WareDao;
import com.spring.entity.PageBean;
import com.spring.entity.Piece;
import com.spring.entity.PieceVo;
import com.spring.service.PieceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("PieceService")
@Transactional
public class PieceServiceImpl implements PieceService {
    @Autowired
    PieceDao pieceDao;
    @Autowired
    WareDao wareDao;

    @Override
    public void deletePieceById(String id) {
        pieceDao.deleteDealer(id);
        pieceDao.deleteSupplier(id);
        pieceDao.deletePieceById(id);
    }

    @Override
    public void insertPiece(Piece piece) {
        pieceDao.insertSelective(piece);
    }

    @Override
    public Piece selectPiece(String id) {
        return pieceDao.selectPiece(id);
    }

    @Override
    public List<Piece> selectAllPiece() {
        return pieceDao.selectAllPiece();
    }

    @Override
    public PageBean<PieceVo> selectPieceByPage(int size, int page, String sort, String asc) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<PieceVo> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);

        //封装总记录数
        int totalCount = pieceDao.selectCountByPiece();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double num = Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);


        map.put("PageStart", (page - 1) * size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);

        //封装每页显示的数据
        List<PieceVo> pieceVoList = pieceDao.selectPieceByPage(map);
        List<PieceVo> pieceVoList1 = pieceDao.selectDealerByPieceId();
        List<PieceVo> pieceVoList2 = pieceDao.selectSupplierByPieceId();
        pieceVoList.forEach(p -> {
            if (CollectionUtils.isNotEmpty(pieceVoList1)) {
                PieceVo dealer =
                        pieceVoList1.stream().filter(y -> y.getId().equals(p.getId())).findFirst().orElse(null);
                if (dealer != null) {
                    String dealer_name = dealer.getDealer_name();
                    p.setDealer_name(dealer_name);
                }
            }
            if (CollectionUtils.isNotEmpty(pieceVoList2)) {
                PieceVo supplier =
                        pieceVoList2.stream().filter(y -> y.getId().equals(p.getId())).findFirst().orElse(null);
                if (supplier != null) {
                    String supplier_name = supplier.getSupplier_name();
                    p.setSupplier_name(supplier_name);
                }
            }
        });
        pageBean.setLists(pieceVoList);
        return pageBean;
    }

    @Override
    public int selectCountByPiece() {
        return pieceDao.selectCountByPiece();
    }

    @Override
    public void updatePiece(Piece piece) {
        pieceDao.updateByPrimaryKeySelective(piece);
    }

    @Override
    public Piece selectPieceById(String id) {
        return pieceDao.selectPieceById(id);
    }

    @Override
    public PageBean<PieceVo> selectBySupplier(String supplierName, int size, int page, String sort, String asc) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<PieceVo> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);


        map.put("PageStart", (page - 1) * size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);
        map.put("supplierName", supplierName);
        List<PieceVo> pieceVos = pieceDao.selectBySupplier(map);
        //封装总记录数
        int totalCount = pieceVos.size();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double num = Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);
        pageBean.setLists(pieceVos);
        return pageBean;
    }

    @Override
    public PageBean<PieceVo> selectByDealer(String dealerName, int size, int page, String sort, String asc) {
        HashMap<String, Object> map = new HashMap<>();
        PageBean<PieceVo> pageBean = new PageBean<>();
        //封装当前页数
        pageBean.setCurrPage(page);
        //封装页数大小
        pageBean.setPageSize(size);
        //封装排序字段
        pageBean.setPageSort(sort);
        //封装排序规则
        pageBean.setPageAsc(asc);


        map.put("PageStart", (page - 1) * size);
        map.put("PageSize", pageBean.getPageSize());
        map.put("PageSort", sort);
        map.put("PageAsc", asc);
        map.put("dealerName", dealerName);
        List<PieceVo> pieceVos = pieceDao.selectByDealer(map);
        //封装总记录数
        int totalCount = pieceVos.size();
        pageBean.setTotalCount(totalCount);

        //封装总页数
        double num = Math.ceil((double) totalCount / size);//向上取整
        pageBean.setTotalPage((int) num);
        pageBean.setLists(pieceVos);
        return pageBean;
    }

    @Override
    public void deleteSupplier(String id) {
        pieceDao.deleteSupplier(id);
    }

    @Override
    public void addSupplier(String id, String s) {
        pieceDao.addSupplier(id, s);
    }

    @Override
    public void deleteDealer(String id) {
        pieceDao.deleteDealer(id);
    }

    @Override
    public void addDealer(String id, String s) {
        pieceDao.addDealer(id, s);
    }
}
