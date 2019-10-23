package naumen.livinir.service;

import naumen.livinir.dao.AnnouncementDao;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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
}
