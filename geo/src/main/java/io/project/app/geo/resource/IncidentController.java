/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.geo.resource;

import com.google.gson.Gson;
import io.project.app.geo.services.GpsDataGenerator;
import io.project.app.geo.incident.IncidentData;
import io.project.app.geo.producer.IncidentProducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/events")
public class IncidentController {
    
    private final IncidentProducer geoProducer;
    
    @Autowired
    public IncidentController(IncidentProducer geoProducer) {
        this.geoProducer = geoProducer;
    }
    
    @GetMapping(path = "/send")
    public ResponseEntity create() {
        IncidentData gpsData = GpsDataGenerator.generateWithEvent();
        Gson gson = new Gson();
        geoProducer.sendMessage(gson.toJson(gpsData), gpsData.getTransactionId());
        return ResponseEntity.status(HttpStatus.OK).body("Done");
        
    }
    
}
