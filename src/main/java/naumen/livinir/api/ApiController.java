package naumen.livinir.api;

import com.google.gson.Gson;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Announcement;
import naumen.livinir.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ApiController
{
    @Inject
    private AnnouncementService announcementService;

    @Inject
    private ResidentDao residentDao;

    @GetMapping("/getAnnouncement")
    public List<Announcement> getAnnoucements(HttpServletRequest request) throws IOException
    {
        Map<String, Object> jsonRequest = getRequestJSONContent(request);
        int count = Integer.parseInt((String) jsonRequest.getOrDefault("count", 1));
        return announcementService.getAnnouncements(count);
    }

    @PostMapping("/add-announcement")
    public void addAnnouncement(Announcement announcement)
    {
        announcementService.addAnnouncement(announcement);
    }

    /**
     * Получение тела запроса в виде map-объекта
     * @param request запрос
     * @return тело запроса(map-объект)
     */
    static Map<String, Object> getRequestJSONContent(HttpServletRequest request) throws IOException
    {
        final Gson gson = new Gson();
        try (final Reader reader = new InputStreamReader(request.getInputStream()))
        {
            return gson.fromJson(reader, Map.class);
        }
        catch (IOException ex)
        {
            throw new IOException("Error converting request data from JSON", ex);
        }
    }
}
