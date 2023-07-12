package is.technologies.hibernate;

import is.technologies.entities.carBrand;
import is.technologies.entities.carModel;
import is.technologies.interfaceService.IServiceHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateServiceForCarBrand implements IServiceHibernate<carBrand,Object> {
    public HibernateServiceForCarBrand()
    {
        configuration = new Configuration().addAnnotatedClass(carBrand.class).addAnnotatedClass(carModel.class);
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
    }
    private Configuration configuration;
    private Session session;
    private SessionFactory sessionFactory;
    @Override
    public carBrand save(carBrand entity) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    @Override
    public void deleteById(long id) {
        session = sessionFactory.getCurrentSession();
        int _id = ((int) id);
        session.beginTransaction();
        carBrand carBrand=session.get(carBrand.class, _id);
        session.delete(carBrand);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteByEntity(carBrand entity) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        carBrand carBrand=session.get(carBrand.class, entity.getId());
        session.delete(carBrand);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE from carBrand").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public carBrand update(carBrand entity) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        carBrand carBrand=session.get(carBrand.class, entity.getId());
        carBrand.setName(entity.getName());
        carBrand.setFoundingDate(entity.getFoundingDate());
        session.getTransaction().commit();
        session.close();

        return entity;
    }

    @Override
    public carBrand getById(long id) {
            session = sessionFactory.getCurrentSession();
            int _id = ((int) id);
            session.beginTransaction();
            carBrand _carBrand = session.get(carBrand.class,_id);
            session.getTransaction().commit();
            session.close();
            return _carBrand;
    }

    @Override
    public ArrayList<carBrand> getAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ArrayList<carBrand> arrayList =new ArrayList<carBrand>(session.createQuery("from carBrand ").getResultList());
        session.getTransaction().commit();
        session.close();
        return  arrayList;
    }

    @Override
    public List<carBrand> getAllByVId(Object entity) {
        return null;
    }
}
