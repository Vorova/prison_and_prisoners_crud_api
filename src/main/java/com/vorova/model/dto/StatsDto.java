package com.vorova.model.dto;

import com.vorova.model.entity.PrisonModel;
import lombok.Data;

import java.util.List;

/**
 * Представление статистики
 */
@Data
public class StatsDto {

    /**
     * Общее кол-во тюрем
     */
    private long countAllPrisons;
    /**
     * Общее кол-во заключенных
     */
    private long countAllPrisoners;
    /**
     * Коллекция тюрем
     */
    private List<PrisonModel> prisons;

}