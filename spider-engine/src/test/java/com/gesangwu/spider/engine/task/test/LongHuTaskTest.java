package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.engine.task.LongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LongHuTaskTest extends BaseTest {

	@Resource
	private LongHuTask task;
	@Resource
	private LongHuService lhService;
	
	@Test
	public void execute(){
		task.execute("2016-10-11");
//		LongHu longHu = lhService.selectByPrimaryKey(82302l);
//		task.fetchDetail(3, "05", longHu);
	}
}
