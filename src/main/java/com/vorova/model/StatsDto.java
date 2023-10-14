package com.vorova.model;

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
    private long allPrison;
    /**
     * Общее кол-во заключенных
     */
    private long allPrisoner;
    /**
     * Коллекция тюрем
     */
    private List<PrisonModel> prisons;

}