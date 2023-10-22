package com.vorova.model;

import lombok.Data;

import java.util.List;

/**
 * Представление тюрьмы. Имеет primary key и название.
 */
@Data
public class PrisonModel {

    private Long id;
    private String title;
    private List<PrisonerModel> prisoners;

}
