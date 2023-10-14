package com.vorova.model;

import lombok.Data;

import java.util.List;

/**
 * Представление тюрьмы. Имеет primary key и название.
 */
@Data
public class PrisonModel {

    /**
     * Primary key
     */
    private Long id;
    /**
     * Название тюрьмы
     */
    private String title;
    /**
     * Заключенные
     */
    private List<PrisonerModel> prisoners;

}
