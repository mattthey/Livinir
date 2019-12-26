package naumen.livinir.service;

import naumen.livinir.dao.AnnouncementDao;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

    @Override
    public void createAnnouncement(Map<String, String> data) throws ParseException
    {
        Announcement announcement = new Announcement();
        announcement.setCity(data.get("city"));
        announcement.setArea(data.get("area"));
        announcement.setAddress(data.get("address"));
        announcement.setLeaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("lease_date")));
                                                                                                                                                                                                                                                    announcement.setDescription(data.get("description"));
        announcement.setOwner(residentDao.findByEmail(data.get("owner")));
        announcementDao.create(announcement);
    }
}
