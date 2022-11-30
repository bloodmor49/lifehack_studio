package com.example.lifehack_studio_test.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.lifehack_studio_test.databinding.FragmentPlaceInfoBinding
import com.example.lifehack_studio_test.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class PlaceInfoFragment @Inject constructor() : Fragment() {

    companion object {
        const val FRAGMENT_NAME = "PlaceInfo"
        fun newInstance() = PlaceInfoFragment()
    }

    private var _viewBinding: FragmentPlaceInfoBinding? = null
    val viewBinding: FragmentPlaceInfoBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentPlaceInfoBinding = null ")

    @Inject
    lateinit var glide: RequestManager

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPlaceInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentPlaceInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun launchLocation(latitude: Double, longitude: Double) {
        val label = "zoom"
        val gmmIntentUri: Uri = Uri.parse("geo:0,0?q=$latitude,$longitude($label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        requireActivity().startActivity(mapIntent)
    }

    private fun launchWebPage(web: String) {
        val uri = Uri.parse(web)
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
        requireActivity().startActivity(browserIntent, null)
    }


    private fun subscribeToObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.placeInfo.collectLatest { info ->
                with(viewBinding) {
                    infoNum.text = info.id.toString()
                    glide.load(info.imgUrl)
                        .error(com.google.android.material.R.drawable.material_ic_clear_black_24dp)
                        .into(ivInfo)
                    tvInfoTitle.text = info.name
                    tvInfoDes.text = info.description
                    tvPhone.text = info.phone
                    tvWww.text = info.www
                    tvWww.setOnClickListener {
                        launchWebPage(info.www)
                    }
                    btnMap.setOnClickListener {
                        launchLocation(info.lat, info.lon)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}