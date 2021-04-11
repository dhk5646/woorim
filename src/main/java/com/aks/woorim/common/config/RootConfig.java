package com.aks.woorim.common.config;

import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;

import com.aks.woorim.common.mybatis.RefreshableSqlSessionFactoryBean;
import com.aks.woorim.common.util.BeanUtil;

/**
 * root-context.xml 설정
 *
 */
@Configuration
@PropertySource({"classpath:application.properties"})
//@EnableTransactionManagement
@MapperScan(basePackages= {"com.aks.woorim"})
@ComponentScan(basePackages= {"com.aks.woorim"}, excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig{

	@Resource
	ApplicationContext applicationContext;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean(name = "postgresqlDs")
	public DataSource postgresqlDs() throws DataSourceLookupFailureException{
		
		/*
		HikariDataSource dataSource = new HikariDataSource(); 
		dataSource.setMaximumPoolSize(20); 
		dataSource.setDriverClassName(postgresqlDriver);
		dataSource.setJdbcUrl(postgresqlUrl);
		dataSource.addDataSourceProperty("user", postgresqlUsername); 
		dataSource.addDataSourceProperty("password", postgresqlPassword);
		dataSource.setAutoCommit(false);
		*/
		
		// 참고 사이트
		//https://zgundam.tistory.com/84
		JndiDataSourceLookup jdsl = new JndiDataSourceLookup(); 
		jdsl.setResourceRef(true);
		DataSource dataSource = jdsl.getDataSource("jdbc/woorimDs");
		return dataSource;
	}
	
	@Bean(name = "postgresqlSqlSessionFactory")
	public SqlSessionFactory postgresqlSqlSessionFactory() throws Exception {
		RefreshableSqlSessionFactoryBean sqlSessionFactoryBean = new RefreshableSqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(postgresqlDs());
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:com/aks/**/*.xml"));
		sqlSessionFactoryBean.setTypeAliases(new Class<?>[] { Map.class});
		sqlSessionFactoryBean.setInterval(3000);
		sqlSessionFactoryBean.afterPropertiesSet();
		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
	}
	
	//@Primary
	@Bean(name = "postgresqlSqlSession")
    public SqlSessionTemplate postgresqlSqlSession() throws Exception {
        return new SqlSessionTemplate(postgresqlSqlSessionFactory());
    }
	
	//@Primary
	@Bean(name = "postgresqlTemplate")
	public JdbcTemplate postgresqlTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(postgresqlDs());
		return template;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(postgresqlDs());
		return transactionManager;
    }
	
	@Bean
	public BeanUtil beanUtil() {
		return new BeanUtil();
	}
	
	/*
	 * public PlatformTransactionManager transactionManager1() {
	 * JtaTransactionManager manager = new JtaTransactionManager();
	 * 
	 * //manager.setUserTransaction(userTransaction);
	 * 
	 * return manager; }
	 * 
	 * public PlatformTransactionManager
	 * transactionManager(PlatformTransactionManager txManager1,
	 * PlatformTransactionManager txManager2) {
	 * 
	 * return new ChainedTransactionManager(txManager1, txManager2); }
	 */
}