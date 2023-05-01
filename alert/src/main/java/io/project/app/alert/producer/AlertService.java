package io.project.app.alert.producer;

/**
 *
 * @author armena
 */
public class AlertService {

    @SuppressWarnings("empty-statement")
    public void router(String alert) {
        switch (alert) {
            case "A" ->
                System.out.println("Parameter is A");
            case "B" ->
                System.out.println("Parameter is b");
            default ->
                System.out.println("Parameter is unknown");
        };
    }

}
