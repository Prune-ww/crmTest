package com.ncu.demo.service.impl;

import com.ncu.demo.dao.GoodsMapper;
import com.ncu.demo.dao.SaleMapper;
import com.ncu.demo.entity.Goods;
import com.ncu.demo.entity.Sale;
import com.ncu.demo.exception.GoodsNotExistException;
import com.ncu.demo.exception.InventoryNotEnoughException;
import com.ncu.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "myService")
public class MyServiceImpl implements MyService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SaleMapper saleMapper;
    @Override
    public Goods queryGoods(Integer id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    @Transactional
    @Override
    public boolean buyGoods(Integer id, Integer acount) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (goods == null){
            //商品不存在的异常
            throw new GoodsNotExistException("您购买的" + id + "号商品不存在");
        }
        if (goods.getGoodsInventory() < acount){
            //商品不够的异常
            throw new InventoryNotEnoughException("你购买的数量超过了本商品的库存");
        }
        Goods newGoods = new Goods();
        newGoods.setGoodsId(id);
        newGoods.setGoodsInventory(goods.getGoodsInventory() - acount);
        goodsMapper.updateByPrimaryKeySelective(newGoods);
        Sale sale = new Sale();
        sale.setGoodsId(id);
        sale.setSaleAmount(acount);
        saleMapper.insertSelective(sale);
        return true;
    }
}
