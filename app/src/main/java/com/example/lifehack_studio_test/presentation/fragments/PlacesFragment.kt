package com.example.lifehack_studio_test.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.lifehack_studio_test.R
import com.example.lifehack_studio_test.databinding.FragmentPlacesBinding
import com.example.lifehack_studio_test.presentation.adapters.PlacesAdapter
import com.example.lifehack_studio_test.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PlacesFragment : Fragment() {

    companion object {

        const val FRAGMENT_NAME = "PlacesFragment"
        fun newInstance() = PlacesFragment()
    }

    private var _viewBinding: FragmentPlacesBinding? = null
    val viewBinding: FragmentPlacesBinding
        get() = _viewBinding ?: throw RuntimeException("PlacesFragment = null ")

    @Inject
    lateinit var placesAdapter: PlacesAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentPlacesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        subscribeToObservers()
        setUpListeners()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.places.collectLatest {
                placesAdapter.submitList(it)
            }
        }
    }

    private fun setUpListeners() {
        placesAdapter.setItemClickListener {
            viewModel.currentId.value = it.id
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PlaceInfoFragment.newInstance())
                .addToBackStack(FRAGMENT_NAME)
                .commit()
        }
    }

    private fun setUpRecyclerView() {
        viewBinding.rvPlaces.adapter = placesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}