package com.scabher.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.scabher.marvelheroes.R
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import kotlinx.android.synthetic.main.activity_hero_detail.*

/**
 * Created by costular on 18/03/2018.
 */
class MarvelHeroeDetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_HEROE = "heroe"
    }

    lateinit var heroDetailViewModel: MarvelHeroeDetailViewModel
    var hero: MarvelHeroEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        heroDetailViewModel = ViewModelProviders.of(this).get(MarvelHeroeDetailViewModel::class.java)

        hero = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let { fillHeroData(it) }
    }

    private fun fillHeroData(hero: MarvelHeroEntity) {
        Glide.with(this)
                .load(hero.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(heroDetailImage)

        heroDetailName.text = hero.name
        heroDetailRealName.text = hero.realName
        heroDetailHeight.text = hero.height
        heroDetailPower.text = hero.power
        heroDetailAbilities.text = hero.abilities
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}