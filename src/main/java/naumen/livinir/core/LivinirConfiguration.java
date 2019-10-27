package naumen.livinir.core;

import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import naumen.livinir.security.SecurityConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("naumen.livinir.dao"),
        @ComponentScan("naumen.livinir.utils"),
        @ComponentScan("naumen.livinir.service"),
})
@Import(SecurityConfig.class)
public class LivinirConfiguration
{
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(Resident.class, Announcement.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager()
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

//    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldConfiguration.class);
//    @NotEmpty
//    private String template;
//
//    @NotEmpty
//    private String defaultName = "Stranger";
//
//    @Valid
//    @NotNull
//    private DataSourceFactory database = new DataSourceFactory();
//
//    @JsonProperty
//    public String getTemplate()
//    {
//        return template;
//    }
//
//    @JsonProperty
//    public void setTemplate(String template)
//    {
//        this.template = template;
//    }
//
//    @JsonProperty
//    public String getDefaultName()
//    {
//        return defaultName;
//    }
//
//    @JsonProperty
//    public void setDefaultName(String defaultName) {
//        this.defaultName = defaultName;
//    }
//
//    public Template buildTemplate() {
//        return new Template(template, defaultName);
//    }
//
//    /**
//     * This gets called with the values from the Dropwizard example.xmp, but we want to override it with the values
//     * from the Heroku DATABASE_URL environment variable.
//     */
//    @JsonProperty("database")
//    public DataSourceFactory getDataSourceFactory() {
//        LOGGER.info("Dropwizard dummy DB URL (will be overridden)=" + database.getUrl());
//        DatabaseConfiguration databaseConfiguration = ExampleDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
//        database = databaseConfiguration.getDataSourceFactory(null);
//        LOGGER.info("Heroku DB URL=" + database.getUrl());
//        return database;
//    }
//
//    @JsonProperty("database")
//    public void setDataSourceFactory(DataSourceFactory dataSourceFactory)
//    {
//        this.database = dataSourceFactory;
//    }

//    @Bean
//    public SessionFactory sessionFactory()
//    {
//
//    }
//
//    public SessionFactory sessionFactory()
//    {
//        return sessionFactory().getObject();
//    }
//
//    private Properties getHibernateProperties()
//    {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }
}
