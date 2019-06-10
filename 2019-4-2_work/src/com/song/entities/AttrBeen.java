package com.song.entities;

public class AttrBeen {
    private String attri;
    private String ename;
    private String path;

    public String getAttri() {
        return attri;
    }

    public void setAttri(String attri) {
        this.attri = attri;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public AttrBeen(String attri, String ename, String path) {
        this.attri = attri;
        this.ename = ename;
        this.path = path;
    }

    public AttrBeen() {
    }

    @Override
    public String toString() {
        return "Attr{" +
                "attri='" + attri + '\'' +
                ", ename='" + ename + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
