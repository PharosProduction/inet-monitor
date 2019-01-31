package com.pharosproduction.inet_monitor

import android.app.Application
import com.pharosproduction.InetMonitor

class App : Application() {

    // Variables

    lateinit var monitor: InetMonitor

    // Life

    override fun onCreate() {
        super.onCreate()
        monitor = InetMonitor(this)
        monitor.registerReceiver()
    }
}