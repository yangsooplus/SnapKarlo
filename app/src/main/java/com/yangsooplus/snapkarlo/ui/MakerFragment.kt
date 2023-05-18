package com.yangsooplus.snapkarlo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yangsooplus.snapkarlo.data.ApiState
import com.yangsooplus.snapkarlo.databinding.FragmentMakerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MakerFragment : Fragment() {

    private lateinit var binding: FragmentMakerBinding
    private val viewModel: MakerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getT2iImage("bubble sugar sweet")
        showImage()
    }

    private fun showImage() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.t2iResponseState.collect {
                    when (it) {
                        is ApiState.Error -> binding.tvState.text = it.message
                        is ApiState.Loading -> binding.tvState.text = "loading"
                        is ApiState.Success -> binding.tvState.text = it.data?.id ?: "none"
                    }
                }
            }
        }
    }
}
