package com.adoptacr.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "mascota")
public class Mascota implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    private String nombre;
    private String raza;
    private String ubicacion;
    private String estado;

    private Integer edad;
    @Column(columnDefinition = "TEXT")
    private String historia;
    @Column(name = "foto_url")
    private String fotoUrl;
}
