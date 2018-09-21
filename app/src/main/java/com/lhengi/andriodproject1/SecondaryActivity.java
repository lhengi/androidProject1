package com.lhengi.andriodproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class SecondaryActivity extends AppCompatActivity {

    private EditText textField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        textField = (EditText) findViewById(R.id.textField);

        textField.setOnEditorActionListener(textFieldListener);


    }

    public boolean isLegal() {
        String[] nameExploded = textField.getText().toString().split(" ");

        String name = "";

        Intent resultIntent = new Intent();

        int counter = 0;
        for (String s : nameExploded) {
            if (!s.equals("") && !s.matches("[a-zA-Z]+")) {
                //System.out.println("#########This is not a good name");
                resultIntent.putExtra("name",textField.getText().toString());
                setResult(RESULT_CANCELED,resultIntent);
                return false;
            } else if (!s.equals("") && s.matches("[a-zA-Z]+")) {
                name += s + " ";
                counter++;
            }
        }

        if (counter < 2)
        {
            resultIntent.putExtra("name", name);
            setResult(RESULT_CANCELED,resultIntent);
            return false;
        }

        //System.out.println("$$$$$$$$$$$This is the name: " + name);
        resultIntent.putExtra("name",name);
        setResult(RESULT_OK,resultIntent);
        return true;


    }

    public TextView.OnEditorActionListener textFieldListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            //System.out.println("ON editor action Listener is triggered!!!!!!!!!");
            boolean legal = isLegal();
            finish();
            return legal;


        }
    };

    @Override
    public void onBackPressed()
    {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", "");
        setResult(RESULT_CANCELED,resultIntent);
        super.onBackPressed();
        finish();
    }

}
