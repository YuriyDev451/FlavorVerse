package com.gukunov.features.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gukunov.entity.food.Category
import com.gukunov.features.R
import com.gukunov.features.databinding.CategoryItemBinding

class MainCategoryAdapter(private val onItemClick: (Category) -> Unit) : RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {

    private val differ = AsyncListDiffer(this, diffCallback)

    fun setData(items: List<Category>) {
        differ.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return MainCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MainCategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.titleCatTxt.text = category.Name
            Glide.with(binding.root).load(category.ImagePath).into(binding.imgCat)

            // Обработка клика на элементе
//            binding.root.setOnClickListener {
//                val bundle = bundleOf("categoryId" to category.Id)
//                it.findNavController().navigate(R.id.action_mainFragment_to_foodListFragment, bundle)
//            }

            binding.root.setOnClickListener {
                onItemClick(category)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }
}
