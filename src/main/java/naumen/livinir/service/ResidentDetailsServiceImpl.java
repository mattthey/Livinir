package naumen.livinir.service;

import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResidentDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    ResidentDao residentDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        Resident resident = residentDao.findByLogin(login);
        if (resident == null)
        {
            throw new UsernameNotFoundException("User Not Found with -> login or email : " + login);
        }
        return ResidentPrinciple.build(resident);
    }
}