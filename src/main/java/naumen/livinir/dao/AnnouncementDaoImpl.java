package naumen.livinir.dao;

import naumen.livinir.entity.Announcement;
import naumen.livinir.utils.Sex;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao
{
    @Inject
    private SessionFactory sessionFactory;

    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementDaoImpl.class);

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
    public List<Announcement> getAnnouncementsByParams(String area, String city, Date leaseDate, Sex sex)
    {
        StringBuilder announcementsByParamsHql = new StringBuilder("FROM Announcement ");
        try {

            if (area != null || city != null || leaseDate != null || sex != null) {
                List<String> conditions = new ArrayList<>();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                if (area != null && !area.trim().isEmpty())
                    conditions.add(String.format("area = '%s' ", area));
                if (city != null && !city.trim().isEmpty())
                    conditions.add(String.format("city = '%s' ", city));
                if (leaseDate != null)
                    conditions.add(String.format("leaseDate = '%s' ", format.format(leaseDate.getTime())));
                if (sex != null)
                    conditions.add(String.format("owner.sex = %d ", sex.ordinal()));
                announcementsByParamsHql.append("WHERE " + String.join("AND ", conditions));
            }
        } catch (Exception e) {
            LOG.warn("have exception {} with name {}", e.getMessage(), e.getClass().toString());
        }
        LOG.info("result query " + announcementsByParamsHql.toString());
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(announcementsByParamsHql.toString());
        return query.list();
    }
}
