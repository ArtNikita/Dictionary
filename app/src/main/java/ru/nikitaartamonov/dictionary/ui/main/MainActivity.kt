package ru.nikitaartamonov.dictionary.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.nikitaartamonov.dictionary.data.DiStorage
import ru.nikitaartamonov.dictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val presenter: MainContract.Presenter = DiStorage.getMainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        if (isFinishing) DiStorage.clearMainActivityPresenter()
        super.onDestroy()
    }
}