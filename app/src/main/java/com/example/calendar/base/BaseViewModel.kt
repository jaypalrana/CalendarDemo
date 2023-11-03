package com.example.calendar.base

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.calendar.api.ApiInterface
import com.example.calendar.api.RetrofitClient
import com.example.calendar.receiver.ConnectionReceiver

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var isLoading = ObservableBoolean(false)
    var loadingMsg = ObservableField("")
    var isNoDataFound = ObservableBoolean(false)
    val toastLiveData = MutableLiveData<String>()
    val snackBarLiveData = MutableLiveData<String>()
    val showNoInternet = MutableLiveData<Boolean>()
    val connectionReceiver = ConnectionReceiver(application.applicationContext)

    //Show loading with you can give your custom Message
    fun showLoading(msg: String = "") {
        loadingMsg.set(msg)
        isLoading.set(true)
    }

    //Hide Loading
    fun dismissLoading() {
        isLoading.set(false)
    }

    //Show no data found screen
    fun showNoDataFound() {
        this.isNoDataFound.set(true)
    }

    //Hide no data found screen
    fun hideNoDataFound() {
        this.isNoDataFound.set(false)
    }

    //Show Toast
    fun showToast(msg: String) {
        toastLiveData.value = msg
    }

    //Show Snackbar
    fun showSnackBar(msg: String) {
        snackBarLiveData.value = msg
    }

    //Check internet connect or not
    fun checkInternetConnection(action: () -> Unit) {
        if (connectionReceiver.haveNetworkConnection()) {
            showNoInternet.postValue(false)
            action.invoke()
        } else {
            showNoInternet.postValue(true)
            dismissLoading()
            showNoDataFound()
        }
    }

    //Register method for receiver
    fun registerNetwork(context: Context) {
        context.registerReceiver(
            connectionReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    fun getApi(): ApiInterface {
        return RetrofitClient.getRetrofitClientObj(getApplication()).getApiInterface()
    }

}