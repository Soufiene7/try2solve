package isi.rnu.tn.try2;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class SignUp extends Activity {
    static ArrayList<String> e =new ArrayList<>();
    static ArrayList<String> c =new ArrayList<>();
    ArrayList<String> list =new ArrayList<>();

        ListView txt ;
        EditText name,password;
        EditText mail;
        Button register;
    DBHelper DB;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sign_up);

            name = findViewById(R.id.fullName);
            mail = findViewById(R.id.email);
            password = findViewById(R.id.pass);
            register = findViewById(R.id.sign);
            txt = findViewById(R.id.list1);
            DB = new DBHelper(this);



          /*  register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String word;
                    Intent intent = new Intent(SignUp.this,Page2.class);
                    Bundle myData = new Bundle();
                    word = password.getText().toString();
                    String e = mail.getText().toString();
                    String nom = name.getText().toString();
                    myData.putString("val1", nom);
                    myData.putString("val2", word);
                    intent.putExtras(myData);
                    rTab(e,word);
                    startActivityForResult(intent,101);
                    checkDataEntered();

                }
            });*/
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = name.getText().toString();
                    String pass = password.getText().toString();
                    String email = mail.getText().toString();

                    if(user.equals("")||pass.equals("")||email.equals(""))
                        Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else{
                            Boolean checkuser = DB.checkusername(email);
                            if(checkuser==false){
                                Boolean insert = DB.insertData(email, pass);
                                if(insert==true){
                                    Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp.this,Page2.class);
                                    Bundle myData = new Bundle();
                                    myData.putString("val1", email);
                                    myData.putString("val2", user);
                                    myData.putString("val3", pass);
                                    intent.putExtras(myData);
                                    startActivityForResult(intent,101);
                                }else{
                                    Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            });
            /*signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });*/

        }

        boolean isEmail(EditText text) {
            CharSequence email = text.getText().toString();
            return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        }

        boolean isEmpty(EditText text) {
            CharSequence str = text.getText().toString();
            return TextUtils.isEmpty(str);
        }

        void checkDataEntered() {
            if (isEmpty(name)) {
                Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
                t.show();
            }

            if (!isEmail(mail)) {
                mail.setError("Enter valid email!");
            }

        }
        public void rTab(String a,String b){
            e.add(a);
            c.add(b);
        }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if ((requestCode == 101) && (resultCode == Activity.RESULT_OK)) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                Bundle myResults = data.getExtras();
                String val = myResults.getString("val");
                list.add(val);
                txt.setAdapter(adapter);
            }
        } catch (Exception ignored) {
        }
    }
}



