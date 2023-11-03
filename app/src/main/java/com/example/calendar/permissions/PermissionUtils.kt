package com.example.calendar.permissions

import android.content.pm.PackageManager

class PermissionUtils {

    fun verifyPermissions(grantResults: IntArray): Int {
        if (grantResults.size < 1) {
            return -1
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (i in grantResults.indices) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return i
            }
        }
        return -1
    }
}