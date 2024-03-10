package isi.rnu.tn.try2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;


public class Quiz extends Activity {
    private static final String TAG = "*ComposantsGraph*";
    TextView tv;
    Button submitbutton, quitbutton,on,off;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    ProgressBar progressbar1;
    private static int progress=600;
    private static int progressresume=600;
    private static int progresspause=600;


    private void ProgressBar(){
        Handler handler = new Handler();
        Thread worker = new Thread(new Runnable() {

            public void run() {
                while (progress > 0 && progressresume==100)
                {
                    try {
// simule un traitement long
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    handler.post(
                            new Runnable()
                            {public void run()
                            {
                                progress=progress-1;
                                progressbar1.setProgress(progress);
                                if(progress==0){
                                    Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                                    startActivity(intent);
                                }

                            }
                            }
                    );
                }
            }
        });


        worker.start();

    }

    private void Chronometre(){
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);}




    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {

            //This method runs in the same thread as the UI.

            //Do something to the UI thread here

        }
    };

    @Override
    protected void onResume() {
        Log.i(TAG,"onResume");
        progressresume=100;
        progress=progresspause;
        ProgressBar();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause");
        progresspause=progress;
        progressresume=0;
        //worker.stop();
        super.onPause();
    }

    @Override
    protected void onStart() {

        Log.i(TAG,"onStart");
        super.onStart();
    }



   /* @Override
    public void onStart() {
        super.onStart();
        CountDownTimer timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                int current = progressbar1.getProgress() + 10;
                if (current > progressbar1.getMax()) current = 0;
                progressbar1.setProgress(current);
            }

            @Override
            public void onFinish() {
                Toast.makeText(Quiz.this, "End!", Toast.LENGTH_SHORT).show();

            }
        }.start();
    }*/

    String questions[] = {
            "Which method can be defined only once in a program?",
            "Which of these is not a bitwise operator?",
            "Which keyword is used by method to refer to the object that invoked it?",
            "Which of these keywords is used to define interfaces in Java?",
            "Which of these access specifiers can be used for an interface?",
            "Which of the following is correct way of importing an entire package ‘pkg’?",
            "What is the return type of Constructors?",
            "Which of the following package stores all the standard java classes?",
            "Which of these method of class String is used to compare two String objects for their equality?",
            "An expression involving byte, int, & literal numbers is promoted to which of these?"
    };
    String answers[] = {"main method","<=","this","interface","public","import pkg.*","None of the mentioned","java","equals()","int"};
    String opt[] = {
            "finalize method","main method","static method","private method",
            "&","&=","|=","<=",
            "import","this","catch","abstract",
            "Interface","interface","intf","Intf",
            "public","protected","private","All of the mentioned",
            "Import pkg.","import pkg.*","Import pkg.*","import pkg.",
            "int","float","void","None of the mentioned",
            "lang","java","util","java.packages",
            "equals()","Equals()","isequal()","Isequal()",
            "int","long","byte","float"
    };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        progressbar1 = findViewById(R.id.progressBar2);
        progressbar1.setProgress(0);
        startService(new Intent(getApplicationContext(),MyService.class));

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Hello User");
        else
            textView.setText("Hello " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        on = (Button)findViewById(R.id.btON);
        off = (Button)findViewById(R.id.btOFF);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(),MyService.class));
            }
            });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(),MyService.class));
            }
        });


    }

}