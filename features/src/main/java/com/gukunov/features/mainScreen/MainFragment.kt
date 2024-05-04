package com.gukunov.features.mainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }


}