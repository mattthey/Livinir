package naumen.livinir.dao;

import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao
{
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Announcement announcement)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(announcement);
        session.flush();
    }

    @Override
    @Transactional
    public void delete(Announcement announcement)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(announcement);
        session.flush();
    }

    @Override
    @Transactional
    public void update(Announcement announcement)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(announcement);
        session.flush();
    }

    @Override
    @Transactional
    public Announcement getById(long id)
    {
        Session session = sessionFactory.getCurrentSession();
        Announcement announcement = session.get(Announcement.class, id);
        return announcement;
    }

    @Override
    @Transactional
    public List<Announcement> getAnnouncements(int count)
    {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Announcement> criteriaQuery = criteriaBuilder.createQuery(Announcement.class);
        Root<Announcement> root = criteriaQuery.from(Announcement.class);
        criteriaQuery.select(root);
        return session.createQuery(criteriaQuery).setMaxResults(count).getResultList();
    }

    @Override
    @Transactional
    public void addResident(Resident resident, Announcement announcement)
    {
    }
}
