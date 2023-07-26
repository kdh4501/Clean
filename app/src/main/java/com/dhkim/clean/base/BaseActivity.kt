package com.dhkim.clean.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.dhkim.clean.R

abstract class BaseActivity<T: ViewDataBinding, R : ViewModel> : AppCompatActivity() {
    lateinit var binding: T
    abstract val viewModel: R
    abstract val layoutResID: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding.lifecycleOwner = this
        initVariable()
        initView()
        initListener()
        initObserver()
    }

    override fun onBackPressed() {
        var processed = false
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (f in fragments) {
                if (f is BaseFragment<*,*> && f.isVisible) {
                    processed = f.onBackPressed()
                }
                if (processed) {
                    break
                }
            }
        }
        if (!processed) {
            super.onBackPressed()
        }
    }

    abstract fun initVariable()
    abstract fun initListener()
    abstract fun initObserver()
    abstract fun initView()
}