package com.example.calendar.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class RequestActivity : AppCompatActivity() {
    private val TAG = "RequestActivity"
    private val PERMISSIONS = "permissions"
    private val EXPLAIN = "explain"
    private val REQUEST_CODE = 1000

    private var mExplain: String? = null
    lateinit var mPermissions: Array<String>

    fun newIntent(
        context: Context?, permissions: Array<String>,
        explain: String?, callBack: OnRequestCallback?
    ): Intent {
        val intent = Intent(context, RequestActivity::class.java)
        val extras = Bundle()
        extras.putStringArray(PERMISSIONS, permissions)
        extras.putString(EXPLAIN, explain)
        intent.putExtras(extras)
        return intent
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        checkPermission()
    }

    private fun initData() {
        val extras = intent.extras
        mExplain = extras!!.getString(EXPLAIN)
        mPermissions = extras.getStringArray(PERMISSIONS)!!
    }
    private fun checkPermission() {
        val deniedIndex: Int = checkSelfPermissions(mPermissions)
        if (deniedIndex != -1) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, mPermissions[deniedIndex]
                )
            ) {
                Log.d(TAG, "denied")
                requestPermission()
            } else {
                Log.d(TAG, "start request permission")
                requestPermission()
            }
        } else {
            Log.d(TAG, "Authorized")
            val intent = Intent()
            intent.putExtra(Constant.GRANT, true)
            sendMessage(intent)
        }
    }

    private fun checkSelfPermissions(permissions: Array<String>): Int {
        for (i in permissions.indices) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    permissions[i]
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return i
            }
        }
        return -1
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            mPermissions, REQUEST_CODE
        )
    }

    private fun sendMessage(intent: Intent) {
        intent.action = packageName
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        finish()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                val index: Int = PermissionUtils().verifyPermissions(grantResults)
                val intent = Intent()
                val args = Bundle()
                if (index == -1) {
                    args.putBoolean(Constant.GRANT, true)
                } else {
                    args.putString(Constant.DENIED, permissions[index])
                }
                intent.putExtras(args)
                sendMessage(intent)
            }
        }
    }

}