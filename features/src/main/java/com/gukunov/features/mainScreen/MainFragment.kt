package com.gukunov.features.mainScreen

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gukunov.features.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


//data class Test(
//    var name: String,
//    var age: Int,
//    var id: Int
//)

@AndroidEntryPoint
class MainFragment : Fragment() {



    private val foodViewModel: FoodViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: MainFoodAdapter
    private lateinit var categoryAdapter: MainCategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFoodRecyclerAdapter()
        initCategoryRecyclerAdapter()
//        lifecycleScope.launch {
//            foodViewModel.getFoods()
//        }

//        val testList = listOf(
//            Test("Alice", 30, 1),
//            Test("Bob", 25, 2),
//            Test("Charlie", 35, 3),
//            Test("David", 28, 4),
//            Test("Emma", 32, 5)
//        )

        foodViewModel.foods.observe(viewLifecycleOwner, Observer {
            // Обновить UI с полученными данными о еде
            binding.progressBar.visibility = View.GONE

            val filterData = it.filter { it.BestFood == true }
            adapter.setData(filterData)
        })

        foodViewModel.category.observe(viewLifecycleOwner, Observer{
            binding.progressBarCategory.visibility = View.GONE
            categoryAdapter.setData(it)
        })


        binding.searchImg.setOnClickListener {
            val searchText = binding.searchTxt.text.toString()
            if (searchText.isNotEmpty()) {
                val action = MainFragmentDirections.actionMainFragmentToFoodListFragment(searchText)
                findNavController().navigate(action)
            } else {
                // Если текстовое поле пустое, показываем диалоговое окно
                showDialogToFillText()
            }
        }



    }

    fun initFoodRecyclerAdapter() {
        adapter = MainFoodAdapter()
        binding.foodRW.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.foodRW.adapter = adapter
    }

    fun initCategoryRecyclerAdapter() {
        categoryAdapter = MainCategoryAdapter{
            val action = it.Id?.let { it1 ->
                it.Name?.let { it2 ->
                    MainFragmentDirections.actionMainFragmentToShowFoodByCategoryFragment(
                        it1,
                        it2
                    )
                }
            }
            findNavController().navigate(action!!)

        }
        binding.categoryRecyclerView.layoutManager =
            GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter
    }

    private fun showDialogToFillText() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Пустой запрос")
            .setMessage("Пожалуйста, введите текст для поиска")
            .setPositiveButton("ОК") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

}