package com.esyop.replication.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "registros_replica")
public class RegistroReplica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCentroVerde;
    private String nombreCentroverde;
    private String error;
    private LocalDate fecha;
    private String procesado;
    private ErrorLevel errorLevel;

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(ErrorLevel errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getIdCentroVerde() {
        return idCentroVerde;
    }

    public void setIdCentroVerde(Long idCentroVerde) {
        this.idCentroVerde = idCentroVerde;
    }

    public String getProcesado() {
        return procesado;
    }

    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCentroverde() { return nombreCentroverde; }
    public void setNombreCentroverde(String nombreCentroverde) { this.nombreCentroverde = nombreCentroverde; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
