package com.example.replicacionservice.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.replicacionservice.service.ReplicaCheckService;

@Component
public class ReplicaCheckScheduler {

    private final ReplicaCheckService replicaCheckService;

    public ReplicaCheckScheduler(ReplicaCheckService replicaCheckService) {
        this.replicaCheckService = replicaCheckService;
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void ejecutarTareaDiaria() throws Exception {
        replicaCheckService.verificarYEnviar();
    }
}
