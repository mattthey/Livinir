package naumen.livinir.service;

import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Resident;
import naumen.livinir.utils.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class ResidentServiceImpl implements UserDetailsService
{
    private static final Logger LOG = LoggerFactory.getLogger(ResidentServiceImpl.class);

    @Autowired
    ResidentDao residentDao;

    @Autowired
    PasswordEncoder encoder;

    public UserDetails loadUserByUsername(String email)
    {
        return residentDao.findByEmail(email);
    }

    public void createResident(Map<String, String> data) throws ParseException
    {
        Resident resident = new Resident();
        resident.setPassword(encoder.encode(data.get("password")));
        resident.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("dateOfBirth")));
        resident.setEmail(data.get("email"));
        resident.setFirstName(data.get("firstName"));
        resident.setLastName(data.get("lastName"));
        resident.setSex(Sex.valueOf(data.get("sex")));
        residentDao.create(resident);
    }
}