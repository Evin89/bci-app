package bci.edwijninga.a40k_api_with_scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class setupGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        Intent intent = getIntent();

        final EditText playerOneNameTV = findViewById(R.id.playerOneName);
        final EditText playerTwoNameTV = findViewById(R.id.playerTwoName);

        playerOneNameTV.setText("A");
        playerTwoNameTV.setText("B");

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.armies_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        final Button furtherBtn = findViewById(R.id.furtherBtn);
        furtherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Get player names */
                String playerOneNameString = playerOneNameTV.getText().toString();
                String playerTwoNameString = playerTwoNameTV.getText().toString();

                /* Check of de spelersnamen ingevuld zijn */
                if (playerOneNameString != null && playerTwoNameString != null){

                    /* Set player scores */
                    Game.instance.setScorePlayerOne(0);
                    Game.instance.setScorePlayerTwo(0);

                    /* Set player names */
                    Game.instance.setPlayerOne(playerOneNameString);
                    Game.instance.setPlayerTwo(playerTwoNameString);

                    /* Set player armies */
                    Game.instance.setArmyPlayerOne(spinner1.getSelectedItem().toString());
                    Game.instance.setArmyPlayerTwo(spinner2.getSelectedItem().toString());


                    Intent intent = new Intent(setupGameActivity.this, GenerateScenarioActivity.class);
                    startActivity(intent);
                } else {
                    return;
                }

            }
        });
    }
}
