package ru.nikitaartamonov.dictionary.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.nikitaartamonov.dictionary.R
import ru.nikitaartamonov.dictionary.app
import ru.nikitaartamonov.dictionary.data.storage.PairOfWordsDao
import ru.nikitaartamonov.dictionary.databinding.ActivityMainBinding
import ru.nikitaartamonov.dictionary.domain.Error
import ru.nikitaartamonov.dictionary.domain.MainRvPairOfWordsEntity
import ru.nikitaartamonov.dictionary.viewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainContract.ViewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    private val adapter = MainRecyclerViewAdapter()

    @Inject
    lateinit var pairOfWordsDao: PairOfWordsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        app.appComponent.inject(this)
        initViews()
        initRv()
        initViewModel()
        updateList()
    }

    private fun initViews() {
        binding.addButton.setOnClickListener {
            viewModel.addWord(binding.searchEditText.text.toString())
        }
    }

    private fun initRv() {
        with(binding.mainRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun initViewModel() {
        viewModel.showErrorLiveData.observe(this) { event ->
            event.getContentIfNotHandled()?.let { showError(it) }
        }
        viewModel.updateListLiveData.observe(this) { event ->
            event.getContentIfNotHandled()?.let { updateList() }
        }
    }

    private fun showError(error: Error) {
        val errorMsg: String = when (error) {
            Error.NOT_A_WORD -> {
                getString(R.string.not_a_word_error_msg)
            }
            Error.CUSTOM -> {
                error.msg ?: getString(R.string.unknown_error_msg)
            }
        }
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

    private fun updateList() {
        pairOfWordsDao.getAll()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .map { list ->
                list.map { MainRvPairOfWordsEntity(it.word, it.meaning) }
            }
            .subscribeBy {
                adapter.updateItems(it)
            }
    }
}