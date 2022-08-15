package com.example.dotpay.application

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.dotpay.presentation.brands.BrandFragment
import com.example.dotpay.presentation.producttypes.MakeUpFragment
import javax.inject.Inject


class FragmentFactory @Inject constructor(): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MakeUpFragment::class.java.name -> MakeUpFragment()
           BrandFragment::class.java.name -> BrandFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}