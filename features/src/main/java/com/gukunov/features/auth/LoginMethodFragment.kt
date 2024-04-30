package com.gukunov.features.auth

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentLoginMethodBinding


class LoginMethodFragment : Fragment() {

    private lateinit var binding : FragmentLoginMethodBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginMethodBinding.inflate(inflater, container, false)
        return binding.root


    }


}