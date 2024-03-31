package spectra.attic.migration.config;

import java.util.Properties;
import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "spectra.attic.migration.**.repository.temp",
    entityManagerFactoryRef = "targetEntityManagerFactory",
    transactionManagerRef = "targetTransactionManager"
)
public class TemporalDataConfig {

    @Value("${spring.datasource.temp.url}")
    String url;

    @Value("${spring.datasource.temp.username}")
    String username;

    @Value("${spring.datasource.temp.password}")
    String password;

    @Value("${spring.datasource.temp.driver-class-name}")
    String driverClassName;

    @Value("${spring.datasource.temp.ddl-auto}")
    private String ddlAuto;

    @Bean(name = "tempDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.temp")
    public DataSource tempDataSource() {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driverClassName)
            .build();
    }

    @Bean(name = "tempEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean targetEntityManagerFactory(
        JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(tempDataSource());
        em.setPackagesToScan("spectra.attic.migration.entity.target");
        em.setJpaVendorAdapter(jpaVendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaProperties(jpaProperties);
        return em;
    }

    @Bean(name = "tempTransactionManager")
    public PlatformTransactionManager targetTransactionManager(
        EntityManagerFactory targetEntityManagerFactory
    ) {
        return new JpaTransactionManager(targetEntityManagerFactory);
    }
}