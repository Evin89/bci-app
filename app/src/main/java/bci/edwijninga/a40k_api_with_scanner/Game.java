package bci.edwijninga.a40k_api_with_scanner;

import android.app.Application;
import android.widget.EditText;

import java.util.List;

class Game {

    public static final Game instance = new Game();


    public String playerOne;
    public String playerTwo;
    public int scorePlayerOne;
    public int scorePlayerTwo;
    public String armyPlayerOne;
    public String armyPlayerTwo;
    public List playerOneObjectives;
    public List playerTwoObjectives;

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setScorePlayerOne(int scorePlayerOne) {
        this.scorePlayerOne = scorePlayerOne;
    }

    public void setScorePlayerTwo(int scorePlayerTwo) {
        this.scorePlayerTwo = scorePlayerTwo;
    }

    public void setArmyPlayerOne(String armyPlayerOne) {
        this.armyPlayerOne = armyPlayerOne;
    }

    public void setArmyPlayerTwo(String armyPlayerTwo) {
        this.armyPlayerTwo = armyPlayerTwo;
    }

    public void setPlayerOneObjectives(List playerOneObjectives) {
        this.playerOneObjectives = playerOneObjectives;
    }

    public void setPlayerTwoObjectives(List playerTwoObjectives) {
        this.playerTwoObjectives = playerTwoObjectives;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public int getScorePlayerOne() {
        return scorePlayerOne;
    }

    public int getScorePlayerTwo() {
        return scorePlayerTwo;
    }

    public String getArmyPlayerOne() {
        return armyPlayerOne;
    }

    public String getArmyPlayerTwo() {
        return armyPlayerTwo;
    }

    public List getPlayerOneObjectives() {
        return playerOneObjectives;
    }

    public List getPlayerTwoObjectives() {
        return playerTwoObjectives;
    }



}
