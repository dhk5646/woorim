package com.aks.woorim.service.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("smp/sqlMapperSampleService")
public class sqlMapperSampleService {

	private static Logger logger = LoggerFactory.getLogger(sqlMapperSampleService.class);

	@Resource(name = "postgresqlSqlSession")
	SqlSession postgresqlSqlSession;


	public Map<String, Object> read(Map<String, Object> map) {

		long startSec = System.currentTimeMillis();

		
		  Map<String, Object> inputMap = new HashMap<String, Object>(); Map<String,
		  Object> outputMap = new HashMap<String, Object>();
		  
		  //1. Interface를 이용한 방법 sqlMapperSampleMapper sampleMapper =
		  //sqlSession.getMapper(sqlMapperSampleMapper.class); outputMap.put("read", sampleMapper.read(runLevel));
		  
		  //2. Name을 이용한 방법 String a = sqlSession.selectOne("smp.SampleService.test", runLevel); //List<Map<String, Object>> b =
		  List<Map<String, Object>> a =  postgresqlSqlSession.selectList("com.aks.woorim.svc.sample.sqlMapperSampleMapper.read");
		  
		  //result.put("a", a); //result.put("b", b);
		 
		long totSec = (System.currentTimeMillis() - startSec) / 1000;

		logger.info("MvcController.doGet End [" + totSec + "]");
		return outputMap;
	}
}