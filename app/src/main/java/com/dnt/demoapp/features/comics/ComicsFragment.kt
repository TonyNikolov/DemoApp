package com.dnt.demoapp.features.comics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dnt.demoapp.R
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.state.Status
import com.dnt.demoapp.features.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_comics.*
import timber.log.Timber
import javax.inject.Inject

class ComicsFragment : BaseFragment() {

    private val viewModel by viewModels<ComicsViewModel> { viewModelFactory }
    private lateinit var adapter: ComicsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initButtons()
        initRv()
        initObservers()
        viewModel.getComics()
    }

    private fun initToolbar() {
        val activity: Activity? = activity
        if (activity is AppCompatActivity) {
            activity.setSupportActionBar(toolbar)
        }

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun initRv() {
        adapter = ComicsAdapter()
        rv.adapter = adapter
    }

    private fun initObservers() {
        viewModel.comicsLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> Timber.d("Loading")
                Status.SUCCESS -> {
                    Timber.d("Success")
                    val data = it.data?.data?.result

                    if (data != null) {
                        adapter.setItems(data)
                    } else {
                        // TODO: 6/9/2020 show no data view
                    }
                }
                Status.ERROR -> Timber.d("Error: %s", it.message)
            }
        })
    }

    private fun initButtons() {

    }
}