package com.esyop.replication.scheduler;

import com.esyop.replication.service.ReplicaCheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReplicaCheckScheduler {

    private final ReplicaCheckService replicaCheckService;

    public ReplicaCheckScheduler(ReplicaCheckService replicaCheckService) {
        this.replicaCheckService = replicaCheckService;
    }

    @Scheduled(cron = "${value.scheduled}")
    public void ejecutarTareaDiaria() throws Exception {
        replicaCheckService.verificarYEnviar();
    }
}
