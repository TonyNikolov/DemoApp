package com.dnt.demoapp.features.base

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnt.demoapp.DemoApp
import com.dnt.demoapp.dagger2.AppComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    protected val appComponent: AppComponent
        get() {
            val activity: Activity? = activity
            if (activity != null) {
                val app = activity.application
                if (app is DemoApp) {
                    return app.appComponent
                }
            }
            throw RuntimeException("Could not locate AppComponent..")
        }
}