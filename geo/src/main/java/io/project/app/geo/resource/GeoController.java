/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.geo.resource;

import io.project.app.geo.services.GpsDataGenerator;
import io.project.app.geo.model.GpsData;
import io.project.app.geo.producer.GeoProducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
public class GeoController {

    private final GeoProducer geoProducer;

    @Autowired
    public GeoController(GeoProducer geoProducer) {
        this.geoProducer = geoProducer;
    }

    @PostMapping(path = "/send")
    public ResponseEntity create() {
        GpsData gpsData = GpsDataGenerator.generateWithEvent();
        geoProducer.sendMessage(gpsData.toString());
        return ResponseEntity.status(HttpStatus.OK).body("Done");

    }

}
