package com.example.replicacionservice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registros_replica")
public class RegistroReplica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCentroverde;
    private LocalDate fecha;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCentroverde() { return nombreCentroverde; }
    public void setNombreCentroverde(String nombreCentroverde) { this.nombreCentroverde = nombreCentroverde; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
