package com.spring.dao;

import com.spring.entity.Piece;
import com.spring.entity.PieceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("PieceDao")
@Mapper
public interface PieceDao {

    void deletePieceById(String id);

    void insertPiece(Piece piece);

    Piece selectPiece(String id);

    List<Piece> selectAllPiece();

    List<PieceVo> selectPieceByPage(HashMap<String, Object> map);

    int selectCountByPiece();

    void updatePiece(Piece piece);

    Piece selectPieceById(String id);

    int deleteByPrimaryKey(String id);

    int insert(Piece record);

    int insertSelective(Piece record);

    Piece selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Piece record);

    int updateByPrimaryKey(Piece record);

    List<PieceVo> selectBySupplier(HashMap<String, Object> map);

    List<PieceVo> selectByDealer(HashMap<String, Object> map);

    void deleteSupplier(String id);

    void addSupplier(@Param("id") String id, @Param("supplierId") String s);

    void deleteDealer(String id);

    void addDealer(@Param("id") String id, @Param("dealerId") String s);

    List<PieceVo> selectDealerByPieceId();

    List<PieceVo> selectSupplierByPieceId();
}
