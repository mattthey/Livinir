package naumen.livinir.dao;

import naumen.livinir.entity.Resident;
import naumen.livinir.utils.Sex;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class ResidentDaoImpl implements ResidentDao
{
    @Autowired
    private SessionFactory sessionFactory;

    // TODO хочется работать со спринговой анотацией Transactional (но эта тварь чет не заводится)

    @Override
    @Transactional
    public void create(Resident resident)
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(resident);
        tx.commit();
    }

    @Override
    @Transactional
    public Resident create()
    {
        Resident resident = new Resident();
        resident.setPassword("sad");
        resident.setLastNmae("sadsadas");
        resident.setFirstName("asdasdasd");
        resident.setEmail("asdasdsadsadasd");
        resident.setSex(Sex.MALE);
        resident.setDateOfBirth(new Date());

        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        session.save(resident);
        session.flush();
//        tx.commit();
        return resident;
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
    public Resident getById(long id)
    {
        return null;
    }
}
