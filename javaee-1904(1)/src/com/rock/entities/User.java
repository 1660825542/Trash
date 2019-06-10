package com.rock.entities;

/**
 * @author :老张
 * @version :1.0
 * @Description :
 * @Date :2019-03-20 14:36:46
 */
public class User {
    private String userid;
    private String password;
    private String qid;
    private String anwser;
    private String score;

    public User() {
    }

    public User(String userid, String password, String qid, String anwser, String score) {
        this.userid = userid;
        this.password = password;
        this.qid = qid;
        this.anwser = anwser;
        this.score = score;
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

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
