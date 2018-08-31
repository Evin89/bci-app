package bci.edwijninga.a40k_api_with_scanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class GenerateScenarioActivity extends AppCompatActivity {

    String TAG = "Debug";
    public String id;
    public String title;

    public TextView generatedScenarioTV;
    public TextView generatedScenarioTypeTV;
    public TextView generateScenarioArmiesTV;
    public TextView genereratedScenarioBattlefieldTV;
    public TextView generatedScenarioDeploymentTV;
    public TextView generatedScenarioFirstTurnTV;
    public TextView generatedScenarioVictoryConditionsTV;
    public TextView generatedScenarioLengthTV;

    String scenarioTitle;
    String scenarioArmies;
    String scenarioType;
    String scenarioBattlefield;
    String scenarioFirstTurn;
    String scenarioLength;
    String scenarioVictoryConditions;
    String scenarioDeployment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_scenario);

        final Button startGameBtn = findViewById(R.id.startGameBtn);

        generatedScenarioTV = findViewById(R.id.generatedScenarioTV);
        generatedScenarioTypeTV = findViewById(R.id.generatedScenarioType);
        generateScenarioArmiesTV = findViewById(R.id.generateScenarioArmies);
        genereratedScenarioBattlefieldTV = findViewById(R.id.generatedScenarioBattlefield);
        generatedScenarioDeploymentTV = findViewById(R.id.generatedScenarioDeployment);
        generatedScenarioFirstTurnTV = findViewById(R.id.generatedScenarioFirstTurn);
        generatedScenarioLengthTV = findViewById(R.id.generatedScenarioLength);
        generatedScenarioVictoryConditionsTV = findViewById(R.id.generatedScenarioVictoryConditions);

        /* Get all data from the Game class object */
        int playerOneScore = Game.instance.getScorePlayerOne();
        int playerTwoScore = Game.instance.getScorePlayerTwo();
        String playerOneName = Game.instance.getPlayerOne();
        String playerTwoName = Game.instance.getPlayerTwo();
        String playerOneArmy = Game.instance.getArmyPlayerOne();
        String playerTwoArmy = Game.instance.getArmyPlayerTwo();

        /* Logging for debugging */
//        Log.d("DEBUG", String.valueOf(playerOneScore));
//        Log.d("DEBUG", String.valueOf(playerTwoScore));
//        Log.d("DEBUG", String.valueOf(playerOneName));
//        Log.d("DEBUG", String.valueOf(playerTwoName));
//        Log.d("DEBUG", String.valueOf(playerOneArmy));
//        Log.d("DEBUG", String.valueOf(playerTwoArmy));
        new getScenarios().execute();

        startGameBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GenerateScenarioActivity.this, FirstPlayerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    class getScenarios extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            Log.d("Debug", "getting scenarios");
            Toast.makeText(GenerateScenarioActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String url = "https://40kapi.evinwijninga.com/scenarios";
            String jsonStr = httpHandler.makeServiceCall(url);
//            Log.e("Debug", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray scenarios = jsonObject.getJSONArray("scenarios");
//                    Log.e(TAG, "JSONARRAY :" + scenarios);

                    Random random = new Random();
                    int randomScenario = random.nextInt(scenarios.length());
//                    Log.e(TAG, "Generated scenario ID = "+String.valueOf(randomScenario));

                    JSONObject generatedsScenario = scenarios.getJSONObject(randomScenario);
//                    Log.e(TAG, String.valueOf(generatedsScenario));

                    scenarioTitle = generatedsScenario.getString("title");
                    scenarioType = generatedsScenario.getString("type");
                    scenarioArmies = generatedsScenario.getString("armies");
                    scenarioBattlefield = generatedsScenario.getString("battlefield");
                    scenarioDeployment = generatedsScenario.getString("deployment");
                    scenarioLength = generatedsScenario.getString("length");
                    scenarioFirstTurn = generatedsScenario.getString("firstTurn");
                    scenarioVictoryConditions = generatedsScenario.getString("victoryConditions");

                    /* Fill instance of Scenario (Singleton) */
                    Scenario.instance.setScanario_title(scenarioTitle);
                    Scenario.instance.setScanario_type(scenarioType);
                    Scenario.instance.setScanario_armies(scenarioArmies);
                    Scenario.instance.setScanario_battlefield(scenarioBattlefield);
                    Scenario.instance.setScanario_deployment(scenarioDeployment);
                    Scenario.instance.setScanario_length(scenarioLength);
                    Scenario.instance.setScanario_firstTurn(scenarioFirstTurn);
                    Scenario.instance.setScanario_victoryConditions(scenarioVictoryConditions);

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            generatedScenarioTV.setText(scenarioTitle);
            generatedScenarioTypeTV.setText(scenarioType);
            generateScenarioArmiesTV.setText(scenarioArmies);
            genereratedScenarioBattlefieldTV.setText(scenarioBattlefield);
            generatedScenarioDeploymentTV.setText(scenarioDeployment);
            generatedScenarioFirstTurnTV.setText(scenarioFirstTurn);
            generatedScenarioLengthTV.setText(scenarioLength);
            generatedScenarioVictoryConditionsTV.setText(scenarioVictoryConditions);
        }
    }
}
