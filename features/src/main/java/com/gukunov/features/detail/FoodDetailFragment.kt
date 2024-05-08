package com.gukunov.features.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gukunov.entity.food.Food
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentFoodDetailBinding
import com.gukunov.features.getFoodByCategory.ShowFoodByCategoryFragmentArgs


class FoodDetailFragment : Fragment() {


    private lateinit var binding: FragmentFoodDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val safeArgs: FoodDetailFragmentArgs by navArgs()

//        val detail = safeArgs.detail

//        val url = detail.ImagePath
//        Glide.with(binding.root).load(url).into(binding.pic)
//
//        binding.titleTv.text = detail.Title
//        binding.priceTv.text = "${detail.Price.toString()}$"
//        binding.ratingTxt.text = detail.Star.toString()
//        binding.time.text = "${detail.TimeValue.toString()}min"
//        binding.description.text = detail.Description

        val args = requireArguments()
        val foodDetail: Food? = args.getParcelable("detail")


        foodDetail?.let {
            val url = it.ImagePath
            Glide.with(binding.root).load(url).into(binding.pic)

            binding.titleTv.text = it.Title
            binding.priceTv.text = "${it.Price.toString()}$"
            binding.ratingTxt.text = it.Star.toString()
            binding.time.text = "${it.TimeValue.toString()}min"
            binding.description.text = it.Description
            binding.ratingBar.rating = it.Star!!
        }

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}