package com.spring.dao;
import com.spring.entity.Dealer;
import com.spring.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

@Repository("SupplierDao")
@Mapper
public interface SupplierDao {
    void insertSupplier(Supplier supplier);

    void deleteSupplier(Integer id);

    void updateSupplier(Supplier supplier);

    Supplier selectSupplier(Integer id);

    Supplier selectSupplierById(Integer id);

    List<Supplier> selectSupplierByPage(HashMap<String, Object> map);

    int selectCountBySupplier();


    Supplier selectSupplierByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    int countSupplierWithKey(@Param("keyWord")String keyWord);
}
