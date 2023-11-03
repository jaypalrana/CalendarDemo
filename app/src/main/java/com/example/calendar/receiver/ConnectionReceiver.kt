package com.example.calendar.receiver

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionReceiver : BroadcastReceiver {

    var context: Context? = null
    var dialog: Dialog? = null
    constructor()
    constructor(context: Context) {
        this.context = context
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (!haveNetworkConnection()) {
            this.context = context ?: return
        }
    }

    fun haveNetworkConnection(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val capabilities = cm?.getNetworkCapabilities(cm.activeNetwork)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ))
    }
}
