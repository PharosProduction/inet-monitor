package com.pharosproduction

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class InetMonitor(val application: Application) {

    // Companion

    companion object {
        private const val ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
        private var dialog: InternetAlertDialog? = null

        fun showNotification(activity: AppCompatActivity) {
            val spm = activity.supportFragmentManager
            hideNotification(activity)
            dialog = InternetAlertDialog().also { it.show(spm, InternetAlertDialog.FRAGMENT_KEY) }
        }

        fun hideNotification(activity: AppCompatActivity) {
            val spm = activity.supportFragmentManager
            dialog = spm.findFragmentByTag(InternetAlertDialog.FRAGMENT_KEY) as InternetAlertDialog?
            dialog?.let { spm.beginTransaction().remove(it).commitNowAllowingStateLoss() }
        }
    }

    // Variables

    private var receiver = NetworkReceiver()

    // Public

    fun registerReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION)
        application.registerReceiver(receiver, intentFilter)
    }

    fun addConnectionListener(connectionListener: ConnectionListener) {
        NetworkReceiver.connectionListener = connectionListener
    }

    interface ConnectionListener {
        fun listenConnection(isConnected: Boolean)
    }
}

private class NetworkReceiver : BroadcastReceiver() {

    // Constants

    companion object {
        var connectionListener: InetMonitor.ConnectionListener? = null
    }

    // Life

    override fun onReceive(context: Context, intent: Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        //should check null because in airplane mode it will be null
        val isOnline = netInfo != null && netInfo.isConnected

        connectionListener?.let { it.listenConnection(isOnline) }
    }
}



