package bci.edwijninga.a40k_api_with_scanner;

import java.util.List;

public class Scenario {

    public static final Scenario instance = new Scenario();

    public String getScanario_title() {
        return scanario_title;
    }

    public void setScanario_title(String scanario_title) {
        this.scanario_title = scanario_title;
    }

    public String getScanario_type() {
        return scanario_type;
    }

    public void setScanario_type(String scanario_type) {
        this.scanario_type = scanario_type;
    }

    public String getScanario_armies() {
        return scanario_armies;
    }

    public void setScanario_armies(String scanario_armies) {
        this.scanario_armies = scanario_armies;
    }

    public String getScanario_battlefield() {
        return scanario_battlefield;
    }

    public void setScanario_battlefield(String scanario_battlefield) {
        this.scanario_battlefield = scanario_battlefield;
    }

    public String getScanario_deployment() {
        return scanario_deployment;
    }

    public void setScanario_deployment(String scanario_deployment) {
        this.scanario_deployment = scanario_deployment;
    }

    public String getScanario_firstTurn() {
        return scanario_firstTurn;
    }

    public void setScanario_firstTurn(String scanario_firstTurn) {
        this.scanario_firstTurn = scanario_firstTurn;
    }

    public String getScanario_length() {
        return scanario_length;
    }

    public void setScanario_length(String scanario_length) {
        this.scanario_length = scanario_length;
    }

    public String getScanario_victoryConditions() {
        return scanario_victoryConditions;
    }

    public void setScanario_victoryConditions(String scanario_victoryConditions) {
        this.scanario_victoryConditions = scanario_victoryConditions;
    }

    public String scanario_title;
    public String scanario_type;
    public String scanario_armies;
    public String scanario_battlefield;
    public String scanario_deployment;
    public String scanario_firstTurn;
    public String scanario_length;
    public String scanario_victoryConditions;
}
