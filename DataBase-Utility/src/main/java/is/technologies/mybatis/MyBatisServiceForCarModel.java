package is.technologies.mybatis;

import is.technologies.entities.carBrand;
import is.technologies.entities.carBrandMapper;
import is.technologies.entities.carModel;
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
import java.util.ArrayList;

public class MyBatisServiceForCarModel implements IServiceMyBatis<carModel> {
    public MyBatisServiceForCarModel(String DRIVER,String URL,String USERNAME,String PASSWORD) throws IOException {
        DataSource dataSource = new PooledDataSource(DRIVER,URL,USERNAME,PASSWORD);
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(carModelMapper.class);
        configuration.addMapper(carBrandMapper.class);

        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);

    }
    private static SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    /**
     * @param entity
     * @return
     */
    @Override
    public carModel save(carModel entity) {
        session = sqlSessionFactory.openSession();
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        mapper.save(entity);
        entity.setId(mapper.getLastId(entity));
        session.commit();
        session.close();
        return entity;
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(long id) {
        session = sqlSessionFactory.openSession();
        int _id = (int) id;
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        mapper.deleteById(_id);
        session.commit();
        session.close();
    }

    /**
     * @param entity
     */
    @Override
    public void deleteByEntity(carModel entity) {
        session = sqlSessionFactory.openSession();
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        mapper.deleteByEntity(entity);
        session.commit();
        session.close();
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        session = sqlSessionFactory.openSession();
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        mapper.deleteAll();
        session.commit();
        session.close();
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public carModel update(carModel entity) {
        session = sqlSessionFactory.openSession();
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        mapper.update(entity);
        session.commit();
        session.close();
        return entity;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public carModel getById(long id) {
        session = sqlSessionFactory.openSession();
        int _id = (int)id;
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        carModel carModel = mapper.selectCarModel(_id);
        carBrandMapper mapper1 = session.getMapper(carBrandMapper.class);
        carBrand carBrand = mapper1.selectCarBrand(carModel.getCarBrand().getId());
        carModel.setCarBrand(carBrand);
        session.commit();
        session.close();
        return carModel;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<carModel> getAll() {
        session = sqlSessionFactory.openSession();
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        ArrayList<carModel> list = new ArrayList<carModel>(mapper.getAll());
        session.commit();
        session.close();
        return list;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<carModel> getAllByVId(int id) {
        session = sqlSessionFactory.openSession();
        carModelMapper mapper = session.getMapper(carModelMapper.class);
        ArrayList<carModel> list = new ArrayList<carModel>(mapper.getByVId(id));
        session.commit();
        session.close();
        return list;
    }
}
