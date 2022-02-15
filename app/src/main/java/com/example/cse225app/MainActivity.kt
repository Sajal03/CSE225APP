package com.example.cse225app

import android.app.ActionBar
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    lateinit var  ratingBar: RatingBar
    lateinit var  showRating: Button
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ratingBar = findViewById(R.id.ratingBar) as RatingBar
        showRating = findViewById(R.id.btnShowRating) as Button
        showRating.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                applicationContext,
                "Your Rating=" + ratingBar.rating,
                Toast.LENGTH_SHORT
            ).show()
        })
        var start = findViewById<Button>(R.id.button)
        var RStart = findViewById<Button>(R.id.RStart)
        var cancel = findViewById<Button>(R.id.cancel_button)
        var Etext = findViewById<EditText>(R.id.time)
        var alarmManager: AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 234, intent, 0)


        start.setOnClickListener {
            var i = Etext.text.toString().toInt()
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (i * 1000),
                pendingIntent
            )
            Toast.makeText(this, "Alarm set in $i seconds", Toast.LENGTH_LONG).show()
        }

        RStart.setOnClickListener {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                5000,
                pendingIntent
            )
            Toast.makeText(this, "Repeating Alarm 5 Seconds", Toast.LENGTH_LONG).show()
        }

        cancel.setOnClickListener {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show()
        }

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("Alarm Clock")
        toolbar.setLogo(R.drawable.clock)
        setSupportActionBar(toolbar) //replace toolbar as an ActionBar
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Back Arrow", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId
        if(id==R.id.action_settings)
        {
            Toast.makeText(applicationContext, "Settings Menu", Toast.LENGTH_LONG).show()
            return true
        }
        else if(id == R.id.action_email)
        {
            Toast.makeText(applicationContext, "Email", Toast.LENGTH_LONG).show()
            return true
        }
        else if (id == R.id.action_add)
        {
            Toast.makeText(applicationContext, "Add", Toast.LENGTH_LONG).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    }
