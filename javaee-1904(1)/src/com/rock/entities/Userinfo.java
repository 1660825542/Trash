package com.rock.entities;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-20 17:00:36
 */
public class Userinfo {
    private String userid;
    private String password;
    private String sex;
    private String hobby;
    private String qid;
    private String answer;
    private String degree;
    private String remark;

    public Userinfo() {
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", qid='" + qid + '\'' +
                ", answer='" + answer + '\'' +
                ", degree='" + degree + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Userinfo(String userid, String password, String sex, String hobby, String qid, String answer, String degree, String remark) {
        this.userid = userid;
        this.password = password;
        this.sex = sex;
        this.hobby = hobby;
        this.qid = qid;
        this.answer = answer;
        this.degree = degree;
        this.remark = remark;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
