package com.scabher.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.scabher.marvelheroes.R
//import com.scabher.marvelheroes.di.components.DaggerGetMarvelHeroesListComponent
//import com.scabher.marvelheroes.di.modules.GetMarvelHeroesListModule
import com.scabher.marvelheroes.domain.model.MarvelHeroEntity
import com.scabher.marvelheroes.presentation.MainApp
import com.scabher.marvelheroes.presentation.util.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HeroesListActivity : AppCompatActivity(), HeroesListContract.View {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    // lateinit var presenter: HeroesListPresenter
    lateinit var heroesListViewModel: HeroesListViewModel

    lateinit var adapter: HeroesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecycler()
        setUpViewModel()
    }

    fun inject() {
//        DaggerGetMarvelHeroesListComponent.builder()
//                .applicationComponent((application as MainApp).component)
//                .getMarvelHeroesListModule(GetMarvelHeroesListModule(this))
//                .build()
//                .inject(this)
        (application as MainApp).component.inject(this)
    }

    private fun setUpRecycler() {
        adapter = HeroesListAdapter { hero, image -> goToHeroDetail(hero, image) }
        heroesListRecycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        heroesListRecycler.itemAnimator = DefaultItemAnimator()
        heroesListRecycler.adapter = adapter
    }

    private fun setUpViewModel() {
        heroesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(HeroesListViewModel::class.java)
        bindEvents()
        heroesListViewModel.loadMarvelHeroes()
    }

    private fun bindEvents() {
        heroesListViewModel.isLoadingState.observe(this, Observer { isLoading ->
            isLoading?.let {
                showLoading(it)
            }
        })

        heroesListViewModel.heroListState.observe(this, Observer { userList ->
            userList?.let {
                showHeroesList(it)
            }
        })
    }

    private fun goToHeroDetail(hero: MarvelHeroEntity, image: View) {
        navigator.goToHeroDetail(this, hero, image)
    }

    override fun showLoading(isLoading: Boolean) {
        heroesListLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun showHeroesList(heroes: List<MarvelHeroEntity>) {
        adapter.swapData(heroes)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show()
    }

}
