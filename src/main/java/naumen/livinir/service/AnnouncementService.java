package naumen.livinir.service;

import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс сервисного класса для работы с резидентами
 *
 * @since 14.10.2019
 * @author mattthey
 */
public interface AnnouncementService
{

    /**
     * Добавить сожителя к объявлению
     * @param resident сожитель
     * @param announcement объявление к которому добавляем
     */
    public void addResident(Resident resident, Announcement announcement);

    public void addAnnouncement(Announcement announcement);

    public List<Announcement> getAnnouncements(int count);

    public void createAnnouncement(Map<String, String> data) throws ParseException;
}
