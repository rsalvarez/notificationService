package com.esyop.replication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import com.esyop.replication.model.RegistroReplica;
import com.esyop.replication.repository.RegistroReplicaRepository;

@Service
public class ReplicaCheckService {
    private static final Logger logger = LoggerFactory.getLogger(ReplicaCheckService.class);

    @Value("${emails.values.recivers}")
    private String recivers;
    @Value("${origen.value}")
    private String origen;

    private final RegistroReplicaRepository registroRepo;
    private final EmailService emailService;

    public ReplicaCheckService(RegistroReplicaRepository registroRepo, EmailService emailService) {
        this.registroRepo = registroRepo;
        this.emailService = emailService;
    }

    public void verificarYEnviar() throws Exception {
        LocalDateTime hoy = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        List<RegistroReplica> registrosDeHoy = registroRepo.findByFechaAndProcesado(hoy.toLocalDate(),"N");
        if (registrosDeHoy.isEmpty()) {
            logger.info ("✅ No hay registros de fallas de replicación hoy (" + hoy + ")");
            return;
        }

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2 style='color:#2c3e50;'>Reporte de replicación del día ")
            .append(hoy)
            .append("</h2>");
        html.append("<p>Los siguientes servidores no pudieron replicarse correctamente:</p>");
        html.append("<table style='border-collapse:collapse;width:100%;'>")
            .append("<thead><tr>")
            .append("<th style='border:1px solid #ddd;padding:8px;background:#f2f2f2;'>#</th>")
            .append("<th style='border:1px solid #ddd;padding:8px;background:#f2f2f2;'>Centro Verde</th>")
            .append("<th style='border:1px solid #ddd;padding:8px;background:#f2f2f2;'>Fecha</th>")
            .append("<th style='border:1px solid #ddd;padding:8px;background:#f2f2f2;'>Mensaje de error</th>")
            .append("<th style='border:1px solid #ddd;padding:8px;background:#f2f2f2;'>Severidad</th>")
            .append("</tr></thead><tbody>");
        AtomicInteger i = new AtomicInteger();
        registrosDeHoy.stream().forEach(r -> {
            r.setProcesado("S");
            html.append("<tr>")
                    .append("<td style='border:1px solid #ddd;padding:8px;'>").append(i.getAndIncrement()).append("</td>")
                    .append("<td style='border:1px solid #ddd;padding:8px;'>").append(r.getNombreCentroverde()).append("</td>")
                    .append("<td style='border:1px solid #ddd;padding:8px;'>").append(r.getFecha().format(DateTimeFormatter.ISO_DATE)).append("</td>")
                    .append("<td style='border:1px solid #ddd;padding:8px;'>").append(r.getError()).append("</td>")
                    .append("<td style='border:1px solid #ddd;padding:8px;'>").append(r.getErrorLevel()).append("</td>")
                    .append("</tr>");
        });

        html.append("</tbody></table>");
        html.append("<p style='margin-top:15px;color:#555;'>Atentamente,<br><b>Sistema de Monitoreo de Replicación</b></p>");
        html.append("</body></html>");

        emailService.enviarCorreo(recivers, "Sistema : "+ origen + " Servidores sin replicar - " + hoy, html.toString());
        logger.info("📧 Correo enviado con " + registrosDeHoy.size() + " registros de fallas (" + hoy + ")");
        registroRepo.saveAll(registrosDeHoy);
    }
}
