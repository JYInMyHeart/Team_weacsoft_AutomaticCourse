package com.spring.service;

import com.spring.entity.PageBean;
import com.spring.entity.Piece;
import com.spring.entity.PieceVo;

import java.util.List;

public interface PieceService {
    void deletePieceById(String id);

    void insertPiece(Piece piece);

    Piece selectPiece(String id);

    List<Piece> selectAllPiece();

    PageBean<PieceVo> selectPieceByPage(int size, int page, String sort, String asc);

    int selectCountByPiece();

    void updatePiece(Piece piece);

    Piece selectPieceById(String id);

    PageBean<PieceVo> selectBySupplier(String supplierName,int size, int page, String sort, String asc);

    PageBean<PieceVo> selectByDealer(String dealerName,int size, int page, String sort, String asc);

    void deleteSupplier(String id);

    void addSupplier(String id, String s);

    void deleteDealer(String id);

    void addDealer(String id, String s);
}
