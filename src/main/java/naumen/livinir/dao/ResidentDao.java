package naumen.livinir.dao;

import naumen.livinir.entity.Resident;

public interface ResidentDao
{
    /**
     * Добавление объявления в БД
     */
    public void create(Resident announcement);

    public Resident create();

    /**
     * Удаление объявления из БД
     */
    public void delete(Resident announcement);

    /**
     * Изменение объявления в БД
     */
    public void update(Resident announcement);

    /**
     * Получение объявление из БД по ID
     */
    public Resident getById(long id);
}
