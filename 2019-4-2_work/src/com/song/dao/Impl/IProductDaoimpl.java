package com.song.dao.Impl;

import java.util.List;

import com.rock.util.JDBCUtil;
import com.song.dao.IProductDao;
import com.song.entities.Product;
import com.song.entities.Remark;

public class IProductDaoimpl implements IProductDao {
        private JDBCUtil jdbcUti=null;
        public IProductDaoimpl()
        {
            jdbcUti=new JDBCUtil();
        }
    public Product findp(String n) {
            String sql="select * from goods where goodid=?";

         Product p= jdbcUti.queryById(Product.class,sql,n);

         return p;
    }

    @Override
    public List<Remark> allremark() {
        String sql="select * from remark ORDER BY TIME";
        return jdbcUti.queryAll(Remark.class,sql);
    }

    @Override
    public int addcar(String phonenum, String product_id, String num) {

            String s="select num from shopcart group by phonenum,product_id,num  having phonenum="+phonenum+" and product_id="+product_id;
            int n=jdbcUti.getCount(s);
            Product p=findp(product_id);
            if (n>0)
            {
                
                int n1=n+Integer.parseInt(num);

                if (n1 > Integer.parseInt(p.getStock()))
                {
                    n1=Integer.parseInt(p.getStock());
                }
                String sql="update shopcart set num="+n1+"  where phonenum="+phonenum+" and product_id="+product_id;
                return jdbcUti.runSql(sql);
            }

            else {
                	String sql = "INSERT INTO  shopcart VALUE(null,'"+p.getName()+"','" + phonenum + "','" + product_id + "','" + num + "','"+p.getStock() + "','"+p.getPicture()+"','"+p.getPrice()+"')";
                	System.out.println(sql);
                	return jdbcUti.runSql(sql);
            	}
    }
}
