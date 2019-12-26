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
     * Получение объявление из БД по email
     */
    public Resident getByEmail(String email);

    /**
     * Поиск пользователя с таким логином
     * @param email
     * @return
     */
    public Resident findByEmail(String email);

    /**
     * Проверка на существование пользователя с таким логином или email
     * @param email
     * @return логический тип
     */
    public boolean existsByEmail(String email);

    public boolean checkEmailPasswordUser(String email, String password);
}
