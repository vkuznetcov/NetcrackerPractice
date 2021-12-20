package buildings.exceptions;

import java.io.IOException;

public class BuildingUnderArrestException extends IOException {
    public BuildingUnderArrestException() {
        String message = "Building is arrested!";
        try {
            throw new Exception(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
