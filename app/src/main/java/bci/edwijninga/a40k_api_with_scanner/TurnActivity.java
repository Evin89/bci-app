package bci.edwijninga.a40k_api_with_scanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class TurnActivity extends AppCompatActivity {

    public int currentTurn;
    public int continueGame;
    public TextView turnTV;
    public String D66;

    String TAG = "DEBUG";
    String type;
    String armies;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);

        turnTV = findViewById(R.id.TurnTV);

        continueGame = 3;
        currentTurn = 1;
    }

    public void OnClick(View v){
        checkIfLastTurn();
        generateObjective();
        nextTurn();

    }

    @SuppressLint("SetTextI18n")
    public void nextTurn(){
        currentTurn += 1;
        turnTV.setText("Turn " + currentTurn);
    }

    public void checkIfLastTurn(){
        if (currentTurn == 5){
            Random rnd = new Random();
            int roll = rnd.nextInt(6 - 1) + 1;
            Log.d("DEBUG", "Roll = " + roll);

            if (roll < continueGame) {
                endGame();
            }
            else {
                continueGame += 1;
                Log.d("DEBUG", "Contunue game on a " + continueGame + "+");
            }
        } else if (currentTurn == 7){
            endGame();
        }
    }

    public void generateObjective(){

        Random r1 = new Random();
        int x = r1.nextInt((6 - 1) + 1);

        Random r2 = new Random();
        int y = r2.nextInt((6 - 1) + 1);

        Log.d("DEBUG", String.valueOf(x));
        Log.d("DEBUG", String.valueOf(y));

        D66 = String.valueOf(x)+String.valueOf(y);
        Log.e("DEBUG", "D66 roll is " + D66);

        new getScenarioDetails();

    }

    @SuppressLint("StaticFieldLeak")
    class getScenarioDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String url = "https://40kapi.evinwijninga.com/objectives/"+D66;
            String jsonStr = httpHandler.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.e(TAG,"jsonStr is not null");

                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray scenario = jsonObject.getJSONArray("objective");
                    Log.e(TAG, "JsonObject: "+String.valueOf(jsonObject));

                    for (int i = 0; i < scenario.length(); i++) {

                        JSONObject s = scenario.getJSONObject(i);
                        name = s.getString("name");
                        type = s.getString("type");
                        armies = s.getString("description");
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
            Toast.makeText(TurnActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            TextView armiesTV = findViewById(R.id.scenarioArmiesTV);
            armiesTV.setText(armies);

            TextView title = findViewById(R.id.scenarioName);
            title.setText(name);

            TextView typeTv = findViewById(R.id.scenarioType);
            typeTv.setText(type);
        }
    }

    public void endGame(){
        Intent intent = new Intent(TurnActivity.this, EndGameActivity.class);
        startActivity(intent);
        finish();
    }
}
