package com.song.service.Impl;



import java.util.List;

import com.song.dao.IProductDao;
import com.song.dao.Impl.IProductDaoimpl;
import com.song.entities.Product;
import com.song.entities.Remark;
import com.song.service.IProductService;

public class IProductServiceimpl implements IProductService {
   private IProductDao iProductDao=null;
   public IProductServiceimpl()
   {
       iProductDao=new IProductDaoimpl();
   }
    public Product findp(String n) {
        return iProductDao.findp(n);
    }

    @Override
    public List<Remark> allremark() {
        return iProductDao.allremark();
    }

    @Override
    public int addcar(String phonenum, String product_id, String num) {
        return iProductDao.addcar(phonenum,product_id,num);
    }
}
