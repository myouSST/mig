package spectra.attic.migration.config;

import java.util.Properties;
import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "spectra.attic.migration.**.repository.source",
    entityManagerFactoryRef = "sourceEntityManagerFactory",
    transactionManagerRef = "sourceTransactionManager"
)
public class SourceDataConfig {

    @Value("${spring.datasource.source.url}")
    String url;

    @Value("${spring.datasource.source.username}")
    String username;

    @Value("${spring.datasource.source.password}")
    String password;

    @Value("${spring.datasource.source.driver-class-name}")
    String driverClassName;

    @Value("${spring.datasource.source.ddl-auto}")
    private String ddlAuto;

    @Primary
    @Bean(name = "sourceDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.source")
    public DataSource sourceDataSource() {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driverClassName)
            .build();
    }

    @Primary
    @Bean(name = "sourceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(
        JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sourceDataSource());
        em.setPackagesToScan("spectra.attic.migration.entity.source");
        em.setJpaVendorAdapter(jpaVendorAdapter);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        //jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
        em.setJpaProperties(jpaProperties);

        return em;
    }

    @Primary
    @Bean(name = "sourceTransactionManager")
    public PlatformTransactionManager sourceTransactionManager(
        EntityManagerFactory sourceEntityManagerFactory
    ) {
        return new JpaTransactionManager(sourceEntityManagerFactory);
    }
}