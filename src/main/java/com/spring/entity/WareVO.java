package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: alex
 * @File: WareVO
 * @Time: 9:38 2020/5/23
 */
public class WareVO implements Serializable {
    /**
     * 仓库id
     */
    private int id;

    /**
     * 地址
     */
    private String site;

    /**
     * 面积(平方米)
     */
    private String area;

    /**
     * 仓库类型(0:标准仓库,1:规划仓库,2:优质仓库)
     */
    private Integer ware_type;

    /**
     * 运营时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date run_date;

    /**
     * 说明
     */
    private String state;

    /**
     * 库房名称
     */
    private String name;

    /**
     * 管理员
     */
    private String manageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getWare_type() {
        return ware_type;
    }

    public void setWare_type(Integer ware_type) {
        this.ware_type = ware_type;
    }

    public Date getRun_date() {
        return run_date;
    }

    public void setRun_date(Date run_date) {
        this.run_date = run_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }
}
