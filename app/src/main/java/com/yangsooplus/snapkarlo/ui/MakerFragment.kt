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
import com.yangsooplus.snapkarlo.R
import com.yangsooplus.snapkarlo.data.ApiState
import com.yangsooplus.snapkarlo.data.Base64Converter
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

        collectImage()
        collectUiState()

        binding.btnCreate.setOnClickListener {
            if (binding.tietT2i.text.toString().isNotEmpty()) {
                viewModel.getT2iImage(binding.tietT2i.text.toString())
            }
        }
    }

    private fun collectImage() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.t2iResponseState.collect { t2i ->
                    when (t2i) {
                        is ApiState.Error -> {
                            binding.ivT2i.visibility = View.VISIBLE
                            binding.ivT2i.setImageResource(R.drawable.ic_error)
                        }
                        is ApiState.Loading -> {
                            binding.ivT2i.visibility = View.GONE
                        }
                        is ApiState.Success -> {
                            binding.ivT2i.visibility = View.VISIBLE
                            t2i.data?.let { setImage(it.images[0].image) }
                        }
                        is ApiState.Idle -> {}
                    }
                }
            }
        }
    }

    private fun collectUiState() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.t2iUiState.collect { uistate ->
                    when (uistate) {
                        UiState.Idle -> {
                            binding.pbLoad.visibility = View.GONE
                            binding.btnCreate.isEnabled = true
                        }
                        UiState.Loading -> {
                            binding.pbLoad.visibility = View.VISIBLE
                            binding.btnCreate.isEnabled = false
                        }
                    }
                }
            }
        }
    }

    private fun setImage(string: String) {
        binding.ivT2i.setImageBitmap(Base64Converter.string2Bitmap(string))
    }
}
