package com.example.calendar.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.calendar.R


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {
    private var mRootView: View? = null
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    private var isCallInit = false

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V
    abstract fun init()
    abstract fun setOnClick()
    abstract fun apiObserve()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isAdded && context != null) {
            mViewModel = getViewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (mRootView == null) {
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
            mRootView = mViewDataBinding?.root
            isCallInit = true
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        performDataBinding()
        init()
        setOnClick()
        apiObserve()

        mViewModel?.registerNetwork(requireActivity())

        mViewModel?.showNoInternet?.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show()
        })
    }

    private fun performDataBinding() {
//        mViewDataBinding?.setVariable(BR.viewmodel, mViewModel)
        mViewDataBinding?.lifecycleOwner = this
        mViewDataBinding?.executePendingBindings()
    }

    fun getViewBinding(): T {
        return mViewDataBinding as T
    }

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}
