package io.project.app.police.helpers;

import io.project.app.police.domain.PoliceOfficer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliceOfficerGenerator {

    private static final String[] FIRST_NAMES = {"John", "Jane", "William", "Emma", "Robert", "Emily", "Michael", "Olivia", "David", "Sophia"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Brown", "Lee", "Garcia", "Martinez", "Davis", "Taylor", "Anderson", "Harris"};
    private static final String[] BADGE_PREFIXES = {"PD", "SHP", "HPD", "LPD", "CPD", "BPD"};

    public static List<PoliceOfficer> generateOfficers(int count) {
        Random rand = new Random();
        List<PoliceOfficer> officers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
            String name = firstName + " " + lastName;
            int age = 25 + rand.nextInt(20); // 25-44
            String badgeNumber = BADGE_PREFIXES[rand.nextInt(BADGE_PREFIXES.length)] + "-" + String.format("%04d", rand.nextInt(10000));

            PoliceOfficer officer = new PoliceOfficer(name, badgeNumber, age);
            officers.add(officer);
        }
        return officers;
    }
}
