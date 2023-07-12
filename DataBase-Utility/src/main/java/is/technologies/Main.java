package is.technologies;

import is.technologies.entities.carBodyType;
import is.technologies.entities.carBrand;
import is.technologies.entities.carBrandMapper;
import is.technologies.entities.carModel;
import is.technologies.hibernate.HibernateServiceForCarBrand;
import is.technologies.hibernate.HibernateServiceForCarModel;
import is.technologies.interfaceService.IServiceHibernate;
import is.technologies.interfaceService.IServiceJDBC;

import is.technologies.interfaceService.IServiceMyBatis;
import is.technologies.jdbc.JDBCServiceForCarBrand;
import is.technologies.jdbc.JDBCServiceForCarModel;
import is.technologies.mybatis.MyBatisServiceForCarBrand;
import is.technologies.mybatis.MyBatisServiceForCarModel;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String URL = "jdbc:postgresql://localhost:5433/testdb";
    public static final String USER = "postgres";
    public static final String PASSWORD = "rootroot";
    public static final String DRIVER = "org.postgresql.Driver";
    public static void main(String[] args) throws SQLException, IOException {

    }
}