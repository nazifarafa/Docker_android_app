package com.example.vibrate_on_command;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) findViewById((R.id.editTextPIN));


        Button button = (Button) findViewById(R.id.button1);
        OnClickListener btnClicked = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(inputText.getText());

                int[] pin = new int[100];
                Arrays.fill(pin, 0);
                for(int j=0; j<s.length(); j++)
                {
                    pin[j+1] = Character.getNumericValue(s.charAt(j));
                }

                long[] pattern = new long[100];
                Arrays.fill(pattern, 0);
                for (int i=0; i<pin.length; i++)
                {
                    pattern[i] = pin[i] * 100;
                }
                Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vi.vibrate(VibrationEffect.createWaveform(pattern, -1));


            }
        };
        button.setOnClickListener(btnClicked);


    }
}