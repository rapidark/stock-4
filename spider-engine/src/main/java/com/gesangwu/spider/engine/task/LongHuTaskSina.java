package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.util.StockTool;
import com.gesangwu.spider.engine.util.TradeTimeUtil;
import com.gesangwu.spider.engine.util.XinLangLongHuTool;

/**
 * TODO 没有对龙虎类型检查数据库中是否存在
 * <ul>
 * 	<li>东方财富</li>
 *  <li>http://data.eastmoney.com/stock/tradedetail.html</li>
 * </ul>
 * <ul>
 * 	<li>新浪</li>
 * 	<li>http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml</li>
 * </ul>
 * @author zhuxb
 *
 */
@Component
public class LongHuTaskSina {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuTaskSina.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String r1 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"[\\-0-9\\.]*\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\",\"ZeMoney\"\\:\"[^\"]*\",\"Tdate\"\\:\"([^\"]*)\",\"JmRate\"\\:\"[^\"]*\",\"ZeRate\"\\:\"[^\"]*\",\"Ltsz\"\\:\"([^\"]*)\"";
	private static Pattern p1 = Pattern.compile(r1);
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuTypeService typeService;
	@Resource
	private CliqueStockTask cliqueTask;
	@Resource
	private SecDeptService deptService;
	
	public void execute(){
		if(!TradeTimeUtil.checkLongHuTime()){
			return;
		}
		Date now= new Date();
		String tradeDate = sdf.format(now);
		execute(tradeDate);
	}
	/**
	 * gpfw:0-全部，1-上证，2-深证
	 */
	public void execute(String tradeDate){
		long start = System.currentTimeMillis();
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		String url = buildUrl(tradeDate, 0);
		String result = HttpTool.get(url, Charset.forName("GB2312"));
		parse(result, tradeDate);
		long end = System.currentTimeMillis();
		logger.info("Fetch LongHu used:" + (end-start) + "ms!");
	}
	
	private String buildUrl(String date, int bazaar){
		StringBuilder sb = new StringBuilder();
		sb.append("http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=");
		sb.append(date);
		sb.append(",endDate=");
		sb.append(date);
		sb.append(",gpfw=");
		sb.append(bazaar);
		sb.append(",js=.html");
		return sb.toString();
	}
	
	private void parse(String content, String tradeDate){
		Set<String> lhSet = completedLongHuMap(tradeDate);
		Matcher m =  p1.matcher(content);
		Date now = new Date();
		List<LongHu> longHuList = new ArrayList<LongHu>();
		Set<String> lhCodeSet = new HashSet<String>();
		Map<String,List<String>> typeMap = XinLangLongHuTool.getLongHuType(tradeDate);
		if(typeMap == null || typeMap.size() == 0){
			logger.error("No type Map fund from Sina!");
			return;
		}
		while(m.find()){
			String code = m.group(1);
			String symbol = StockTool.codeToSymbol(code);
			if(lhSet.contains(symbol)){
				continue;
			}
			String stockName = m.group(2);
			String price = m.group(3);
			String chg = m.group(4);
			String turnover = m.group(5);
			if(lhCodeSet.contains(code)){//已经存在了
				continue;
			}else {
				lhCodeSet.add(code);
			}
			LongHu longHu = new LongHu();
			longHu.setSymbol(symbol);
			longHu.setStockName(stockName);
			longHu.setPrice(Double.valueOf(price));
			longHu.setChgPercent(DecimalUtil.parse(chg).doubleValue());
			if(StringUtil.isNotBlank(turnover)){
				longHu.setTurnover(DecimalUtil.parse(turnover).doubleValue());
			}
			longHu.setTradeDate(tradeDate);
			longHu.setGmtCreate(now);
			List<String> typeList = typeMap.get(code);
			if(CollectionUtils.isEmpty(typeList)){
				continue;
			}
			List<String> yrList = new ArrayList<String>();
			List<String> erList = new ArrayList<String>();
			List<String> srList = new ArrayList<String>();
			buildLhType(yrList, erList, srList, typeList);
			if(yrList.size() > 0){
				String typeArr = StringUtil.join(yrList.iterator(), SymbolConstant.COMMA);
				longHu.setYrType(typeArr);
			}
			if(erList.size() > 0){
				String typeArr = StringUtil.join(erList.iterator(), SymbolConstant.COMMA);
				longHu.setErType(typeArr);		
			}
			if(srList.size() > 0){
				String typeArr = StringUtil.join(srList.iterator(), SymbolConstant.COMMA);
				longHu.setSrType(typeArr);
			}
			longHuList.add(longHu);
		}
		for (LongHu longHu : longHuList) {
			fetchDetail(longHu);
		}
	}
	
	/**
	 * 已完成的龙虎榜
	 * @return
	 */
	private Set<String> completedLongHuMap(String tradeDate){
		Set<String> lhSet = new HashSet<String>();
		List<LongHu> lhList = lhService.selectByTradeDate(tradeDate);
		for (LongHu longHu : lhList) {
			lhSet.add(longHu.getSymbol());
		}
		return lhSet;
	}
	
	public void fetchDetail(LongHu longHu){
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		if(StringUtil.isNotBlank(longHu.getYrType())){
			lhdList.addAll(fetchDetail(1, longHu.getYrType(), longHu));
		}
		if(StringUtil.isNotBlank(longHu.getErType())){
			lhdList.addAll(fetchDetail(2, longHu.getErType(), longHu));
		}
		if(StringUtil.isNotBlank(longHu.getSrType())){
			lhdList.addAll(fetchDetail(3, longHu.getSrType(), longHu));
		}
		lhService.insert(longHu, lhdList);
		cliqueTask.calc(longHu);
	}
	
