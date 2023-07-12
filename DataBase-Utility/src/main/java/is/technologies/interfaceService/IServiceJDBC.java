package is.technologies.interfaceService;

import java.sql.SQLException;
import java.util.List;

public interface IServiceJDBC<T> {
    public T save(T entity) throws SQLException;
    public void deleteById(long id) throws SQLException;
    public void deleteByEntity(T entity) throws SQLException;
    public void deleteAll() throws SQLException;
    public T update(T entity,int id) throws SQLException;
    public T getById(long id) throws SQLException;
    public List<T> getAll() throws SQLException;
    public List<T> getAllByVid(int id) throws SQLException;
}
