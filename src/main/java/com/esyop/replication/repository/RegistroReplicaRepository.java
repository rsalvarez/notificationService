package com.esyop.replication.repository;

import com.esyop.replication.model.RegistroReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroReplicaRepository extends JpaRepository<RegistroReplica, Long> {
    List<RegistroReplica> findByFecha(LocalDate fecha);
    List<RegistroReplica> findByFechaAndProcesado(LocalDate fecha, String procesado);
}
