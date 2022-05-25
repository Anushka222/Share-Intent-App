package com.evernote.shareIntent

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayData(intent)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onNewIntent(newIntent : Intent?) {
        super.onNewIntent(newIntent)
        if (newIntent != null) {
            displayData(newIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun displayData(intent: Intent) {
        val shareExtension = ShareExtensionUtil()
        val  textView1= findViewById<TextView>(R.id.textView1)
        val  textView2 = findViewById<TextView>(R.id.textView2)
        val  textView3= findViewById<TextView>(R.id.textView3)
        val  textView4 = findViewById<TextView>(R.id.textView4)
        val  textView5 = findViewById<TextView>(R.id.textView5)
        val  imageView = findViewById<ImageView>(R.id.imageView)
        var arguments = shareExtension.createShareIntentData(this, intent)
        if (arguments != null) {
            if(arguments.getString("intentDataIcon")!=null){
           var iconUrl= arguments.getString("intentDataIcon")!!
                Glide.with(this)
                    .load(iconUrl)
                    .into(imageView)
            }
            else{
                Glide.with(this)
                    .load(R.drawable.data_icon)
                    .into(imageView)
            }

            textView1.text = arguments.getString("intentDataType")
            textView2.text = arguments.getString("intentDataContent")
            textView3.text = arguments.getString("extraData")
            textView4.text = arguments.getString("title")
            textView5.text = arguments.getString("intentDataReferer")
        }
        else {
                Log.d("ShareExtension:", "Init intent type is null")
        }
    }
}