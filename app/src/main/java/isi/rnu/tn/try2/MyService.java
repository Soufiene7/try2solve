package isi.rnu.tn.try2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        Toast.makeText(this,"Service created", Toast.LENGTH_SHORT).show();
        mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        mMediaPlayer.setLooping(false);

    }
    @Override
    public void onStart(Intent intent, int startId){
        Toast.makeText(this,"Service Started", Toast.LENGTH_SHORT).show();
        mMediaPlayer.start();
    }
    @Override
    public void onDestroy(){
        Toast.makeText(this,"Service Stopped", Toast.LENGTH_SHORT).show();
        mMediaPlayer.stop();

    }

}
