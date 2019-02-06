package com

import android.app.Application
import com.pharosproduction.InetMonitor

class MockApplication: Application() {

    lateinit var inetMonitor: InetMonitor

    override fun onCreate() {
        super.onCreate()

        inetMonitor = InetMonitor(this)
    }
}