package io.project.app.lakehouse;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * @author armena
 */
@Configuration
class PersistenceContext {

    @ConfigurationProperties(prefix = "spring.datasource.lakehouse")
    @Primary
    @Bean(name = "lakehouseDataSource")
    @Qualifier("lakehouseDataSource")
    public DataSource lakehouseDataSource() {
        return new BasicDataSource();
    }

    
}

//https://www.programcreek.com/java-api-examples/?code=ypmc%2Fspring-cloud%2Fspring-cloud-master%2Fspring-batch%2Fsrc%2Fmain%2Fjava%2Fcn%2Fcib%2Fconfiguration%2FRepositorySecondaryConfig.java#