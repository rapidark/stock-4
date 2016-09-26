package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LongHuTypeMapper;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.LongHuTypeExample;
import com.gesangwu.spider.biz.service.LongHuTypeService;

@Service
public class LongHuTypeServiceImpl extends BaseServiceImpl<LongHuType, LongHuTypeExample> implements LongHuTypeService {
	
	@Resource
	private LongHuTypeMapper mapper;

	@Override
	protected BaseMapper<LongHuType, LongHuTypeExample> getMapper() {
		return mapper;
	}

	@Override
	public LongHuType selectByType(String type) {
		LongHuTypeExample example = new LongHuTypeExample();
		LongHuTypeExample.Criteria criteria = example.createCriteria();
		criteria.andLhTypeEqualTo(type);
		List<LongHuType> typeList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(typeList) ? null : typeList.get(0);
	}

}
