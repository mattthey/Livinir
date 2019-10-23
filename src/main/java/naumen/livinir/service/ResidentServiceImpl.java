package naumen.livinir.service;

import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResidentServiceImpl implements ResidentService
{
    @Inject
    private ResidentDao residentDao;

    @Override
    public Resident createResidentFromParamentr(Map<String, Object> mapParams)
    {
        Resident resident = new Resident();
//        resident.setDateOfBirth(new Date((String) mapParams.get("dateOfPirthday")));
        resident.setEmail(String.valueOf(mapParams.get("email")));
        resident.setFirstName(String.valueOf(mapParams.get("firstName")));
        resident.setLastNmae(String.valueOf(mapParams.get("lastName")));
        resident.setPassword(String.valueOf(mapParams.get("password")));
//        resident.setMyAnnouncements((List<Announcement>)(mapParams.get("myAnnouncements")));
        residentDao.create(resident);
        return resident;
    }
}
