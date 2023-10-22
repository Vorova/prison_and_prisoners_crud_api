package com.vorova.model;

import lombok.Data;

/**
 * Модель заключенного. <br>
 * Каждый заключенный имеет свой уникальный id, имя и принадлежность к тюрьме. <br>
 * Данная принадлежность отражает связь Many To One и в базе реализована, как
 * REFERENCE KEY на сущность Prison(primary key) <br>
 *
 * У заключенного необходима принадлежность к тюрьме(иначе он не заключенный) - в базе
 * это обозначено как наличие constraint - Not Null на поле prison_id
 */
@Data
public class PrisonerModel {

    private Long id;
    private String name;
    private Long prisonId;

}