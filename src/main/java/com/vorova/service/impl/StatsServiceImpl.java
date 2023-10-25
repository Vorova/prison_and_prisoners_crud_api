package com.vorova.service.impl;

import com.vorova.dao.PrisonDao;
import com.vorova.dao.impl.PrisonDaoImpl;
import com.vorova.model.dto.StatsDto;
import com.vorova.service.StatsService;

/**
 * Реализация сервисного слоя для StatsModel.
 */
public class StatsServiceImpl implements StatsService {

    private PrisonDao prisonDao = new PrisonDaoImpl();

    /**
     * Этот метод занимается сбором статистики
     * @return StatsDto
     */
    @Override
    public StatsDto get() {
        var stats = new StatsDto();
        stats.setPrisons(prisonDao.findAll());
        stats.setCountAllPrisons(stats.getPrisons().size());
//        stats.setCountAllPrisoners(
//            stats.getPrisons().stream()
//                    .flatMap(prison ->
//                            prison.getPrisoners().stream()
//                    ).count()
//        );
        return stats;
    }
}
