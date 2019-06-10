package com.rock.HDFS;

public class Salary {
    public static void main(String[] args) {
        System.out.println(converson("1.2-2.4千/月"));
        System.out.println(converson("200元/小时"));
        System.out.println(converson("1.2-2.4万/月"));
    }
    public static double converson(String s)
    {
        double b= 0;
        if (s.contains("-"))
        {
            String f[]=s.split("-");
            if (f[1].contains("千"))
            {
                String []f1=f[1].split("千");
                double q=Double.parseDouble(f1[0])/10;
                f[1]=""+(q*12)+"万/年";
                b= q*12;
            }
            if (f[1].contains("月"))
            {
                String []f1=f[1].split("万");
                double q1=Double.parseDouble(f1[0]);
                b= q1*12;
            }

        }
        if (s.contains("时"))
        {
            String []shi=s.split("元");
            double q=Double.parseDouble(shi[0]);
            b= q*8*22*12/10000;
        }

        return (double)Math.round(b*100)/100;
    }

}