	private static final String r2 = "SYMBOL\\:\"([0-9]{6})\",type\\:\"([0-9]{2})\",comCode\\:\"([0-9]*)\",comName\\:\"([^\"]*)\",buyAmount\\:\"([0-9\\.]*)\",sellAmount\\:\"([0-9\\.]*)\",netAmount\\:([0-9\\.\\-]*)";
	private Pattern p2 = Pattern.compile(r2);
	
	public List<LongHuDetail> fetchDetail(int dateType, String lhType, LongHu longHu){
		int index = lhType.indexOf(SymbolConstant.COMMA);
		if(index > 0){
			lhType = lhType.substring(0, index);
		}
		String code = StockUtil.symbol2Code(longHu.getSymbol());
		String tradeDate = longHu.getTradeDate();
		String result = getDetailContent(lhType, code, tradeDate);
		Matcher m = p2.matcher(result);
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		Date now = new Date();
		Map<String,LongHuDetail> detailMap = new HashMap<String,LongHuDetail>();
		while(m.find()){
			String deptCode = m.group(3);
			String deptName = m.group(4);//TODO 这里是否需要更新营业部的名称
			String buyAmtStr = m.group(5);
			String sellAmtStr = m.group(6);
			String netAmtStr = m.group(7);
			souDept(deptCode, deptName);
			LongHuDetail detail = detailMap.get(deptCode);
			BigDecimal buyAmt = DecimalUtil.parse(buyAmtStr);
			BigDecimal sellAmt = DecimalUtil.parse(sellAmtStr);
			BigDecimal netBuy = DecimalUtil.parse(netAmtStr);
			if(detail != null){
				if(detail.getSellAmt().compareTo(sellAmt) < 0){
					detail.setSellAmt(sellAmt);
					netBuy = detail.getBuyAmt().subtract(sellAmt);
					detail.setNetBuy(netBuy);
					sellTotal = sellTotal.add(sellAmt);
				}
				continue;
			} else {
				detail = new LongHuDetail(); 
				detailMap.put(deptCode, detail);
			}
			
			buyTotal = buyTotal.add(buyAmt);
			sellTotal = sellTotal.add(sellAmt);

			detail.setLongHuId(longHu.getId());
			detail.setBuyAmt(buyAmt);
			detail.setSellAmt(sellAmt);
			detail.setNetBuy(netBuy);
			detail.setDateType(dateType);
			detail.setGmtCreate(now);
			detail.setSecDeptCode(deptCode);
			detail.setSymbol(StockUtil.code2Symbol(code));
			detail.setTradeDate(tradeDate);	
		}
		if(dateType == 1){
			longHu.setYrAmt(buildAmt(buyTotal, sellTotal));
		} else if(dateType == 2){
			longHu.setErAmt(buildAmt(buyTotal, sellTotal));
		} else if (dateType == 3){
			longHu.setSrAmt(buildAmt(buyTotal, sellTotal));
		}
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		for (LongHuDetail detail : detailMap.values()) {
			lhdList.add(detail);
		}
		return lhdList;
	}
	
	/**
	 * 保存或更新营业部信息
	 * @param secCode
	 * @param secName
	 */
	private void souDept(String deptCode, String deptName){
		SecDept dept = deptService.selectByCode(deptCode);
		if(dept == null){
			dept = new SecDept();
			dept.setCode(deptCode);
			dept.setDeptAddr(deptName);
			dept.setGmtCreate(new Date());
			deptService.insert(dept);
		} else {
			dept.setDeptAddr(deptName);
			dept.setGmtUpdate(new Date());
			deptService.updateByPrimaryKey(dept);
		}
	}
	
	private String getDetailContent(String lhType, String code, String tradeDate){
		String url = buildDetailUrl(lhType, code, tradeDate);
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
		headerMap.put("Accept-Encoding", "gzip, deflate, sdch");
		return HttpTool.get(url, headerMap, Charset.forName("GBK"));
	}
	
	private String buildAmt(BigDecimal buyTotal, BigDecimal sellTotal){
		BigDecimal netBuy = buyTotal.subtract(sellTotal);
		StringBuilder sb = new StringBuilder();
		sb.append(buyTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(sellTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(netBuy.toString());
		return sb.toString();
	}
	
	private void buildLhType(List<String> yrList, List<String> erList, List<String> srList, List<String> typeList){
		for (String type : typeList) {
			LongHuType lhType = typeService.selectByType(type);
			if(lhType.getDateType() == 1){//一日
				yrList.add(type);
			} else if(lhType.getDateType() == 3){//三日
				srList.add(type);
			} else if(lhType.getDateType() == 2){//二日
				erList.add(type);
			}
		}
	}
	
	private String buildDetailUrl(String type, String code, String date){
		StringBuilder sb = new StringBuilder();
		sb.append("http://vip.stock.finance.sina.com.cn/q/api/jsonp.php/var%20details=/InvestConsultService.getLHBComBSData?symbol=");
		sb.append(code);
		sb.append("&tradedate=");
		sb.append(date);
		sb.append("&type=");
		sb.append(type);
		return sb.toString();
	}
	
	public static void main(String[] args){
		LongHuTaskSina task = new LongHuTaskSina();
		task.execute("2016-10-11");
	}
}
