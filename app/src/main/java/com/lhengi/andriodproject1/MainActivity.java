package com.lhengi.andriodproject1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button1;
    private Button button2;
    private String name;
    private int resultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(button1Listener);
        button2.setOnClickListener(button2Listener);
        name = "*";

    }

    public void launchSecondActivity()
    {

        Intent intent = new Intent(this,SecondaryActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent result)
    {
        this.resultCode = resultCode;
        if (resultCode == RESULT_CANCELED)
        {
            System.out.println("Bad name");
            name = result.getStringExtra("name");;
            return;
        }


        name = result.getStringExtra("name");
        System.out.println("&&&&&&&&& name:"+name);


    }

    public View.OnClickListener button1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textView.setText("Button 1 clicked!!!");
            launchSecondActivity();

        }
    };

    public View.OnClickListener button2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textView.setText("Button 2 clicked!");
            if(resultCode == RESULT_CANCELED)
            {
                System.out.println("#### Invalid name");

                Toast.makeText(getApplicationContext(),"Your name "+name+" is illegal!!!",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.NAME,name);
                startActivity(intent);

            }
        }
    };
}
