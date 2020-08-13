package com.pawelsobaszek.rickandmortycharacters.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pawelsobaszek.rickandmortycharacters.R
import com.pawelsobaszek.rickandmortycharacters.model.Character
import com.pawelsobaszek.rickandmortycharacters.util.getProgressDrawable
import com.pawelsobaszek.rickandmortycharacters.util.loadImage
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersListAdapter(var characters: ArrayList<Character>): RecyclerView.Adapter<CharactersListAdapter.CharactersViewHolder>() {

    fun updateCharacters(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharactersViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharactersListAdapter.CharactersViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.iv_character_image
        private val characterName = view.tv_character_name
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(character: Character) {
            characterName.text = character.name
            imageView.loadImage(character.image, progressDrawable)
        }
    }
}