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
        Session session = sessionFactory.getCurrentSession();
        session.save(resident);
        session.flush();
    }

    @Override
    @Transactional
    public void delete(Resident resident)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(resident);
        session.flush();
    }

    @Override
    @Transactional
    public void update(Resident resident)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(resident);
        session.flush();
    }

    @Override
    @Transactional
    public Resident getById(long id)
    {
        Session session = sessionFactory.getCurrentSession();

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
    public Boolean existsByEmail(String email)
    {
        Session session = sessionFactory.getCurrentSession();

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
    @Transactional
    public Resident findByLogin(String login)
    {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("login"), login));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    @Transactional
    public Boolean existsByLogin(String login)
    {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Resident> criteriaQuery = criteriaBuilder.createQuery(Resident.class);
        Root<Resident> root = criteriaQuery.from(Resident.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("login"), login));
        Query query = session.createQuery(criteriaQuery).setMaxResults(1);
        List<Resident> results = (List<Resident>) query.getResultList();
        return results.size() > 0;
    }
}
