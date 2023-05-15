/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author user
 */
@Data
@Document(collection = "police_audit_logs")
public class PoliceAuditLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 9049723802624970610L;

    @Id
    private String id;

    private String entityId;

    private String entityName;

    private String action;

    private LocalDateTime timestamp;

    public PoliceAuditLog(String entityId, String entityName, String action) {
        this.entityId = entityId;
        this.entityName = entityName;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }
}
