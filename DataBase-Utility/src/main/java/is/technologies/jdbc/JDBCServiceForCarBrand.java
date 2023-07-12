package is.technologies.jdbc;

import is.technologies.entities.carBrand;
import is.technologies.interfaceService.IServiceJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceForCarBrand implements IServiceJDBC<carBrand> {
    public JDBCServiceForCarBrand(String URL,String USER,String PASSWORD) throws SQLException
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
    public carBrand save(carBrand entity) throws SQLException {
        String INSERT_NEW = "INSERT INTO mydatabase.CarBrand(brandname, foundingdate) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,entity.getName());
        preparedStatement.setDate(2,entity.getFoundingDate());
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        entity.setId(resultSet.getInt(1));

        return entity;
    }

    @Override
    public void deleteById(long id) throws SQLException {
        String DELETE_NEW = "DELETE FROM mydatabase.CarBrand WHERE mydatabase.carbrand.brandid = " + id;
        Statement statement = connection.createStatement();
        statement.execute(DELETE_NEW);

    }

    @Override
    public void deleteByEntity(carBrand entity) throws SQLException {
        String DELETE_NEW = "DELETE FROM mydatabase.CarBrand " +
                "WHERE mydatabase.carbrand.brandname =? AND  mydatabase.carbrand.foundingdate =?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NEW);
        preparedStatement.setString(1,entity.getName());
        preparedStatement.setDate(2,entity.getFoundingDate());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteAll() throws SQLException {
        String DELETE_NEW = "DELETE FROM mydatabase.CarBrand";
        Statement statement = connection.createStatement();
        statement.execute(DELETE_NEW);

    }

    @Override
    public carBrand update(carBrand entity,int id) throws SQLException {
        String UPDATE_NEW = "UPDATE mydatabase.carbrand SET brandname = ?, foundingdate = ? WHERE brandid = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEW);
        preparedStatement.setString(1,entity.getName());
        preparedStatement.setDate(2,entity.getFoundingDate());
        preparedStatement.setInt(3,id);
        preparedStatement.execute();
        return entity;
    }

    @Override
    public carBrand getById(long id) throws SQLException {
        String SEARCH_NEW = "SELECT * from mydatabase.carbrand WHERE brandid = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SEARCH_NEW);
        resultSet.next();
        carBrand entity = new carBrand(resultSet.getString(2),resultSet.getDate(3));
        return entity;
    }

    @Override
    public List<carBrand> getAll() throws SQLException {
        String SEARCH_NEW = "SELECT * from mydatabase.carbrand ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SEARCH_NEW);
        ArrayList<carBrand> brandArrayList = new ArrayList<carBrand>();
        while(resultSet.next())
        {
            String name = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            brandArrayList.add(new carBrand(name,date));
        }
        return brandArrayList;
    }

    @Override
    public List<carBrand> getAllByVid(int id) {
        return null;
    }
}
