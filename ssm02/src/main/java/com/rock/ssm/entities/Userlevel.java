package com.rock.ssm.entities;

public class Userlevel {
    private Integer levelid;

    private String levelname;

    private String leveltxt;

    
    
    
    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname == null ? null : levelname.trim();
    }

    public String getLeveltxt() {
        return leveltxt;
    }

    public void setLeveltxt(String leveltxt) {
        this.leveltxt = leveltxt == null ? null : leveltxt.trim();
    }
}