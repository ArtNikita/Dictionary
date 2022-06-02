package ru.nikitaartamonov.dictionary.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.dictionary.domain.MainRvPairOfWordsEntity

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainViewHolder>() {

    fun updateItems(newList: List<MainRvPairOfWordsEntity>) {
        val diffUtil = MainRvDiffUtil(items, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    private var items: List<MainRvPairOfWordsEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

private class MainRvDiffUtil(
    private val oldList: List<MainRvPairOfWordsEntity>,
    private val newList: List<MainRvPairOfWordsEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].word == newList[newItemPosition].word
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition, newItemPosition)
    }
}