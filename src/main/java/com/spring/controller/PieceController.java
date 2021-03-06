package com.spring.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spring.entity.Dealer;
import com.spring.entity.PageBean;
import com.spring.entity.Piece;
import com.spring.entity.PieceVo;
import com.spring.entity.Supplier;
import com.spring.entity.User;
import com.spring.interceptor.LoginInterceptor;
import com.spring.service.DealerService;
import com.spring.service.PieceService;
import com.spring.service.SupplierService;
import com.spring.service.WareService;
import com.spring.util.JwtToken;
import com.spring.util.MyUtil;
import com.spring.util.ResponseEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.spring.util.MyUtil.*;

@CrossOrigin
@Controller
@RequestMapping("/piece")
public class PieceController {

    @Autowired
    PieceService pieceService;
    @Autowired
    WareService wareService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    DealerService dealerService;

    /**
     * 添加零件
     *
     * @param pieceVo
     * @return
     * @throws IOException
     * @throws TokenExpiredException
     */
    @PostMapping("/web/insert")
    @ResponseBody
    public ResponseEntity insertWare(@RequestBody PieceVo pieceVo)
            throws IOException, TokenExpiredException, ParseException {

        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }

        responseData = ResponseEntity.ok();
        Piece piece = new Piece();
        BeanUtils.copyProperties(pieceVo, piece);
        int wareId = wareService.selectWareByName(pieceVo.getWare_name()).getId();
        piece.setWare_id(wareId);
        pieceService.insertPiece(piece);
        Piece p = pieceService.selectPieceByName(piece.getPiece_name());
        pieceVo.setId(p.getId());
        updateSupplierAndDealer(pieceVo);
        responseData.putDataValue("msg", "添加成功");
        return responseData;
    }

    /**
     * 零件列表接口
     *
     * @param size
     * @param page
     * @param sort
     * @param keyWord
     * @param asc
     * @return
     * @throws IOException
     */
    @GetMapping("/web/piece_list")
    @ResponseBody
    public ResponseEntity selectPiece(@RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
                                      @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                                      @RequestParam(value = "keyWord", required = false) String keyWord)
            throws IOException {
        ResponseEntity responseData;
        String token = LoginInterceptor.globalToken;
        User user = JwtToken.unsign(token, User.class);
        PageBean<PieceVo> pagemsg = pieceService.selectPieceByPage(size, page, sort, asc, keyWord, user);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("records", pagemsg);
        return responseData;
    }

    /**
     * 根据id查询零件信息
     *
     * @return
     * @throws Exception
     * @Param id
     */
    @GetMapping("/web/selectById")
    @ResponseBody
    public ResponseEntity selectPieceById(@RequestParam int id) throws Exception {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        Piece pieceFromBase = pieceService.selectPieceById(id);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("piece", pieceFromBase);
        return responseData;
    }

    /**
     * 根据供应商查询零件信息
     *
     * @return
     * @throws Exception
     * @Param id
     */
    @GetMapping("/web/selectBySupplier")
    @ResponseBody
    public ResponseEntity selectBySupplier(@RequestParam String supplierName,
                                           @RequestParam(value = "size", required = false, defaultValue = "10")
                                                   int size,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "sort", required = false, defaultValue = "id")
                                                   String sort,
                                           @RequestParam(value = "asc", required = false, defaultValue = "asc")
                                                   String asc) throws Exception {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        String token = LoginInterceptor.globalToken;
        User user = JwtToken.unsign(token, User.class);
        PageBean<PieceVo> piece = pieceService.selectBySupplier(supplierName, size, page, sort, asc, user);
        responseData.putDataValue("records", piece);
        return responseData;
    }

    /**
     * 根据供应商查询零件信息
     *
     * @param dealerName
     * @return
     * @throws Exception
     */
    @GetMapping("/web/selectByDealer")
    @ResponseBody
    public ResponseEntity selectByDealer(@RequestParam String dealerName,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "sort", required = false, defaultValue = "id")
                                                 String sort,
                                         @RequestParam(value = "asc", required = false, defaultValue = "asc")
                                                 String asc) throws Exception {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        String token = LoginInterceptor.globalToken;
        User user = JwtToken.unsign(token, User.class);
        PageBean<PieceVo> piece = pieceService.selectByDealer(dealerName, size, page, sort, asc, user);
        responseData.putDataValue("records", piece);
        return responseData;
    }

    /**
     * 修改零件信息
     *
     * @param pieceVo
     * @return
     * @throws IOException
     */
    @PostMapping("/web/update")
    @ResponseBody
    public ResponseEntity updatePiece(@RequestBody PieceVo pieceVo) throws IOException, ParseException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        Piece piece = new Piece();
        BeanUtils.copyProperties(pieceVo, piece);
        int wareId = wareService.selectWareByName(pieceVo.getWare_name()).getId();
        updateSupplierAndDealer(pieceVo);
        piece.setWare_id(wareId);
        //修改信息
        pieceService.updatePiece(piece);
        responseData.putDataValue("id", piece.getId());
        return responseData;
    }

    private void updateSupplierAndDealer(@RequestBody PieceVo pieceVo) {
        pieceService.deleteSupplier(pieceVo.getId());
        Arrays.stream(pieceVo.getSupplier_name().split(",")).filter(StringUtils::isNotBlank).forEach(s -> {
            Supplier supplier = supplierService.selectSupplierByName(s);
            pieceService.addSupplier(pieceVo.getId(), supplier.getId());
        });
        pieceService.deleteDealer(pieceVo.getId());
        Arrays.stream(pieceVo.getDealer_name().split(",")).filter(StringUtils::isNotBlank).forEach(s -> {
            Dealer dealer = dealerService.selectDealerNyName(s);
            pieceService.addDealer(pieceVo.getId(), dealer.getId());
        });
    }

    /**
     * 删除零件
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/web/delete")
    @ResponseBody
    public ResponseEntity deletePiece(@RequestParam(required = false) int id) throws IOException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        pieceService.deletePieceById(id);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("msg", "删除数据成功");
        return responseData;
    }
}
