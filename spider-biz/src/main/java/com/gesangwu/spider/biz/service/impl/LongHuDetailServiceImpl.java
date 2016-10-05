package com.gesangwu.spider.biz.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.LongHuDetailPair;
import com.gesangwu.spider.biz.dao.mapper.LongHuDetailMapper;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.service.LongHuDetailService;

@Service
public class LongHuDetailServiceImpl extends BaseServiceImpl<LongHuDetail, LongHuDetailExample> implements
		LongHuDetailService {
	
	@Resource
	private LongHuDetailMapper mapper;

	@Override
	protected BaseMapper<LongHuDetail, LongHuDetailExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<LongHuDetail> detailList) {
		mapper.insertBatch(detailList);
	}

	@Override
	public LongHuDetailPair selectDetailPairs(String symbol, String tradeDate, int dateType) {
		List<LongHuDetail> buyList = getBuyList(symbol, tradeDate, dateType);
		List<LongHuDetail> sellList = getSellList(symbol, tradeDate, dateType);
		return new LongHuDetailPair(buyList, sellList);
	}
	
	private List<LongHuDetail> getBuyList(String symbol, String tradeDate, int dateType){
		LongHuDetailExample example = new LongHuDetailExample();
		example.setOrderByClause("buy_amt desc");
		example.setOffset(0);
		example.setRows(5);
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andBuyAmtGreaterThan(BigDecimal.ZERO);
		criteria.andDateTypeEqualTo(dateType);
		return mapper.selectByExample(example);
	}
	
	private List<LongHuDetail> getSellList(String symbol, String tradeDate, int dateType){
		LongHuDetailExample example = new LongHuDetailExample();
		example.setOrderByClause("sell_amt desc");
		example.setOffset(0);
		example.setRows(5);
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andSellAmtGreaterThan(BigDecimal.ZERO);
		criteria.andDateTypeEqualTo(dateType);
		return mapper.selectByExample(example);
	}

	@Override
	public void selectByPagination(LongHuDetailExample example,
			Page<LongHuDetail> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        page.setRecords(mapper.selectByExample(example));
	}

	@Override
	public void selectDetailExtByExample(LongHuDetailExample example, Page<LongHuDetailExt> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        page.setRecords(mapper.selectDetailExtByExample(example));
	}

}
