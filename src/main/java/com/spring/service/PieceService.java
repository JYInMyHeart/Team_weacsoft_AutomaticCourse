package com.spring.service;

import com.spring.entity.PageBean;
import com.spring.entity.Piece;
import com.spring.entity.PieceVo;
import com.spring.entity.User;

import java.util.List;

public interface PieceService {
    void deletePieceById(Integer id);

    void insertPiece(Piece piece);

    Piece selectPiece(Integer id);

    List<Piece> selectAllPiece();

    PageBean<PieceVo> selectPieceByPage(Integer size, Integer page, String sort, String asc, String keyWord, User user);

    int selectCountByPiece();

    void updatePiece(Piece piece);

    Piece selectPieceById(int id);

    PageBean<PieceVo> selectBySupplier(String supplierName,Integer size, Integer page, String sort, String asc,User user);

    PageBean<PieceVo> selectByDealer(String dealerName,Integer size, Integer page, String sort, String asc,User user);

    void deleteSupplier(int id);

    void addSupplier(int id, int sId);

    void deleteDealer(int id);

    void addDealer(int id, int dId );

    Piece selectPieceByName(String piece_name);
}
