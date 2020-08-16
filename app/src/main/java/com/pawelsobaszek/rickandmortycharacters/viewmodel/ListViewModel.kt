package com.pawelsobaszek.rickandmortycharacters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pawelsobaszek.rickandmortycharacters.di.DaggerApiComponent
import com.pawelsobaszek.rickandmortycharacters.model.Character
import com.pawelsobaszek.rickandmortycharacters.model.CharacterList
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
    val nextPageCharacters = MutableLiveData<List<Character>>()
    val charactersLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val page = MutableLiveData<Int>()
    val nextPage = MutableLiveData<Int>()
    val newPageSuccesAdded = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCharacters()
    }

    fun refreshAll() {
        fetchCharactersAfterSwipeUp()
    }

    fun nextPage() {
        if (nextPage.value!! <= page.value!!) {
            fetchNextPageCharacters()
        }
    }

    private fun fetchCharacters() {
        loading.value = true
        if (characters.value == null) {
            disposable.add(
                        charactersService.getCharacters("1")
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object: DisposableSingleObserver<CharacterList>() {
                                override fun onSuccess(value: CharacterList?) {
                                    characters.value = value?.results
                                    charactersLoadError.value = false
                                    loading.value = false
                                    page.value = value?.info?.pages
                                    nextPage.value = 2
                                }

                                override fun onError(e: Throwable?) {
                                    charactersLoadError.value = true
                                    loading.value = false
                                }
                            })
            )
        } else {
            charactersLoadError.value = false
            loading.value = false
        }
    }

    private fun fetchNextPageCharacters() {
        loading.value = true
        disposable.add(
            charactersService.getCharacters(nextPage.value.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<CharacterList>() {
                    override fun onSuccess(value: CharacterList?) {
                        nextPage.value = nextPage.value?.plus(1)
                        newPageSuccesAdded.value = true
                        nextPageCharacters.value = value?.results
                        loading.value = false
                        characters.value = characters.value?.plus(nextPageCharacters.value!!)
                    }
                    override fun onError(e: Throwable?) {
                        newPageSuccesAdded.value = false
                        loading.value = false
                    }
                })
        )
    }

    private fun fetchCharactersAfterSwipeUp() {
        loading.value = true
        disposable.add(
            charactersService.getCharacters("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<CharacterList>() {
                    override fun onSuccess(value: CharacterList?) {
                        characters.value = value?.results
                        charactersLoadError.value = false
                        loading.value = false
                        page.value = value?.info?.pages
                        nextPage.value = 2
                    }

                    override fun onError(e: Throwable?) {
                        charactersLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}