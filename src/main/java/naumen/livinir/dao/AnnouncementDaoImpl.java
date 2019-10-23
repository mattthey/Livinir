package naumen.livinir.dao;

import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao
{
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Announcement create(Announcement announcement)
    {
        Session session = sessionFactory.getCurrentSession();
        Announcement createdAnnouncement = (Announcement) session.save(announcement);
        session.flush();
        return createdAnnouncement;
    }

    @Override
    @Transactional
    public long delete(Announcement announcement)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(announcement);
        session.flush();
        return announcement.getId();
    }

    @Override
    @Transactional
    public Announcement update(Announcement announcement)
    {
        Session session = sessionFactory.getCurrentSession();
        session.update(announcement);
        session.flush();
        return announcement;
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
    public void addResident(Resident resident, Announcement announcement)
    {
    }
}
