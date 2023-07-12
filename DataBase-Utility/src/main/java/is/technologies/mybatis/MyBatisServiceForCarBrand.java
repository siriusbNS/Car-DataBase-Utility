package is.technologies.mybatis;

import is.technologies.entities.carBrand;
import is.technologies.entities.carBrandMapper;
import is.technologies.entities.carModelMapper;
import is.technologies.interfaceService.IServiceMyBatis;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyBatisServiceForCarBrand implements IServiceMyBatis<carBrand> {
    public MyBatisServiceForCarBrand(String DRIVER,String URL,String USERNAME,String PASSWORD) throws IOException {
        DataSource dataSource = new PooledDataSource(DRIVER,URL,USERNAME,PASSWORD);
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(carBrandMapper.class);
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);

    }
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    @Override
    public carBrand save(carBrand entity) {
        session = sqlSessionFactory.openSession();
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        mapper.save(entity);
        entity.setId(mapper.getLastId(entity));
        session.commit();
        session.close();
        return entity;
    }

    @Override
    public void deleteById(long id) {
        session = sqlSessionFactory.openSession();
        int _id = (int) id;
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        mapper.deleteById(_id);
        session.commit();
        session.close();
    }

    @Override
    public void deleteByEntity(carBrand entity) {
        session = sqlSessionFactory.openSession();
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        mapper.deleteByEntity(entity);
        session.commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        session = sqlSessionFactory.openSession();
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        mapper.deleteAll();
        session.commit();
        session.close();
    }

    @Override
    public carBrand update(carBrand entity) {
        session = sqlSessionFactory.openSession();
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        mapper.update(entity);
        session.commit();
        session.close();
        return entity;
    }

    @Override
    public carBrand getById(long id) {
        session = sqlSessionFactory.openSession();
        int _id = (int)id;
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        carBrand carBrand = mapper.selectCarBrand(_id);
        session.commit();
        session.close();
        return carBrand;
    }

    @Override
    public ArrayList<carBrand> getAll() {
        session = sqlSessionFactory.openSession();
        carBrandMapper mapper = session.getMapper(carBrandMapper.class);
        ArrayList<carBrand> list = new ArrayList<carBrand>(mapper.getAll());
        session.commit();
        session.close();
        return list;
    }

    @Override
    public ArrayList<carBrand> getAllByVId(int id) {
        return null;
    }
}
