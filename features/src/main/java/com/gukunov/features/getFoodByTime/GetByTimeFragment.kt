package com.gukunov.features.getFoodByTime

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentFoodListBinding
import com.gukunov.features.getFoodByPrice.ShowFoodByPriceFragmentArgs
import com.gukunov.features.getFoodByPrice.ShowFoodByPriceViewModel
import com.gukunov.features.searchFood.FoodListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetByTimeFragment : Fragment() {
    private lateinit var binding: FragmentFoodListBinding
    private lateinit var adapter: FoodListAdapter
    private val viewModel: GetByTimeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerAdapter()

        val safeArgs: GetByTimeFragmentArgs by navArgs()
        val timeId = safeArgs.time

        viewModel.loadFoodsByCategory(timeId)

        viewModel.foods.observe(viewLifecycleOwner, Observer { foodsByTime ->
            adapter.setData(foodsByTime)
            binding.progressBar2.visibility = View.GONE
        })

        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.titleTxt.visibility = View.GONE
    }

    private fun initRecyclerAdapter() {
        adapter = FoodListAdapter()
        binding.foodList.layoutManager = GridLayoutManager(context, 2)
        binding.foodList.adapter = adapter

        adapter.setOnItemClickListener { detail ->
            val bundle = Bundle().apply {
                putParcelable("detail", detail)
            }
            findNavController().navigate(R.id.action_getByTimeFragment_to_foodDetailFragment, bundle)
        }
    }


}