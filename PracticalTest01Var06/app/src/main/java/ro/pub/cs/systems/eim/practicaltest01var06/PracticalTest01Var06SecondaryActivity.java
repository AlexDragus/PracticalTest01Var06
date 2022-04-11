package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    Integer globalScore = 0;

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if (((Button) view).getId() == R.id.buttonOk) {
                Log.d("debug", "Button with id [" + ((Button) view).getId() + "] and text Ok pressed!");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("ro.pub.cs.systems.eim.practicaltest01var06.SCORE", globalScore);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();

        if (intent != null) {
            int noOfCheckboxes = intent.getIntExtra("ro.pub.cs.systems.eim.practicaltest01var06.NO_OF_CHECKBOXES", 3);
            String number1 = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var06.NUMBER_1");
            String number2 = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var06.NUMBER_2");
            String number3 = intent.getStringExtra("ro.pub.cs.systems.eim.practicaltest01var06.NUMBER_3");

            if (number1 == null) {
                Toast.makeText(this, "Number 1 not set", Toast.LENGTH_LONG).show();
            }
            if (number2 == null) {
                Toast.makeText(this, "Number 2 not set", Toast.LENGTH_LONG).show();
            }
            if (number3 == null) {
                Toast.makeText(this, "Number 3 not set", Toast.LENGTH_LONG).show();
            }

            if (number1.equals(number2) && number2.equals(number3)) {
                int score = 0;
                if (noOfCheckboxes == 0)
                    score = 100;
                else if (noOfCheckboxes == 1)
                    score = 50;
                else if (noOfCheckboxes == 2)
                    score = 10;
                globalScore = score;
                TextView screenText = (TextView) findViewById(R.id.textView);
                screenText.setText("Gained " + Integer.toString(score));
            }
        }
    }
}