package com.example.myintentapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log

import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myintentapp.ShareExtensionUtil


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  textView = findViewById<TextView>(R.id.textView)
        val  textView2 = findViewById<TextView>(R.id.textView2)
        val  textView3= findViewById<TextView>(R.id.textView3)
        val  textView4 = findViewById<TextView>(R.id.textView4)
        val  textView5 = findViewById<TextView>(R.id.textView5)
        val  textView6 = findViewById<TextView>(R.id.textView6)
        val shareExtension = ShareExtensionUtil()
        var arguments = shareExtension.createShareIntentData(this, intent)
            if(arguments!=null){
           textView.text=arguments.getString("intentDataType")
            textView2.text=arguments.getString("intentDataContent")
            textView3.text=arguments.getString("extraData")
            textView4.text= arguments.getString("title")
            textView5.text=arguments.getString("intentDataReferer")
            textView6.text= arguments.getString("intentDataIcon")

        } else {
            Log.d("ShareExtension:", "Init intent type is null")
        }
    }
}