package com.example.calendar.permissions

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

object  PermissionManager {
    var mContext: Context? = null
     var mExplain: String? = null
    lateinit var mPermissions: Array<String>
    var mCallBack: OnRequestCallback? = null
    private val mCallBackBroadcastReceiver: CallBackBroadcastReceiver = CallBackBroadcastReceiver()


    fun request() {
        if (mPermissions.isEmpty()) {
            throw NullPointerException(if (mPermissions == null) "mPermissions is null" else "mPermissions is empty")
        }
        val intent: Intent =
            RequestActivity().newIntent(mContext, mPermissions, mExplain, mCallBack)
        mContext!!.startActivity(intent)
        LocalBroadcastManager.getInstance(mContext!!)
            .registerReceiver(
                mCallBackBroadcastReceiver,
                IntentFilter(mContext!!.packageName)
            )
    }

    class Builder(context: Context?) {
        private var mPermissionCompat = PermissionManager

        init {
            mContext = context
        }

        fun addPermissionRationale(explain: String?): Builder {
            mExplain = explain
            return this
        }

        fun addPermissions(permissions: Array<String>): Builder {
            mPermissions = permissions
            return this
        }

        fun addRequestPermissionsCallBack(callBack: OnRequestCallback): Builder {
            mCallBack = callBack
            return this
        }

        fun build(): PermissionManager {
            return mPermissionCompat
        }
    }

    internal class CallBackBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("BroadcastReceiver", intent.action.toString())
            if (mCallBack == null) {
                return
            }
            val result = intent.getBooleanExtra(Constant.GRANT, false)
            if (result) {
                mCallBack!!.onGrant()
            } else {
                val permission = intent.getStringExtra(Constant.DENIED)
                mCallBack!!.onDenied(permission)
            }
            mContext?.let {
                LocalBroadcastManager.getInstance(it)
                    .unregisterReceiver(mCallBackBroadcastReceiver)
            }
        }
    }


}