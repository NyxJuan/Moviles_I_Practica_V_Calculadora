package com.example.practica_calculadora;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServicioMusica extends Service {

    MediaPlayer reproductor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        reproductor = MediaPlayer.create(getApplicationContext(),R.raw.metallica);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        reproductor.stop();
    }
}
