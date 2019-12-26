package naumen.livinir.api;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class HttpHelper
{
    public static Gson gson = new Gson();
    /**
     * Получение тела запроса в виде map-объекта
     * @param request запрос
     * @return тело запроса(map-объект)
     */
    public static Map<String, String> getRequestJSONContent(HttpServletRequest request) throws IOException
    {
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
