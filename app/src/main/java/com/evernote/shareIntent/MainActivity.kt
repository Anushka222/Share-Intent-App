package com.evernote.shareIntent

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log

import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayData()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onNewIntent(newIntent : Intent?) {
        super.onNewIntent(newIntent)
        displayData()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun displayData() {
        val  textView1= findViewById<TextView>(R.id.textView1)
        val  textView2 = findViewById<TextView>(R.id.textView2)
        val  textView3= findViewById<TextView>(R.id.textView3)
        val  textView4 = findViewById<TextView>(R.id.textView4)
        val  textView5 = findViewById<TextView>(R.id.textView5)
        val  textView6 = findViewById<TextView>(R.id.textView6)
        val shareExtension = ShareExtensionUtil()
        var arguments = shareExtension.createShareIntentData(this, intent)
        if(arguments!=null){
            textView1.text = "intentDataType = " + arguments.getString("intentDataType")
            textView2.text = "intentDataContent = "+ arguments.getString("intentDataContent")
            textView3.text = "extraData = "+ arguments.getString("extraData")
            textView4.text = "title = " + arguments.getString("title")
            textView5.text = "intentDataReferer = " + arguments.getString("intentDataReferer")
            textView6.text = "intentDataIcon = " + arguments.getString("intentDataIcon")
        } else {
            Log.d("ShareExtension:", "Init intent type is null")
        }
    }
}