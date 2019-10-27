package naumen.livinir.api;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import naumen.livinir.dao.ResidentDao;
import naumen.livinir.entity.Resident;
import naumen.livinir.service.AnnouncementService;
import naumen.livinir.service.ResidentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ApiController
{
    @Inject
    private ResidentService residentService;

    @Inject
    private AnnouncementService announcementService;

    @Inject
    private ResidentDao residentDao;

    // TODO вынести тупую обработку JSON в отдльный вспомогательный модуль

    @PostMapping(value = "/create-resident", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resident createResident(HttpServletRequest request) throws IOException
    {
//        Map<String, Object> gsonMap = getRequestJSONContent(request);
//        Resident resident = residentService.createResidentFromParamentr(gsonMap);
        Resident resident = residentDao.create();
        return resident;
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
        catch (IOException | JsonIOException | UnsupportedOperationException ex)
        {
            throw new IOException("Error converting request data from JSON", ex);
        }
    }

//    private final VisitsRepository visitsRepository;
//
//    public ApiController(VisitsRepository visitsRepository)
//    {
//        this.visitsRepository = visitsRepository;
//    }
//
//    @GetMapping("/visits")
//    public Iterable<Visit> getVisits()
//    {
//        return visitsRepository.findAll();
//    }
}
