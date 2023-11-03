package com.example.calendar.permissions

interface OnRequestCallback {
    fun onGrant()

     fun onDenied(permission: String?)
}