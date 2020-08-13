package com.pawelsobaszek.rickandmortycharacters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pawelsobaszek.rickandmortycharacters.di.DaggerApiComponent
import com.pawelsobaszek.rickandmortycharacters.model.Character
import com.pawelsobaszek.rickandmortycharacters.model.CharactersService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var charactersService: CharactersService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable()

    val characters = MutableLiveData<List<Character>>()
    val charactersLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        loading.value = true
        disposable.add(
            charactersService.getCharacters()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Character>>() {
                    override fun onSuccess(value: List<Character>?) {
                        characters.value = value
                        charactersLoadError.value = false
                        loading.value = false
                        print(characters)
                    }

                    override fun onError(e: Throwable?) {
                        charactersLoadError.value = true
                        loading.value = false
                        print(characters)
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}