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

    void deletePieceById(Integer id);

    void insertPiece(Piece piece);

    Piece selectPiece(Integer id);

    List<Piece> selectAllPiece();

    List<PieceVo> selectPieceByPage(HashMap<String, Object> map);

    int selectCountByPiece();

    void updatePiece(Piece piece);

    Piece selectPieceById(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Piece record);

    int insertSelective(Piece record);

    Piece selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Piece record);

    int updateByPrimaryKey(Piece record);

    List<PieceVo> selectBySupplier(HashMap<String, Object> map);

    List<PieceVo> selectByDealer(HashMap<String, Object> map);

    void deleteSupplier(Integer id);

    void addSupplier(@Param("id") Integer id, @Param("supplierId") Integer s);

    void deleteDealer(Integer id);

    void addDealer(@Param("id") Integer id, @Param("dealerId") Integer s);

    List<PieceVo> selectDealerByPieceId();

    List<PieceVo> selectSupplierByPieceId();

    Piece selectPieceByName(String piece_name);
}
