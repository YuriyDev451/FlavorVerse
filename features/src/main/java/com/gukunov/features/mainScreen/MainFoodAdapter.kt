package com.gukunov.features.mainScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gukunov.entity.food.Food
import com.gukunov.features.databinding.BestFoodItemBinding

class MainFoodAdapter: RecyclerView.Adapter<MainFoodAdapter.MainFoodViewHolder>() {

    private val differ = AsyncListDiffer(this, diffCallback)


    fun setData(items: List<Food>){
        differ.submitList(items)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFoodAdapter.MainFoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BestFoodItemBinding.inflate(inflater, parent, false)
        return MainFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFoodAdapter.MainFoodViewHolder, position: Int) {
        differ.currentList.getOrNull(position)?.let {
            holder.bind(it)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class MainFoodViewHolder(private val binding: BestFoodItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(viewModel: Food){


            binding.priceTxt.text = viewModel.Price.toString()
            binding.timeTxt.text = viewModel.TimeValue.toString()
            binding.titleTxt.text = viewModel.Title
            binding.starTxt.text = viewModel.Star.toString()



            val url = viewModel.ImagePath
            Glide.with(binding.root).load(url).into(binding.img)


        }
    }

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<Food>(){
            override fun areItemsTheSame(
                oldItem: Food,
                newItem: Food
            ): Boolean {
                return oldItem.Id==newItem.Id
            }

            override fun areContentsTheSame(
                oldItem: Food,
                newItem: Food
            ): Boolean {
                return oldItem==newItem
            }

        }

    }
}