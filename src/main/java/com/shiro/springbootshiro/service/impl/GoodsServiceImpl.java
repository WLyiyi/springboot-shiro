package com.shiro.springbootshiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.springbootshiro.bean.Evaluate;
import com.shiro.springbootshiro.bean.Goods;
import com.shiro.springbootshiro.mapper.GoodsMapper;
import com.shiro.springbootshiro.service.IEvaluateService;
import com.shiro.springbootshiro.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	//@Autowired
	//private JedisPool jedisPool;
	@Autowired
	private IEvaluateService evaluateService;
	
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Goods> findAll() {
		return goodsMapper.findAll();
	}
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	@Override
	public Goods findById(Integer id) {
	/*	Jedis jedis=jedisPool.getResource();
		String str = jedis.get(id+"");
		jedis.close();
		if(str==null || str.equals("")){
			Goods goods = goodsMapper.findGoodsById(id);
			List<Evaluate> evaList = evaluateService.findEvaluateByGoodsId(goods.getGoodsId());
			goods.setEvaList(evaList);
			jedis.set(goods.getGoodsId()+"", JSON.toJSONString(goods));
			System.out.println("ヽ(́◕◞౪◟◕‵)ﾉ(*￣︿￣)你没有使用缓存，且加重了服务器负担！(*￣︿￣)(*￣︿￣)");
			return goods;
		}else{
			System.out.println("(｡◕∀◕｡)(｡◕∀◕｡)(｡◕∀◕｡)恭喜你成功使用缓存进行查找！ヽ(́◕◞౪◟◕‵)ﾉ(｡◕∀◕｡)(｡◕∀◕｡)");
			return JSON.toJavaObject(JSON.parseObject(str), Goods.class);
		}*/
		Goods goods = goodsMapper.findGoodsById(id);
		List<Evaluate> evaList = evaluateService.findEvaluateByGoodsId(goods.getGoodsId());
		goods.setEvaList(evaList);
		return goods;
	}
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer update(Goods goods) {
		Integer rs = goodsMapper.updateGoods(goods);
		/*Jedis jedis = jedisPool.getResource();
		if(rs>0){
			List<Evaluate> evaList = evaluateService.findEvaluateByGoodsId(goods.getGoodsId());
			goods.setEvaList(evaList);
			jedis.set(goods.getGoodsId()+"", JSON.toJSONString(goods));
		}
		jedis.close();*/
		return rs;
	}
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteGoods(Integer goodsId) {
		Integer rs = goodsMapper.deleteGoods(goodsId);
		/*Jedis jedis=jedisPool.getResource();
		Integer rs = goodsMapper.deleteGoods(goodsId);
		if(rs>0){
			jedis.del(goodsId+"");
		}
		jedis.close();*/
		return rs;
	}
	
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	public PageInfo<Goods> findBySplitPage(Integer page, Integer size, String keyword){
		List<Goods> list =new ArrayList<Goods>();
		PageHelper.startPage(page, size);
		if(keyword!=null &&!keyword.trim().equals("")){
			list=goodsMapper.findGoodsLikeName(keyword);
		}else{
			list = goodsMapper.findAll();
		}
		PageInfo<Goods> info=new PageInfo<Goods>(list);
		return info;
	}
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Goods> findGoodsByType(Integer typeId) {
		return goodsMapper.findGoodsByType(typeId);
	}
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Goods> findHotGoods(Integer num) {
		return goodsMapper.findHotGoods(num);
	}
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Goods> findGoodsLikeName(String name) {
		return goodsMapper.findGoodsLikeName(name);
	}
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer addGoods(Goods goods) {
		Integer rs = goodsMapper.saveGoods(goods);
		/*Jedis jedis = jedisPool.getResource();
		Integer rs = goodsMapper.saveGoods(goods);
		if(rs>0){
			jedis.set(goods.getGoodsId()+"", JSON.toJSONString(goods));
		}
		jedis.close();*/
		return rs;
	}
	@Transactional(propagation= Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Goods> findGoodsByVolume(Integer limit) {
		return goodsMapper.findGoodsByVolume(limit);
	}
}
