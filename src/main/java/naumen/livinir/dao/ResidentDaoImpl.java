package naumen.livinir.dao;

import naumen.livinir.entity.Resident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ResidentDaoImpl implements ResidentDao
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Resident resident)
    {
        Session session = getCurrentSession();
        session.save(resident);
        session.flush();
    }

    @Override
    @Transactional
    public void delete(Resident resident)
    {
        Session session = getCurrentSession();
        session.delete(resident);
        session.flush();
    }

    @Override
    @Transactional
    public void update(Resident resident)
    {
        Session session = getCurrentSession();
        session.save(resident);
        session.flush();
    }

    @Override
    @Transactional
    public Resident getById(long id)
    {
        Session session = getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                     .where(criteriaBuilder.equal(root.get("id"), id));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    @Transactional
    public Resident getByEmail(String email)
    {
        Session session = getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("email"), email));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    @Transactional
    public Resident findByEmail(String email)
    {
        Session session = getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("email"), email));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email)
    {
        Session session = getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("email"), email));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0;
    }

    @Override
    public boolean checkEmailPasswordUser(String email, String password)
    {
        Session session = getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and (
                        criteriaBuilder.equal(root.get("email"), email),
                        criteriaBuilder.equal(root.get("password"), password)
                ));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0;
    }

    private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }
}
