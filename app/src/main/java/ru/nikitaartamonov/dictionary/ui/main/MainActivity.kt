package ru.nikitaartamonov.dictionary.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.dictionary.data.di.DiStorage
import ru.nikitaartamonov.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val presenter: MainContract.Presenter = DiStorage.getMainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.attach(this)
        initViews()
    }

    private fun initViews() {
        binding.addButton.setOnClickListener {
            presenter.addWord(binding.searchEditText.text.toString())
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateList() {
        //todo
    }

    override fun onDestroy() {
        presenter.detach()
        if (isFinishing) DiStorage.clearMainActivityPresenter()
        super.onDestroy()
    }
}