package com.gukunov.features.mainScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gukunov.entity.food.Category
import com.gukunov.entity.food.Food
import com.gukunov.features.databinding.BestFoodItemBinding
import com.gukunov.features.databinding.CategoryItemBinding

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    private val differ = AsyncListDiffer(this, diffCallback)


    fun setData(items: List<Category>){
        differ.submitList(items)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainCategoryAdapter.MainCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return MainCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainCategoryAdapter.MainCategoryViewHolder, position: Int) {
        differ.currentList.getOrNull(position)?.let {
            holder.bind(it)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MainCategoryViewHolder(private val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(viewModel: Category){

            binding.titleCatTxt.text = viewModel.Name





//            val url = viewModel.ImagePath
//            Glide.with(binding.root).load(url).into(binding.img)


        }
    }

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<Category>(){
            override fun areItemsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem.Id==newItem.Id
            }

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean {
                return oldItem==newItem
            }

        }

    }

}