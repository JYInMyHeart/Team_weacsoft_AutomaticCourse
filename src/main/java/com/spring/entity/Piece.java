package com.spring.entity;

import java.util.Date;

public class Piece {
    private int id;
    private String piece_name;
    private String scc;
    private String help_code;
    private String price;
    private int inventory;
    private String state;
    private Date run_date;
    private int ware_id;



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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWare_id() {
        return ware_id;
    }

    public void setWare_id(int ware_id) {
        this.ware_id = ware_id;
    }
}
