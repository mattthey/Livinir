package naumen.livinir.dao;

import naumen.livinir.entity.Resident;

import java.util.Optional;

public interface ResidentDao
{
    /**
     * Добавление объявления в БД
     */
    public void create(Resident announcement);

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

    /**
     * Поиск пользователя с таким логином
     * @param login
     * @return
     */
    public Resident findByLogin(String login);

    /**
     * Проверка на существование пользователя с таким логином
     * @param login логин
     * @return логический тип
     */
    public Boolean existsByLogin(String login);

    /**
     * Проверка на существованя пользователя с таким email
     * @param email - email
     * @return логический тип
     */
    public Boolean existsByEmail(String email);
}
