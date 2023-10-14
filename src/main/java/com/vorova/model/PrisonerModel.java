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

    /**
     * Primary key
     */
    private Long id;
    /**
     * Имя заключенного
     */
    private String name;
    /**
     * Принадлежность к тюрьме(Prison), имеет not null constraint в базе
     */
    private Long prison_id;

}