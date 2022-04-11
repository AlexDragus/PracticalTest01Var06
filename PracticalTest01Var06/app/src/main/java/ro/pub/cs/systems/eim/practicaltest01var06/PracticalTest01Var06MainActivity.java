package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    Integer score = 0;

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            if (((Button) view).getId() == R.id.buttonPlay) {
                Log.d("debug", "Button with id [" + ((Button) view).getId() + "] and text Play pressed!");
                EditText editTextNumber1 = (EditText) findViewById(R.id.editTextNumber1);
                EditText editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);
                EditText editTextNumber3 = (EditText) findViewById(R.id.editTextNumber3);

                CheckBox checkBoxNumber1 = (CheckBox) findViewById(R.id.checkBoxNumber1);
                CheckBox checkBoxNumber2 = (CheckBox) findViewById(R.id.checkBoxNumber2);
                CheckBox checkBoxNumber3 = (CheckBox) findViewById(R.id.checkBoxNumber3);

                String number1 = editTextNumber1.getText().toString();
                String number2 = editTextNumber2.getText().toString();
                String number3 = editTextNumber3.getText().toString();

                List<String> givenList = Arrays.asList("1", "2", "3", "*");
                if (!checkBoxNumber1.isChecked()) {
                    Random rand = new Random();
                    String randomElement = givenList.get(rand.nextInt(givenList.size()));
                    editTextNumber1.setText(randomElement);
                }
                if (!checkBoxNumber2.isChecked()) {
                    Random rand = new Random();
                    String randomElement = givenList.get(rand.nextInt(givenList.size()));
                    editTextNumber2.setText(randomElement);
                }
                if (!checkBoxNumber3.isChecked()) {
                    Random rand = new Random();
                    String randomElement = givenList.get(rand.nextInt(givenList.size()));
                    editTextNumber3.setText(randomElement);
                }

                number1 = editTextNumber1.getText().toString();
                number2 = editTextNumber2.getText().toString();
                number3 = editTextNumber3.getText().toString();

                int no_of_checkboxes = 0;

                if (checkBoxNumber1.isChecked())
                    no_of_checkboxes++;
                if (checkBoxNumber2.isChecked())
                    no_of_checkboxes++;
                if (checkBoxNumber3.isChecked())
                    no_of_checkboxes++;

                Log.d("debug", "Cele trei numere sunt: [" + number1 + "] [" + number2 + "] [" + number3 + "]");

                if (number1.equals(number2) && number2.equals(number3)) {
                    Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01var06.intent.action.PracticalTest01Var06SecondaryActivity");
                    intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var06.NUMBER_1", number1);
                    intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var06.NUMBER_2", number2);
                    intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var06.NUMBER_3", number3);
                    intent.putExtra("ro.pub.cs.systems.eim.practicaltest01var06.NO_OF_CHECKBOXES", no_of_checkboxes);
                    startActivityForResult(intent, 14);
                } else {
                    Toast.makeText(getApplication(), "Score = " + Integer.toString(score), Toast.LENGTH_LONG).show();

                    Log.d("debug", "Same total score: " + Integer.toString(score));
                }

            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 14:
                int localScore = intent.getIntExtra("ro.pub.cs.systems.eim.practicaltest01var06.SCORE", 0);
                Log.d("debug", "Local score received: " + Integer.toString(localScore));
                score += localScore;
                Log.d("debug", "New total score: " + Integer.toString(score));
                Toast.makeText(getApplication(), "Score = " + Integer.toString(score), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(buttonClickListener);

    }
}