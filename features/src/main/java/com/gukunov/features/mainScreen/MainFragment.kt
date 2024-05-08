package com.gukunov.features.mainScreen

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.gukunov.entity.food.Price
import com.gukunov.entity.food.Time
import com.gukunov.features.R
import com.gukunov.features.auth.LogFragmentDirections
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
    private lateinit var price: List<Price>
    private lateinit var time: List<Time>



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
        binding.signOut.setOnClickListener {
            Firebase.auth.signOut()
            val action = MainFragmentDirections.actionMainFragmentToLoginMethodFragment()
            findNavController().navigate(action)
        }

        val account: GoogleSignInAccount? = context?.let { GoogleSignIn.getLastSignedInAccount(it) }

// Проверяем, успешно ли выполнена аутентификация
        if (account != null) {
            // Получаем имя пользователя
            val userName: String? = account.displayName

            // Используем имя пользователя, например, отображаем его на экране
            if (userName != null) {
                // Например, отображаем имя пользователя в TextView
                binding.uName.text = userName
            }
        } else {
            // Пользователь не аутентифицирован, обрабатываем этот случай
        }





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


        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ArrayList<String>())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.priceSpinner.adapter = adapter


        foodViewModel.price.observe(viewLifecycleOwner, { prices ->
            price = prices
            val priceList = prices.map { it.Value } // Используйте reversed() для переворачивания списка
            adapter.clear()
            adapter.addAll(priceList)
            adapter.notifyDataSetChanged()
        })

         var isUserInteracted = false

        binding.priceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isUserInteracted) { // Проверяем, было ли пользовательское взаимодействие
                    val selectedPriceId = price[position].Id // Используем сохраненный список цен
                    val action = selectedPriceId?.toInt()?.let { MainFragmentDirections.actionMainFragmentToShowFoodByPriceFragment(it) }
                    if (action != null) {
                        findNavController().navigate(action)
                        isUserInteracted = false // Сбрасываем флаг после перехода на следующий экран
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Обработка события отсутствия выбора
            }
        }

        binding.priceSpinner.setOnTouchListener { _, _ ->
            isUserInteracted = true // Устанавливаем флаг, когда пользователь взаимодействует с элементами
            false
        }



        val timeAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ArrayList<String>())
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.timeSpinner.adapter = timeAdapter


        foodViewModel.time.observe(viewLifecycleOwner, { tim ->
            time = tim
            val timeList = tim.map { it.Value }
            timeAdapter.clear()
            timeAdapter.addAll(timeList)
            timeAdapter.notifyDataSetChanged()
        })
        var isInteracted = false

        binding.timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isInteracted) { // Проверяем, было ли пользовательское взаимодействие
                    val selectedTimeId = time[position].Id // Используем сохраненный список цен
                    val action = selectedTimeId?.toInt()?.let { MainFragmentDirections.actionMainFragmentToGetByTimeFragment2(it) }
                    if (action != null) {
                        findNavController().navigate(action)
                        isInteracted = false // Сбрасываем флаг после перехода на следующий экран
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Обработка события отсутствия выбора
            }
        }

        binding.timeSpinner.setOnTouchListener { _, _ ->
            isInteracted = true // Устанавливаем флаг, когда пользователь взаимодействует с элементами
            false
        }

    }

    fun initFoodRecyclerAdapter() {
        adapter = MainFoodAdapter()
        binding.foodRW.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.foodRW.adapter = adapter

        adapter.setOnItemClickListener { detail ->
            val bundle = Bundle().apply {
                putParcelable("detail", detail)
            }
            findNavController().navigate(R.id.action_mainFragment_to_foodDetailFragment, bundle)
        }
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