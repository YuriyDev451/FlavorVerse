package com.gukunov.features.getFoodByCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.gukunov.features.R
import com.gukunov.features.databinding.FragmentFoodListBinding
import com.gukunov.features.searchFood.FoodListAdapter
import com.gukunov.features.searchFood.FoodListFragmentArgs
import com.gukunov.features.searchFood.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowFoodByCategoryFragment : Fragment() {

    private lateinit var binding: FragmentFoodListBinding
    private lateinit var adapter: FoodListAdapter
    private val viewModel: ShowFoodByCategoryViewModel by viewModels()

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

        val safeArgs: ShowFoodByCategoryFragmentArgs by navArgs()
        val categoryId = safeArgs.categoryId
        val title = safeArgs.categoryName

        binding.titleTxt.text=title

         viewModel.loadFoodsByCategory(categoryId)

        viewModel.foods.observe(viewLifecycleOwner, Observer { foodsByCateg ->
            adapter.setData(foodsByCateg)
            binding.progressBar2.visibility = View.GONE
        })
    }

    private fun initRecyclerAdapter() {
        adapter = FoodListAdapter()
        binding.foodList.layoutManager = GridLayoutManager(context, 2)
        binding.foodList.adapter = adapter
    }

}