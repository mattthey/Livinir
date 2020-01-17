package naumen.livinir.dao;

import naumen.livinir.entity.Announcement;
import naumen.livinir.entity.Resident;
import naumen.livinir.utils.Sex;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * DAO - интерфейс для взаимодействия с БД
 */
public interface AnnouncementDao
{
    /**
     * Добавление объявления в БД
     * @param announcement объявление
     * @return - Созданное объявление (возможно нам будет нужно только его ID для ссылки)
     */
    public void create(Announcement announcement);

    /**
     * Удаление объявления из БД
     * @param announcement объявление, которое нужно удалить
     * @return long = id удаленного объявления
     */
    public void delete(Announcement announcement);

    /**
     * Изменение объявления в БД
     * @param announcement изменение параметров объявления
     * @return Announcement - измененное объявление
     */
    public void update(Announcement announcement);

    /**
     * Получение объявление из БД по ID
     * @param id - ID
     * @return объявление с переданным ID
     */
    public Announcement getById(long id);

    /**
     * Возвращает первые
     * @param count
     * @return
     */
    public List<Announcement> getAnnouncements(int count);

    public List<Announcement> getAnnouncementsByParams(String area, String city, Date leaseDate, Sex sex);

}
