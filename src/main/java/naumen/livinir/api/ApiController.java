package naumen.livinir.api;

import com.google.common.collect.ImmutableMap;
import naumen.livinir.dao.AnnouncementDao;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import naumen.livinir.service.JwtServiceImpl;
import naumen.livinir.service.AnnouncementService;
import naumen.livinir.utils.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class ApiController
{
    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    AnnouncementDao announcementDao;

    @Autowired
    ResidentDao residentDao;

    @Autowired
    JwtServiceImpl jwtProvider;

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/getAnnouncement")
    public List<Announcement> getAnnoucements(HttpServletRequest request) throws IOException
    {
        Map<String, String> jsonRequest = HttpHelper.getRequestJSONContent(request);
        int count = Integer.parseInt(jsonRequest.getOrDefault("count", "1"));
        return announcementService.getAnnouncements(count);
    }

    @PostMapping("/addAnnouncement")
    public void addAnnouncement(HttpServletRequest request) throws IOException, ParseException
    {
        announcementService.createAnnouncement(HttpHelper.getRequestJSONContent(request));
    }

    @GetMapping("/getAds")
    public Map<String, List<Map<String, String>>> getAds()
    {
        List<Map<String, String>> result = new ArrayList<>();
        List<Announcement> resReq = announcementService.getAnnouncements(15);
        for (Announcement a : resReq)
        {
            result.add(ImmutableMap.<String, String>builder()
                    .put("city", a.getCity())
                    .put("area", a.getArea())
                    .put("address", a.getAddress())
                    .put("lease_date", a.getLeaseDate().toString())
                    .put("description", a.getDescription())
                    .put("owner", a.getOwner().getEmail())
            .build());
        }
        return Collections.singletonMap("ads", result);
    }

    @GetMapping("/getAdsParams")
    public Map<String, List<Map<String, String>>> getAdsParams(HttpServletRequest request)
    {
        String ares = request.getParameter("area");
        String city = request.getParameter("city");
        Date leaseDate = null;
        Sex sex = null;
        try {
            leaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("lease_date"));
        } catch (ParseException ex) {
            String value = request.getParameter("lease_date");
            if (value != null && !value.trim().isEmpty())
            {
                logger.warn("Can't parse {} to Date", value);
            }
        }
        try {
            sex = Sex.valueOf(request.getParameter("sex"));
        } catch (Exception e) {
            String value = request.getParameter("sex");
            if (value != null && !value.trim().isEmpty())
            {
                logger.warn("Can't parse {} to Sex enum", value);
            }
        }
        List<Announcement> resReq = Collections.emptyList();
        try {
            resReq = announcementDao.getAnnouncementsByParams(ares, city, leaseDate, sex);
        } catch (Exception e) {
            logger.error("Error in search getAdsParams {}", e.getMessage());
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (Announcement a : resReq)
        {
            result.add(ImmutableMap.<String, String>builder()
                    .put("city", a.getCity())
                    .put("area", a.getArea())
                    .put("address", a.getAddress())
                    .put("lease_date", a.getLeaseDate().toString())
                    .put("description", a.getDescription())
                    .put("owner", a.getOwner().getEmail())
                    .build());
        }
        return Collections.singletonMap("ads", result);
    }

    @GetMapping("/profile")
    public Map<String, String> getUser(HttpServletRequest request) throws IOException
    {
        String email = request.getParameter("email");
        Resident resident = residentDao.getByEmail(email);
        return ImmutableMap.of(
                "email", resident.getEmail(),
                "firstName", resident.getFirstName(),
                "lastName", resident.getLastName(),
                "dateOfBirth", resident.getDateOfBirth().toString(),
                "sex", resident.getSex().toString()
        );
    }
}
