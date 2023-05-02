/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.services;

import io.project.app.police.domain.PoliceCar;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliceCarGenerator {

    private static final String[] MAKES = {"Chevrolet", "Ford", "Dodge", "Toyota", "Honda", "Nissan"};
    private static final String[] MODELS = {"Impala", "Taurus", "Charger", "Camry", "Accord", "Altima"};
    private static final String[] DUTIES = {"Patrol", "Traffic", "K-9", "SWAT", "Detective"};
    private static final String[] CAR_PREFIXES = {"PD", "SHP", "HPD", "LPD", "CPD", "BPD"};

    public static List<PoliceCar> generateCars(int count) {
        Random rand = new Random();
        List<PoliceCar> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String make = MAKES[rand.nextInt(MAKES.length)];
            String model = MODELS[rand.nextInt(MODELS.length)];
            int year = 2020 + rand.nextInt(4); // 2020-2023
            String duty = DUTIES[rand.nextInt(DUTIES.length)];
            String carNumber = CAR_PREFIXES[rand.nextInt(CAR_PREFIXES.length)] + "-" + String.format("%04d", rand.nextInt(10000));

            PoliceCar car = new PoliceCar(make, model, year, duty, carNumber, LocalDateTime.now());
            cars.add(car);
        }
        return cars;
    }
}
