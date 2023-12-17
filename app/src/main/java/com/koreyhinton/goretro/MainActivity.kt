package com.koreyhinton.goretro

import android.content.Context
import android.content.res.ColorStateList
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener
import android.hardware.SensorEventListener
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    var bgViewMember: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("retro", "setContentView will set")
        print("setContentView will set")
        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_main)
        Log.d("retro", "setContentView did set")
        //setContentView(GameView(applicationContext, null,0))//R.layout.activity_main)
        //addContentView(GameView(applicationContext, null,0), null)
        //while (true) {
        print("setContentView done")

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // shakeDetector.start(sensorManager, SensorManager.SENSOR_DELAY_GAME)
        val mSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //Log.d("retro", findViewById<GameView>(R.id.gameId).toString())
        // sensorManager.run {  }

        var threshold = 60 // 20
        var count: Int = 0
        val bands = arrayOf(
            intArrayOf(R.drawable.bg_a,R.drawable.bg_b,R.drawable.bg_c),
            intArrayOf(R.drawable.bg_d,R.drawable.bg_e,R.drawable.bg_f)
        )
        var band = 0
        var bi = 0

        var lastVal: Float = 0f
        val foo: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                if (event.values[1]==lastVal || abs(event.values[1] - lastVal) < 0.06) {
                    return
                }
                lastVal = event.values[1]
                var bgView = findViewById<ImageView>(R.id.imageView)
                //Log.d("retro", "onSensorChanged")
                count += 1
                count %= threshold

                if (count < (threshold / 3)) {
                    bi += 1
                    if (bi >= 1000 || (band==bands.count()-1 && bi>=5)) {
                        band += 1
                        band %= bands.count()
                        bi = 0
                    }
                    bgView?.setImageResource(bands[band][0])
                } else if (count < (2*threshold / 3)){
                    bgView?.setImageResource(bands[band][1])
                } else if (count > (2*threshold / 3)) {
                    bgView?.setImageResource(bands[band][2])
                }

                /*findViewById<GameView>(R.id.gameId).setBackgroundColor(0x0000FF)
                findViewById<GameView>(R.id.gameId).
                print(event)*/
            }
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                //Log.d("retro", "onAccuracyChanged")
                //findViewById<GameView>(R.id.gameId).setBackgroundColor(0x0000FF)


                print(sensor)
            }
        }
        /*val triggerEventListener = object : TriggerEventListener() {
            override fun onTrigger(event: TriggerEvent?) {
                // Do work
                findViewById<GameView>(R.id.gameId).setBackgroundColor(0xFF0000)
                print(event)
            }
        }*/
        sensorManager.registerListener(foo, mSensor, SensorManager.SENSOR_DELAY_FASTEST)
        /*mSensor?.also { sensor ->
            sensorManager.senso// .requestTriggerSensor(triggerEventListener, sensor)
        }*/
        //}
    }

    override fun onStart() {
        super.onStart()

    }
}