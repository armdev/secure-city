/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.services;

import io.project.app.police.domain.PoliceAuditLog;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class PoliceAuditLogService {

    private final MongoTemplate mongoTemplate;

    public PoliceAuditLogService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void saveAuditLog(String entityId, String entityName, String action) {
        PoliceAuditLog auditLog = new PoliceAuditLog(entityId, entityName, action);
        mongoTemplate.save(auditLog);
    }

}
