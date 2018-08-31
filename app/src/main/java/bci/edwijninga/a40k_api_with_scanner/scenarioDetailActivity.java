package bci.edwijninga.a40k_api_with_scanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class scenarioDetailActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();


    String scenarioId;
    String scenarioTitle;
    String description;
    String type;
    String armies;
    String battlefield;
    String deployment;
    String firstTurn;
    String victoryConditions;
    String length;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_detail);

        Intent intent = getIntent();
        scenarioId = intent.getStringExtra("scenarioId");
        scenarioTitle = intent.getStringExtra("scenarioName");

        TextView title = findViewById(R.id.scenarioName);
        title.setText(scenarioTitle);

        new getScenarioDetails().execute();
    }

    @SuppressLint("StaticFieldLeak")
    class getScenarioDetails extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String url = "https://40kapi.evinwijninga.com/scenarios/"+scenarioId;
            String jsonStr = httpHandler.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.e(TAG,"jsonStr is not null");

                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray scenario = jsonObject.getJSONArray("scenario");
                    Log.e(TAG, "JsonObject: "+String.valueOf(jsonObject));

                    for (int i = 0; i < scenario.length(); i++) {

                        JSONObject s = scenario.getJSONObject(i);
                        type = s.getString("type");
                        armies = s.getString("armies");
                        battlefield = s.getString("battlefield");
                        deployment = s.getString("deployment");
                        firstTurn = s.getString("firstTurn");
                        length = s.getString("length");
                        victoryConditions = s.getString("victoryConditions");

                        Log.e(TAG, "armies = " + armies);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"Try lukt niet");
                }

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            super.onPreExecute();
            Toast.makeText(scenarioDetailActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            TextView armiesTV = findViewById(R.id.scenarioArmiesTV);
            armiesTV.setText(armies);

            TextView title = findViewById(R.id.scenarioName);
            title.setText(scenarioTitle);

            TextView typeTv = findViewById(R.id.scenarioType);
            typeTv.setText(type);

            TextView battlefieldTV = findViewById(R.id.ScenarioBattlefieldTV);
            battlefieldTV.setText(battlefield);

            TextView DeploymentTV = findViewById(R.id.ScenarioDeploymentTV);
            DeploymentTV.setText(deployment);

            TextView firstTurnTV = findViewById(R.id.ScenarioFirstTurnTV);
            firstTurnTV.setText(firstTurn);

            TextView lengthTV = findViewById(R.id.ScenarioLengthTV);
            lengthTV.setText(length);

            TextView victoryConditionsTV = findViewById(R.id.ScenarioVictoryConditionsTV);
            victoryConditionsTV.setText(victoryConditions);
        }
    }
}
