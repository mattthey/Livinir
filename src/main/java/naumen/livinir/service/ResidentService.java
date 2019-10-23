package naumen.livinir.service;

import naumen.livinir.entity.Resident;

import java.util.Map;

/**
 * Интерфейс сервисного класса для работы с резидентами
 *
 * @since 14.10.2019
 * @author mattthey
 */
public interface ResidentService
{
    public Resident createResidentFromParamentr(Map<String, Object> mapParams);
}
