package com.song.service;



import java.util.List;

import com.song.entities.Product;
import com.song.entities.Remark;

public interface IProductService {
    public Product findp(String n);
    public List<Remark> allremark();
    public int addcar(String phonenum,String product_id,String num);
}
