package isi.rnu.tn.try2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page2 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt;
        Button btBack;
        setContentView(R.layout.page2);
        txt = (TextView) findViewById(R.id.textView1);
        btBack = findViewById(R.id.back);
        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();
        String v1 = myBundle.getString("val1");
        String v2 = myBundle.getString("val2");
        String v3 = myBundle.getString("val3");
        txt.setText("Welcome "+v2);
        String v4 = v1+" "+v3;
        myBundle.putString("val", v4);
        myLocalIntent.putExtras(myBundle);
        setResult(SignUp.RESULT_OK, myLocalIntent);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button startbutton=(Button)findViewById(R.id.button);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Quiz.class);
                intent.putExtra("myname",v2);
                startActivity(intent);
            }
        });


    }
}