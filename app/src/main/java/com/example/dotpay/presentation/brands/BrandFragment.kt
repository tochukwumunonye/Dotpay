package com.example.dotpay.presentation.brands

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dotpay.R
import com.example.dotpay.databinding.FragmentBrandsBinding
import dagger.hilt.android.AndroidEntryPoint

class BrandFragment : Fragment(R.layout.fragment_brands){

    private var _binding: FragmentBrandsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentBrandsBinding.bind(view)

        binding.btngo.setOnClickListener {
           val selected = binding.spinner.selectedItem.toString()
            val action = BrandFragmentDirections.actionBrandFragmentToMakeUp(selected)
            findNavController().navigate(action)
        }

    }

}