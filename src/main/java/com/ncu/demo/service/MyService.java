package com.ncu.demo.service;

import com.ncu.demo.entity.Goods;
public interface MyService {
    Goods queryGoods(Integer id);

    boolean buyGoods(Integer id, Integer acount);
}
