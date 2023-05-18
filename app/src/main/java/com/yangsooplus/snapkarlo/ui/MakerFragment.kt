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
        viewModel.getT2iImage("bubble sugar sweet")
        collectImage()
    }

    private fun collectImage() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.t2iResponseState.collect { t2i ->
                    when (t2i) {
                        is ApiState.Error -> {}
                        is ApiState.Loading -> {}
                        is ApiState.Success -> t2i.data?.let { setImage(it.images[0].image) }
                    }
                }
            }
        }
    }

    private fun setImage(string: String) {
        binding.ivMaker.setImageBitmap(Base64Converter.string2Bitmap(string))
    }
}
