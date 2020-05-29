package com.dnt.demoapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_venues.*

class VenuesFragment : Fragment() {

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