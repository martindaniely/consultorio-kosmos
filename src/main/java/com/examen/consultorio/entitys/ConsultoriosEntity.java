package com.examen.consultorio.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "consultorios")
@Getter
@Setter
@ToString
public class ConsultoriosEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "piso")
    private String piso;


}
