package com.gukunov.features.splash

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieCompositionFactory
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {


    //private lateinit var lottieAnimationView: LottieAnimationView

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.fragment_splash, container, false)
//        lottieAnimationView = view?.findViewById(R.id.lottie) ?:
//        return view
        binding=FragmentSplashBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LottieCompositionFactory.fromRawRes(requireContext(), R.raw.animation)
            .addListener { composition ->
                binding.lottie.setComposition(composition)
                binding.lottie.repeatCount = 0 // Отключаем повторение анимации
                binding.lottie.addAnimatorListener(object : Animator.AnimatorListener {


                    override fun onAnimationStart(animation: Animator) {
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        navigateToNextFragment()
                    }

                    override fun onAnimationCancel(animation: Animator) {
                    }

                    override fun onAnimationRepeat(animation: Animator) {
                    }
                })
                binding.lottie.playAnimation()
            }
    }

    private fun navigateToNextFragment() {
        // Переход на другой фрагмент
        val action = SplashFragmentDirections.actionSplashFragmentToLoginMethodFragment()
        findNavController().navigate(action)
    }
    }


