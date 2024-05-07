package com.gukunov.features.searchFood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gukunov.entity.food.Food
import com.gukunov.features.databinding.FoodItemBinding

class FoodListAdapter : RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {

    private val differ = AsyncListDiffer(this, FoodListAdapter.diffCallback)


    fun setData(items: List<Food>) {
        differ.submitList(items)
    }


    inner class FoodListViewHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: Food) {

            binding.priceTxt.text = viewModel.Price.toString()

            binding.priceTxt.text = viewModel.Price.toString()
            binding.timeTxt.text = viewModel.TimeValue.toString()
            binding.titleTxt.text = viewModel.Title
            binding.starTxt.text = viewModel.Star.toString()



            val url = viewModel.ImagePath
            Glide.with(binding.root).load(url).into(binding.img)


//            val url = viewModel.ImagePath
//            Glide.with(binding.root).load(url).into(binding.imgCat)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodItemBinding.inflate(inflater, parent, false)
        return FoodListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        differ.currentList.getOrNull(position)?.let {
            holder.bind(it)

        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(
                oldItem: Food,
                newItem: Food
            ): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(
                oldItem: Food,
                newItem: Food
            ): Boolean {
                return oldItem == newItem
            }

        }

    }
}