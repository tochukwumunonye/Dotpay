package com.example.dotpay.presentation.producttypes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dotpay.R
import com.example.dotpay.databinding.FragmentMakeupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MakeUpFragment : Fragment(R.layout.fragment_makeup){

    lateinit var makeupAdapter: MakeupAdapter
    private val viewModel: MakeupViewModel by viewModels()

    val args by navArgs<MakeUpFragmentArgs>()

    private val brandName: String by lazy {
        args.selectedBrand
    }

    private var _binding: FragmentMakeupBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMakeupBinding.bind(view)

        makeupAdapter = MakeupAdapter()

        binding.apply {
            makeupRecyclerview.apply {
                adapter = makeupAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                itemAnimator?.changeDuration = 0
            }
        }

        binding.brandName.text = brandName

        binding.blush.setOnClickListener {
            val productType = binding.blush.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.bronzer.setOnClickListener {
            val productType = binding.bronzer.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.eyebrow.setOnClickListener {
            val productType = binding.eyebrow.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.foundation.setOnClickListener {
            val productType = binding.foundation.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.Eyeshadow.setOnClickListener {
            val productType = binding.Eyeshadow.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.lipLiner.setOnClickListener {
            val productType = binding.lipLiner.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.mascara.setOnClickListener {
            val productType = binding.mascara.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.nailPolish.setOnClickListener {
            val productType = binding.nailPolish.text.toString()
            viewModel.search(brandName,productType)
        }

        binding.lipstick.setOnClickListener {
            val productType = binding.lipstick.text.toString()
            viewModel.search(brandName,productType)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {viewState ->
                    when(viewState) {
                        is MakeupListViewState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            makeupAdapter.submitList(viewState.product)
                        }

                        is MakeupListViewState.Error -> {
                            binding.progressBar.visibility =  View.GONE
                            Toast.makeText(
                                requireContext(),
                                viewState.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is MakeupListViewState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
/**
 *
 *

almay
alva
anna sui
annabelle
benefit
boosh
burt's bees
butter london
c'est moi
cargo cosmetics
china glaze
clinique
coastal classic creation
colourpop
covergirl
dalish
deciem
dior
dr. hauschka
e.l.f.
essie

maia's mineral galaxy
marcelle
marienatie
maybelline
milani
mineral fusion
misa
mistura
moov
nudus
nyx
orly
pacifica
penny lane organics
physicians formula
piggy paint
pure anada
rejuva minerals
revlon
sally b's skin yummies
salon perfect
sante
sinful colours
smashbox
stila
suncoat
w3llpeople
wet n wild
zorah
zorah biocosmetiques
 **/