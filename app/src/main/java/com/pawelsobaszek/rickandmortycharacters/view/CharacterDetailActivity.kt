package com.pawelsobaszek.rickandmortycharacters.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pawelsobaszek.rickandmortycharacters.R
import com.pawelsobaszek.rickandmortycharacters.model.Character
import com.pawelsobaszek.rickandmortycharacters.util.getProgressDrawable
import com.pawelsobaszek.rickandmortycharacters.util.loadImage
import kotlinx.android.synthetic.main.character_details_layout.*

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_details_layout)

        setDetails()
    }

    fun setDetails() {
        val name = intent.getStringExtra(PARAM_NAME)
        val image = intent.getStringExtra(PARAM_IMAGE)
        val gender = intent.getStringExtra(PARAM_GENDER)
        val status = intent.getStringExtra(PARAM_STATUS)
        val origin = intent.getStringExtra(PARAM_ORIGIN)
        val location = intent.getStringExtra(PARAM_LOCATION)

        tv_detail_name.text = name
        iv_detail_character_image.loadImage(image, getProgressDrawable(this))
        tv_detail_gender.text = gender
        tv_detail_alive.text = status
        tv_detail_origin.text = origin
        tv_detail_location.text = location
    }

    companion object {
        val PARAM_NAME = "characterNAME"
        val PARAM_IMAGE = "characterIMAGE"
        val PARAM_GENDER = "characterGENDER"
        val PARAM_STATUS = "characterSTATUS"
        val PARAM_ORIGIN = "characterORIGIN"
        val PARAM_LOCATION = "characterLOCATION"

        fun getIntent(context: Context, character: Character?): Intent {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(PARAM_NAME, character?.name)
            intent.putExtra(PARAM_IMAGE, character?.image)
            intent.putExtra(PARAM_GENDER, character?.gender)
            intent.putExtra(PARAM_STATUS,  character?.status)
            intent.putExtra(PARAM_ORIGIN, character?.origin?.origin)
            intent.putExtra(PARAM_LOCATION, character?.location?.location)
            return intent
        }
    }

}