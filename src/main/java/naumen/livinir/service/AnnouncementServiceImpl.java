package naumen.livinir.service;

import naumen.livinir.dao.AnnouncementDao;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService
{
    @Inject
    private AnnouncementDao announcementDao;

    @Inject
    private ResidentDao residentDao;

    @Override
    public void addResident(Resident resident, Announcement announcement)
    {
    }

    @Override
    public void addAnnouncement(Announcement announcement)
    {
        announcementDao.create(announcement);
    }

    @Override
    public List<Announcement> getAnnouncements(int count)
    {
        return announcementDao.getAnnouncements(count);
    }
}
