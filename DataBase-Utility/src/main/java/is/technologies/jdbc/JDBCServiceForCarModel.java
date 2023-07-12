package is.technologies.jdbc;

import is.technologies.entities.carBodyType;
import is.technologies.entities.carBrand;
import is.technologies.entities.carModel;

import is.technologies.interfaceService.IServiceJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceForCarModel implements IServiceJDBC<carModel> {
    public JDBCServiceForCarModel(String URL,String USER,String PASSWORD) throws SQLException
    {
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            if (!connection.isClosed())
                System.out.println("Подключение установлено");
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }
    private Driver driver;
    private Connection connection;
    @Override
    public carModel save(carModel entity) throws SQLException {
        String INSERT_NEW = "INSERT INTO mydatabase.carmodel(modelname,modellength,modelwidth,bodytype,brandid_) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,entity.getModelName());
        preparedStatement.setInt(2,entity.getLength());
        preparedStatement.setInt(3,entity.getWeidth());
        preparedStatement.setString(4,entity.getCarBodyType());
        preparedStatement.setInt(5,entity.getCarBrand().getId());
        preparedStatement.execute();
        ResultSet resultSet1 = preparedStatement.getGeneratedKeys();
        resultSet1.next();
        entity.setId(resultSet1.getInt(1));


        return entity;
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String DELETE_NEW = "DELETE FROM mydatabase.carmodel WHERE mydatabase.carmodel.modelid = " + id;
        Statement statement = connection.createStatement();
        statement.execute(DELETE_NEW);
    }

    @Override
    public void deleteByEntity(carModel entity) throws SQLException {
        String DELETE_NEW = "DELETE FROM mydatabase.carmodel " +
                "WHERE mydatabase.carmodel.modelname =? AND  mydatabase.carmodel.modellength =? AND  mydatabase.carmodel.modelwidth =? AND  mydatabase.carmodel.bodytype =?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NEW);
        preparedStatement.setString(1,entity.getModelName());
        preparedStatement.setInt(2,entity.getLength());
        preparedStatement.setInt(3,entity.getWeidth());
        preparedStatement.setString(4,entity.getCarBodyType());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteAll() throws SQLException {
        String DELETE_NEW = "DELETE FROM mydatabase.carmodel";
        Statement statement = connection.createStatement();
        statement.execute(DELETE_NEW);

    }

    @Override
    public carModel update(carModel entity, int id) throws SQLException {
        String UPDATE_NEW = "UPDATE mydatabase.carmodel SET modelname = ?, modellength = ?,modelwidth = ?,bodytype = ? WHERE modelid = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEW);
        preparedStatement.setString(1,entity.getModelName());
        preparedStatement.setInt(2,entity.getLength());
        preparedStatement.setInt(3,entity.getWeidth());
        preparedStatement.setString(4,entity.getCarBodyType());
        preparedStatement.setInt(5,id);
        preparedStatement.execute();
        return entity;
    }

    @Override
    public carModel getById(long id) throws SQLException {
        String SEARCH_NEW = "SELECT * from mydatabase.carmodel WHERE modelid = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SEARCH_NEW);
        resultSet.next();
        String SEARCH_NEW1 = "SELECT * from mydatabase.carbrand WHERE brandid = " + resultSet.getInt(6);
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement1.executeQuery(SEARCH_NEW1);
        resultSet1.next();
        carBrand entityBrand = new carBrand(resultSet1.getString(2),resultSet1.getDate(3));
        resultSet1.close();
        statement1.close();
        carModel entityModel = new carModel(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),carBodyType.rodster.getByString(resultSet.getString(5)) ,entityBrand);
        return entityModel;
    }

    @Override
    public List<carModel> getAll() throws SQLException {
        String SEARCH_NEW = "SELECT * from mydatabase.carmodel ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SEARCH_NEW);
        ArrayList<carModel> modelArrayList = new ArrayList<carModel>();
        while(resultSet.next())
        {
            String SEARCH_NEW1 = "SELECT * from mydatabase.carbrand WHERE brandid = " + resultSet.getInt(6);
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(SEARCH_NEW1);
            resultSet1.next();
            carBrand entityBrand = new carBrand(resultSet1.getString(2),resultSet1.getDate(3));
            resultSet1.close();
            statement1.close();
            modelArrayList.add(new carModel(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),carBodyType.rodster.getByString(resultSet.getString(5)) ,entityBrand));
        }
        return modelArrayList;
    }

    @Override
    public List<carModel> getAllByVid(int id) throws SQLException {
        String SEARCH_NEW = "SELECT * from mydatabase.carmodel Where brandid_ = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SEARCH_NEW);
        ArrayList<carModel> modelArrayList = new ArrayList<carModel>();
        while(resultSet.next())
        {
            String SEARCH_NEW1 = "SELECT * from mydatabase.carbrand WHERE brandid = " + resultSet.getInt(6);
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(SEARCH_NEW1);
            resultSet1.next();
            carBrand entityBrand = new carBrand(resultSet1.getString(2),resultSet1.getDate(3));
            resultSet1.close();
            statement1.close();
            modelArrayList.add(new carModel(resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),carBodyType.rodster.getByString(resultSet.getString(5)) ,entityBrand));
        }
        return modelArrayList;
    }


}
