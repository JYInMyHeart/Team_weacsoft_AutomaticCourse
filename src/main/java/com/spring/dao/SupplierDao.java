package com.spring.dao;
import com.spring.entity.Dealer;
import com.spring.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
@Repository("SupplierDao")
@Mapper
public interface SupplierDao {
    void insertSupplier(Supplier supplier);
    void deleteSupplier(String id);
    void updateSupplier(Supplier supplier);
    Supplier selectSupplier(String id);
    Supplier selectSupplierById(String id);
    List<Supplier> selectSupplierByPage(HashMap<String, Object> map);
    int selectCountBySupplier();


    Supplier selectSupplierByName(String name);

    int deleteByPrimaryKey(String id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}
