package com.pawelsobaszek.rickandmortycharacters.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawelsobaszek.rickandmortycharacters.R
import com.pawelsobaszek.rickandmortycharacters.model.Character
import com.pawelsobaszek.rickandmortycharacters.model.CharacterClickListener
import com.pawelsobaszek.rickandmortycharacters.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    CharacterClickListener {

    lateinit var viewModel: ListViewModel
    private val charactersAdapter = CharactersListAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        charactersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersAdapter
        }

        setOnRefreshListener()
        setOnScrollListener(false)
        observeViewModel()
    }


    fun setOnRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refreshAll()
        }
    }

    fun setOnScrollListener(isLoading: Boolean) {
        charactersList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (isLoading) {
                    //nothing
                } else {
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.nextPage()
                    }
                }
            }
        })
    }

    fun observeViewModel() {
        viewModel.characters.observe(this, Observer { characters ->
            characters?.let {
                charactersList.visibility = View.VISIBLE
                list_error.visibility = View.GONE
                charactersAdapter.updateCharacters(characters)
            }
        })

        viewModel.charactersLoadError.observe(this, Observer { isError ->
            isError?.let { if (it) {
                list_error.visibility = View.VISIBLE
                charactersList.visibility = View.GONE
            } else View.GONE }
        })

        viewModel.newPageSuccesAdded.observe(this, Observer { newPageSuccesAdded ->
            newPageSuccesAdded?.let { if (!it) Toast.makeText(this, "Brak połączenia z internetem", Toast.LENGTH_SHORT).show()}
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                rotateLoading.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    setOnScrollListener(true)
                    rotateLoading.start()
                } else {
                    setOnScrollListener(false)
                    rotateLoading.stop()
                }
            }
        })
    }

    override fun onCharacterClick(character: Character) {
        startActivity(CharacterDetailActivity.getIntent(this, character))
    }

}