package com.pharosproduction.inet_monitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pharosproduction.InetMonitor

class MainActivity : AppCompatActivity(), InetMonitor.ConnectionListener {

    // Life

    override fun listenConnection(isConnected: Boolean) {
        if (!isConnected) {
            InetMonitor.showNotification(this)
        } else InetMonitor.hideNotification(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).monitor.addConnectionListener(this)
    }
}
