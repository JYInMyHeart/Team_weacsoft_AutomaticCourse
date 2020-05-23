package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: alex
 * @File: PieceVo
 * @Time: 11:06 2020/5/23
 */
public class PieceVo implements Serializable {
    private String id;
    private String piece_name;
    private String scc;
    private String help_code;
    private String price;
    private int inventory;
    private String state;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date run_date;
    private String ware_name;
    private String supplier_name;
    private String dealer_name;

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getDealer_name() {
        return dealer_name;
    }

    public void setDealer_name(String dealer_name) {
        this.dealer_name = dealer_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPiece_name() {
        return piece_name;
    }

    public void setPiece_name(String piece_name) {
        this.piece_name = piece_name;
    }

    public String getScc() {
        return scc;
    }

    public void setScc(String scc) {
        this.scc = scc;
    }

    public String getHelp_code() {
        return help_code;
    }

    public void setHelp_code(String help_code) {
        this.help_code = help_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getRun_date() {
        return run_date;
    }

    public void setRun_date(Date run_date) {
        this.run_date = run_date;
    }

    public String getWare_name() {
        return ware_name;
    }

    public void setWare_name(String ware_name) {
        this.ware_name = ware_name;
    }
}
