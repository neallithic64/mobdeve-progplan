package com.mobdeve.s17.lim.matthew.mobdeve_progplan;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

	private MediaPlayer player;

	@Override
	public void onCreate() {
		super.onCreate();
		player = MediaPlayer.create(this, R.raw.bgm);
		player.setVolume(25,25);
		player.setLooping(true);
		Log.d("Service MusicService","onCreate Initialize");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		player.start();
		Log.d("Service MusicService","onStartCommand Play");
		return Service.START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		player.stop();
		player.release();
		Log.d("Service MusicService","onDestroy Stop");
	}

	//(x) Binding related code
	private final IBinder binder = new LocalBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	public class LocalBinder extends Binder {
		MusicService getService() {
			return MusicService.this;
		}
	}
}

