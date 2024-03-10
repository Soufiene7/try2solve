package isi.rnu.tn.try2;

import static isi.rnu.tn.try2.R.*;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btSubmit,btReset;
    Button btSignup;
    EditText name,password;
    DBHelper DB;

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(MainActivity.this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(MainActivity.this, "onResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        name = (EditText) findViewById(id.name);
        password = (EditText) findViewById(id.password);
        btSubmit = (Button) findViewById(id.sub);
        btReset = (Button) findViewById(id.reset);
        btSignup = (Button) findViewById(id.btSign1);
        DB = new DBHelper(this);



        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                password.setText("");

            }
        });
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);

            }
        });
      /*  btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom, pass;
                nom = name.getText().toString();
                pass = password.getText().toString();
                if(signIn(nom,pass)){
                    Toast.makeText(MainActivity.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Page2.class);
                    Bundle myData = new Bundle();
                    myData.putString("val1", nom);
                    intent.putExtras(myData);
                    startActivity(intent);

                }else if (pass.length()<10)
                {
                    Toast.makeText(MainActivity.this, "Password length<10", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
                }





        });*/
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = name.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(MainActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Page2.class);
                        Bundle myData = new Bundle();
                        myData.putString("val2", user);
                        intent.putExtras(myData);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }}
