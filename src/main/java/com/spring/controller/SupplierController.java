package com.spring.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.entity.PageBean;
import com.spring.entity.Supplier;
import com.spring.entity.User;
import com.spring.service.SupplierService;
import com.spring.util.JwtToken;
import com.spring.util.MyUtil;
import com.spring.util.ResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.spring.util.MyUtil.*;

@CrossOrigin
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    /**
     * 添加供应商
     *
     * @param supplier
     * @return
     * @throws IOException
     * @throws TokenExpiredException
     */
    @PostMapping("/web/insert")
    @ResponseBody
    public ResponseEntity insertSupplier(@RequestBody Supplier supplier)
            throws IOException, TokenExpiredException, ParseException {

        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }

        responseData = ResponseEntity.ok();
        supplierService.insertSupplier(supplier);
        responseData.putDataValue("msg", "添加成功");
        return responseData;
    }

    /**
     * 供应商列表接口
     *
     * @param size
     * @param page
     * @param sort
     * @param asc
     * @param keyWord
     * @return
     * @throws IOException
     */
    @GetMapping("/web/supplier_list")
    @ResponseBody
    public ResponseEntity selectSupplier(@RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
                                         @RequestParam(value = "asc", required = false, defaultValue = "asc") String asc,
                                         @RequestParam(value = "keyWord", required = false) String keyWord)
            throws IOException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        PageBean<Supplier> pagemsg = supplierService.selectSupplierByPage(size, page, sort, asc,keyWord);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("records", pagemsg);
        return responseData;

    }

    /**
     * 根据id查询供应商信息
     *
     * @return
     * @throws Exception
     * @Param id
     */
    @GetMapping("/web/selectById")
    @ResponseBody
    public ResponseEntity selectSupplierById(@RequestParam int id) throws Exception {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        Supplier supplierFromBase = supplierService.selectSupplierById(id);
        responseData.putDataValue("supplier", supplierFromBase);
        return responseData;
    }

    /**
     * 修改供应商信息
     *
     * @param supplier
     * @return
     * @throws IOException
     */
    @PostMapping("/web/update")
    @ResponseBody
    public ResponseEntity updateSupplier(@RequestBody Supplier supplier) throws IOException, ParseException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        //修改信息
        supplierService.updateSupplier(supplier);
        return responseData;
    }

    /**
     * 删除供应商
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/web/delete")
    @ResponseBody
    public ResponseEntity deleteSupplier(@RequestParam int id) throws IOException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        supplierService.deleteSupplier(id);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("msg", "删除数据成功");
        return responseData;
    }
}
