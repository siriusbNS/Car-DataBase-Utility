package is.technologies.interfaceService;

import java.util.ArrayList;
import java.util.List;

public interface IServiceHibernate<T,V> {
    public T save(T entity);
    public void deleteById(long id);
    public void deleteByEntity(T entity);
    public void deleteAll();
    public T update(T entity);
    public T getById(long id);
    public ArrayList<T> getAll();

    public List<T> getAllByVId(V entity);
}
