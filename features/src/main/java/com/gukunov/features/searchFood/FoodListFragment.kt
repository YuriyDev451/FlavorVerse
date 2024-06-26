package com.gukunov.features.searchFood

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodListFragment : Fragment() {

    private lateinit var binding: FragmentFoodListBinding
    private lateinit var adapter: FoodListAdapter
    private val viewModel: FoodListViewModel by viewModels()

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
        val safeArgs: FoodListFragmentArgs by navArgs()
        val searchQuery = safeArgs.searchQuery



        binding.titleTxt.text = searchQuery
        viewModel.searchFood(searchQuery)

        viewModel.searchResult.observe(viewLifecycleOwner, Observer { foods ->
            adapter.setData(foods)
            binding.progressBar2.visibility = View.GONE
        })


        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initRecyclerAdapter() {
        adapter = FoodListAdapter()
        binding.foodList.layoutManager = GridLayoutManager(context, 2)
        binding.foodList.adapter = adapter

        adapter.setOnItemClickListener { detail ->
            val bundle = Bundle().apply {
                putParcelable("detail", detail)
            }
            findNavController().navigate(R.id.action_foodListFragment_to_foodDetailFragment, bundle)
        }
    }
}