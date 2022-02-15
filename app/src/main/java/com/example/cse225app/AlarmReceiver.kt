package com.example.cse225app

import android.content.BroadcastReceiver
import android.content.Context

import android.content.Intent
import android.media.MediaPlayer


import android.util.Log
import android.widget.Toast



class AlarmReceiver : BroadcastReceiver() {



    override fun onReceive(context: Context, intent: Intent) {
        var mp = MediaPlayer.create(
            context,
            R.raw.app_src_main_res_raw_ringtone
        )
        Log.d("Hello","repeating alarm")
        mp.start()
        Toast.makeText(context, "Alarm Ringing", Toast.LENGTH_LONG).show()
    }

}