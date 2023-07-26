package com.dhkim.clean.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Utils {
    companion object {
        /**
         * @param form - error, success, info, warning, normal, custom
         */
        const val NOTI_ERROR = "error"
        const val NOTI_SUCCESS= "success"
        const val NOTI_INFO = "info"
        const val NOTI_WARNING = "warning"
        const val NOTI_NORMAL = "normal"
        fun showNotification(msg: String, form: String) {
            when(form) {
            }
        }

        fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
            }
        }
    }
}