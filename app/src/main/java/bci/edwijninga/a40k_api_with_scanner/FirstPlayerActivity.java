package bci.edwijninga.a40k_api_with_scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstPlayerActivity extends AppCompatActivity {

    String firstPlayer;
    String playerOneName;
    String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_player);

        final Button playerOneBtn = findViewById(R.id.choosePlayerOneBtn);
        final Button playerTwoBtn = findViewById(R.id.choosePlayerTwoBtn);

        playerOneName = Game.instance.getPlayerOne();
        playerTwoName = Game.instance.getPlayerTwo();

        playerOneBtn.setText(playerOneName);
        playerTwoBtn.setText(playerTwoName);

    }

    public void Onclick(View v){
        switch (v.getId()){
            case R.id.choosePlayerOneBtn:
                firstPlayer = playerOneName;
                break;
            case R.id.choosePlayerTwoBtn:
                firstPlayer = playerTwoName;
                break;
        }

        Intent intent = new Intent(FirstPlayerActivity.this, TurnActivity.class);
        startActivity(intent);
        finish();


        Log.e("DEBUG", "first turn goes to" + firstPlayer);
    }

}
