package com.dnt.demoapp.features.venues

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dnt.demoapp.R
import com.dnt.demoapp.features.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_venues.*
import javax.inject.Inject

class VenuesFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<VenuesViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initButtons()
        viewModel.getVenues()
    }

    private fun initToolbar() {
        val activity: Activity? = activity
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(toolbar)
        }

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun initButtons() {

    }
}