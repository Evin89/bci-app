package bci.edwijninga.a40k_api_with_scanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioListActivity extends AppCompatActivity {

//    private static String TAG = ScenarioListActivity.class.getSimpleName();
//    public ListView ScenarioListView;
//    public String id;
//    public String title;
//
//    ArrayList<HashMap<String, String>> scenarioList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scenario_detail);
//        scenarioList = new ArrayList<>();
//
//        ScenarioListView = findViewById(R.id.scenariosListView);
//
//        new getScenarios().execute();
//
//        // ListView Item Click Listener
////        ScenarioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                String scenarioId = ((TextView) view.findViewById(R.id.id)).getText().toString();
////                String scenarioName = ((TextView) view.findViewById(R.id.title)).getText().toString();
////                getGenerateScenarioIntent(scenarioId, scenarioName);
////            }
////
////        });
//    }
//
//    private void getGenerateScenarioIntent(String scenarioId, String scenarioName) {
//        Intent intent = new Intent(this, scenarioDetailActivity.class);
//        intent.putExtra("scenarioId", scenarioId);
//        intent.putExtra("scenarioName", scenarioName);
//        Log.e(TAG, "Scenario ID = "+scenarioId);
//        startActivity(intent);
//    }
//
//    @SuppressLint("StaticFieldLeak")
//    class getScenarios extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(ScenarioListActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            HTTPHandler httpHandler = new HTTPHandler();
//            String url = "https://40kapi.evinwijninga.com/scenarios";
//            String jsonStr = httpHandler.makeServiceCall(url);
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//
//
//                try {
//                    JSONObject jsonObject = new JSONObject(jsonStr);
//
//                    JSONArray scenarios = jsonObject.getJSONArray("scenarios");
//                    Log.e(TAG, "JSONARRAY :" + scenarios);
//
//                    for (int i = 0; i < scenarios.length(); i++) {
//                        Log.e(TAG, "Scenario's length: " + String.valueOf(scenarios.length()));
//                        JSONObject s = scenarios.getJSONObject(i);
//
//                        if (s.getString("id") != null) {
//                            id = s.getString("id");
//                            Log.e(TAG, id);
//                        } else {
//                            Log.e(TAG, "id = null");
//                        }
//
//                        if (s.getString("title") != null) {
//                            title = s.getString("title");
//                            Log.e(TAG, title);
//                        } else {
//                            Log.e(TAG, "title = null");
//                        }
//
//                        if (id != null && title != null) {
//
//                            // make new scenario
//                            HashMap<String, String> scenario = new HashMap<>();
//                            // add properties to scenario
//                            scenario.put("id", id);
//                            scenario.put("title", title);
//                            // add scenario to scenariolist
//                            scenarioList.add(scenario);
//                        } else {
//                            Log.e(TAG, "id or title is null");
//                        }
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//                }
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            ListAdapter adapter = new SimpleAdapter(ScenarioListActivity.this, scenarioList, R.layout.scenario_list_item, new String[]{"id", "title"},
//                    new int[]{R.id.id, R.id.title});
//            ScenarioListView.setAdapter(adapter);
//        }
//    }
//}

    private String TAG = MainActivity.class.getSimpleName();
    private ListView ScenarioListView;
    public String id;
    public String title;

    ArrayList<HashMap<String, String>> scenarioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_list);
        scenarioList = new ArrayList<>();

        ScenarioListView = findViewById(R.id.scenariosListView);

        new getScenarios().execute();

        // ListView Item Click Listener
        ScenarioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String scenarioId = ((TextView) view.findViewById(R.id.id)).getText().toString();
                String scenarioName = ((TextView) view.findViewById(R.id.title)).getText().toString();
                getGenerateScenarioIntent(scenarioId, scenarioName);
            }

        });
    }

    private void getGenerateScenarioIntent(String scenarioId, String scenarioName) {
        Intent intent = new Intent(this, scenarioDetailActivity.class);
        intent.putExtra("scenarioId", scenarioId);
        intent.putExtra("scenarioName", scenarioName);
        Log.e(TAG, "Scenario ID = "+scenarioId);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    class getScenarios extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ScenarioListActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler httpHandler = new HTTPHandler();
            String url = "https://40kapi.evinwijninga.com/scenarios";
            String jsonStr = httpHandler.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {


                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    JSONArray scenarios = jsonObject.getJSONArray("scenarios");
                    Log.e(TAG, "JSONARRAY :" + scenarios);

                    for (int i = 0; i < scenarios.length(); i++) {
                        Log.e(TAG, "Scenario's length: " + String.valueOf(scenarios.length()));
                        JSONObject s = scenarios.getJSONObject(i);

                        if (s.getString("id") != null) {
                            id = s.getString("id");
                            Log.e(TAG, id);
                        } else {
                            Log.e(TAG, "id = null");
                        }

                        if (s.getString("title") != null) {
                            title = s.getString("title");
                            Log.e(TAG, title);
                        } else {
                            Log.e(TAG, "title = null");
                        }

                        if (id != null && title != null) {

                            // make new scenario
                            HashMap<String, String> scenario = new HashMap<>();
                            // add properties to scenario
                            scenario.put("id", id);
                            scenario.put("title", title);
                            // add scenario to scenariolist
                            scenarioList.add(scenario);
                        } else {
                            Log.e(TAG, "id or title is null");
                        }
                    }
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
            ListAdapter adapter = new SimpleAdapter(ScenarioListActivity.this, scenarioList,
                    R.layout.scenario_list_item, new String[]{"id", "title"},
                    new int[]{R.id.id, R.id.title});
            ScenarioListView.setAdapter(adapter);
        }
    }
}