package com.spring.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spring.entity.PageBean;
import com.spring.entity.User;
import com.spring.entity.Ware;
import com.spring.entity.WareVO;
import com.spring.interceptor.LoginInterceptor;
import com.spring.service.UserService;
import com.spring.service.WareService;
import com.spring.util.JwtToken;
import com.spring.util.MyUtil;
import com.spring.util.ResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.spring.util.MyUtil.*;

@CrossOrigin
@Controller
@RequestMapping("/ware")
public class WareController {

    @Autowired
    private WareService wareService;
    @Autowired
    private UserService userService;


    /**
     * 添加仓库
     *
     * @param ware
     * @return
     * @throws IOException
     * @throws TokenExpiredException
     */
    @PostMapping("/web/insertWare")
    @ResponseBody
    public ResponseEntity insertWare(@RequestBody WareVO ware)
            throws IOException, TokenExpiredException, ParseException, InvocationTargetException,
                   IllegalAccessException {

        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        Ware w = new Ware();
        BeanUtils.copyProperties(ware,w);
        wareService.insertWare(w);
        Ware wareForId = wareService.selectWareByName(w.getName());
        userService.setManageName(ware.getManageName(),wareForId.getId());
        responseData.putDataValue("msg", "添加成功");
        return responseData;
    }

    /**
     * 仓库列表接口
     *
     * @param size
     * @param page
     * @param sort
     * @param asc
     * @param keyWord
     * @return
     * @throws IOException
     */
    @GetMapping("/web/ware_list")
    @ResponseBody
    public ResponseEntity selectWare(@RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                     @RequestParam(value = "sort", required = false,defaultValue = "id") String sort,
                                     @RequestParam(value = "keyWord", required = false) String keyWord,
                                     @RequestParam(value = "asc", required = false,defaultValue = "asc") String asc) throws IOException {

        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        String token = LoginInterceptor.globalToken;
        User user = JwtToken.unsign(token, User.class);
        PageBean<WareVO> pagemsg = wareService.selectWareByPage(size, page, sort, asc,keyWord,user);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("records", pagemsg);
        return responseData;


    }

    /**
     * 根据id查询仓库信息
     *
     * @return
     * @throws Exception
     * @Param id
     */
    @GetMapping("/web/wareLeader")
    @ResponseBody
    public ResponseEntity wareLeader(@RequestParam int id) throws Exception {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        List<User> list = wareService.wareLeader(id);
        responseData.putDataValue("wareLeader", list);
        return responseData;
    }

    /**
     * 根据id查询仓库信息
     *
     * @return
     * @throws Exception
     * @Param id
     */
    @GetMapping("/web/selectWareById")
    @ResponseBody
    public ResponseEntity selectWareById(@RequestParam int id) throws Exception {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        Ware wareFromBase = wareService.selectWareById(id);
        responseData.putDataValue("ware", wareFromBase);
        return responseData;
    }

    /**
     * 修改仓库信息
     *
     * @param ware
     * @return
     * @throws IOException
     */
    @PostMapping("/web/updateWare")
    @ResponseBody
    public ResponseEntity updateWareInfoByMyself(@RequestBody Ware ware) throws IOException, ParseException {

        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        responseData = ResponseEntity.ok();
        wareService.updateWare(ware);
        responseData.putDataValue("ware", ware);
        return responseData;
    }

    /**
     * 删除仓库
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/web/delete")
    @ResponseBody
    public ResponseEntity deleteWare(@RequestParam int id) throws IOException {
        ResponseEntity responseData = null;
        if (authority()) {
            responseData = ResponseEntity.badRequest();
            responseData.putDataValue("msg", "权限错误");
            responseData.putDataValue("timestamp", MyUtil.getTime());
            return responseData;
        }
        wareService.deleteWareById(id);
        responseData = ResponseEntity.ok();
        responseData.putDataValue("msg", "删除数据成功");
        return responseData;
    }
}
