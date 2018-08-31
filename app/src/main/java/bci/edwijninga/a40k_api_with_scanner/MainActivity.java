package bci.edwijninga.a40k_api_with_scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Defining buttons. */
        final Button playGame_btn = findViewById(R.id.setup_game_btn);
        final Button objectives_btn = findViewById(R.id.objectives_btn);
        final Button scenarios_btn = findViewById(R.id.scenarios_btn);


        playGame_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, setupGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        objectives_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ObjectiveListActivity.class);
                startActivity(intent);
            }
        });

        scenarios_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScenarioListActivity.class);
                startActivity(intent);
            }
        });

    }


}
