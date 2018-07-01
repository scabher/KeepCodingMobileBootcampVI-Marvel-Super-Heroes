package com.scabher.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.scabher.marvelheroes.R
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import com.scabher.marvelheroes.presentation.MainApp
import kotlinx.android.synthetic.main.activity_hero_detail.*
import javax.inject.Inject

/**
 * Created by costular on 18/03/2018.
 */
class MarvelHeroeDetailActivity : AppCompatActivity() {

    companion object {
        const val PARAM_HEROE = "heroe"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var heroDetailViewModel: MarvelHeroeDetailViewModel

    private var hero: MarvelHeroEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation

        setUpViewModel()
        loadHeroData()
        addViewsListeners()

    }

    fun inject() {
        (application as MainApp).component.inject(this)
    }

    private fun loadHeroData() {
        hero = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let {
            fillHeroData(it)
            heroDetailViewModel.isHeroFavorite(it.name)
        }
    }

    private fun setUpViewModel() {
        heroDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MarvelHeroeDetailViewModel::class.java)
        bindEvents()
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

        setFavorite()
    }

    private fun setFavorite() {
        hero?.let {
            val color = if (it.isFavorite)
                    android.R.color.holo_green_dark
                else
                    R.color.material_grey_600

            btnFavorite.setTextColor(ContextCompat.getColor(this, color))
        }
    }


    private fun addViewsListeners() {
        btnFavorite.setOnClickListener {
            hero?.let { hero ->
                hero.isFavorite = !hero.isFavorite
                heroDetailViewModel.setHeroFavorite(hero.name, hero.isFavorite)
            }
        }
    }

    private fun bindEvents() {
        heroDetailViewModel.heroState.observe(this, Observer { heroUpdated ->
            hero?.let { hero ->
                heroUpdated?.let { heroUpdated ->
                    hero.isFavorite = heroUpdated.isFavorite
                    setFavorite()
                }
            }
        })
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