package is.technologies.hibernate;

import is.technologies.entities.carBrand;
import is.technologies.entities.carModel;
import is.technologies.interfaceService.IServiceHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class HibernateServiceForCarModel implements IServiceHibernate<carModel,carBrand> {
    public HibernateServiceForCarModel()
    {
        configuration = new Configuration().addAnnotatedClass(carBrand.class).addAnnotatedClass(carModel.class);
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.getCurrentSession();
    }
    private Configuration configuration;
    private Session session;
    private SessionFactory sessionFactory;
    @Override
    public carModel save(carModel entity) {
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
        carModel carModel=session.get(carModel.class, _id);
        session.delete(carModel);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteByEntity(carModel entity) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        carModel carModel=session.get(carModel.class, entity.getId());
        session.delete(carModel);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE from carModel ").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public carModel update(carModel entity) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        carModel carModel=session.get(carModel.class, entity.getId());
        carModel.setCarBrand(entity.getCarBrand());
        carModel.setModelName(entity.getModelName());
        carModel.setLength(entity.getLength());
        carModel.setWeidth(entity.getWeidth());
        carModel.setCarBodyType(entity.getCarBodyType());
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    @Override
    public carModel getById(long id) {
        session = sessionFactory.getCurrentSession();
        int _id = ((int) id);
        session.beginTransaction();
        carModel carModel = session.get(carModel.class,_id);
        session.getTransaction().commit();
        session.close();
        return carModel;
    }

    @Override
    public ArrayList<carModel> getAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ArrayList<carModel> arrayList =new ArrayList<carModel>(session.createQuery("from carModel ").getResultList());
        session.getTransaction().commit();
        session.close();
        return  arrayList;
    }

    @Override
    public ArrayList<carModel> getAllByVId(carBrand entity) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        ArrayList<carModel> arrayList =new ArrayList<carModel>(session.createQuery("from carModel where carBrand ="+entity.getId()).getResultList());
        session.getTransaction().commit();
        session.close();
        return  arrayList;
    }
}
