package com.vorova.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель заключенного. <br>
 * Каждый заключенный имеет свой уникальный id, имя и принадлежность к тюрьме. <br>
 * Данная принадлежность отражает связь Many To One и в базе реализована, как
 * REFERENCE KEY на сущность Prison(primary key) <br>
 *
 * У заключенного необходима принадлежность к тюрьме(иначе он не заключенный) - в базе
 * это обозначено как наличие constraint - Not Null на поле prison_id
 */
@Getter
@Setter
@Entity
@Table(name = "prisoner")
public class PrisonerModel {

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private PrisonModel prisonId;

}