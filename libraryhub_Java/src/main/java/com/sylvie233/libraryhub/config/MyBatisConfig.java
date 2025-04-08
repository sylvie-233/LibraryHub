package com.sylvie233.libraryhub.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("com.sylvie233.libraryhub.mapper")
public class MyBatisConfig {
    
    /**
     * 数据库
     * @return
     * @throws IOException 
     */
    @Bean
    public DataSource dataSource() throws IOException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.sqlite.JDBC");
        ClassPathResource resource = new ClassPathResource("libraryhub.db");
        String absolutePath = resource.getFile().getAbsolutePath();
        basicDataSource.setUrl("jdbc:sqlite:" + absolutePath);
        return basicDataSource;
    }

    /**
     * 数据库事务管理器
     * @param ds
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }


    /**
     * MyBatis 会话管理器
     * @param ds
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
