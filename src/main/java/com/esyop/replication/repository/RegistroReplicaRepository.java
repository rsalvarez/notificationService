package com.example.replicacionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.replicacionservice.model.RegistroReplica;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroReplicaRepository extends JpaRepository<RegistroReplica, Long> {
    List<RegistroReplica> findByFecha(LocalDate fecha);
}
