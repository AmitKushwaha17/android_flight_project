package com.example.intent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView

import androidx.appcompat.app.AppCompatActivity


class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val splashVideoView:VideoView = findViewById(R.id.splashVideoView)

        val videoPath = "android.resource://"+ packageName +"/"+R.raw.splash1
        val videoUri = Uri.parse(videoPath)

        splashVideoView.setVideoURI(videoUri)

        splashVideoView.start()

        splashVideoView.setOnCompletionListener {
            navigateToMainActivity()
        }

        splashVideoView.setOnPreparedListener{ mediaPlayer ->
            mediaPlayer.isLooping = false
        }
    }

    private fun navigateToMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}