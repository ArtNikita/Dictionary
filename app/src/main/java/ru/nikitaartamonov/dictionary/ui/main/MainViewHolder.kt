package ru.nikitaartamonov.dictionary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nikitaartamonov.dictionary.R
import ru.nikitaartamonov.dictionary.databinding.MainViewHolderBinding
import ru.nikitaartamonov.dictionary.domain.MainRvPairOfWordsEntity

class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.main_view_holder, parent, false)
) {

    private val binding by lazy { MainViewHolderBinding.bind(itemView) }

    fun bind(item: MainRvPairOfWordsEntity) {
        binding.itemTextView.text = convertToString(item)
    }

    private fun convertToString(item: MainRvPairOfWordsEntity) = "${item.word} - ${item.meaning}"
}