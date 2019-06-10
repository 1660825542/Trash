package main;

import service.SpamDeterminationService;

public class MainTest {
    public static void main(String[] args) {
        SpamDeterminationService spamDeterminationService = new SpamDeterminationService();
        //用来增加学习数据
        spamDeterminationService.submitMsg("free free free",true);

        //用来预测记录是否是垃圾消息
//        System.out.println(spamDeterminationService.isSpam("free free free"));

    }
}
