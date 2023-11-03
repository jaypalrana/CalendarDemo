package com.example.calendar.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {
    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V
    abstract fun init()
    abstract fun setOnClick()
    abstract fun apiObserve()
    fun getViewBinding(): T {
        return mViewDataBinding
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = getViewModel()
//        mViewDataBinding.setVariable(BR.viewmodel, mViewModel)
        mViewDataBinding.executePendingBindings()

        mViewModel.toastLiveData.observe(this, Observer {
            mViewModel.showToast(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        init()
        setOnClick()
        apiObserve()
        mViewModel.registerNetwork(applicationContext)
       /* mViewModel.showNoInternet.observe(this, Observer {
            if (it == true) {
                Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }
        })*/
    }

    fun showToastShort(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }


}