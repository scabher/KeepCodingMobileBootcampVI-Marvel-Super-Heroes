package com.scabher.marvelheroes.viewmodel

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.scabher.marvelheroes.domain.usecase.GetMarvelHeroesList
import com.scabher.marvelheroes.presentation.heroeslist.HeroesListViewModel
import org.junit.Before
import org.junit.Test

class HeroesListViewModelTest {

    val usecase: GetMarvelHeroesList = mock()

    lateinit var viewModel: HeroesListViewModel

    @Before
    fun setUp() {
        viewModel = HeroesListViewModel(usecase)
    }

    @Test
    fun `test presenter initialization`() {
        viewModel.loadMarvelHeroes()

//        verify(view).showLoading(true)
        verify(usecase).execute(any(), any())
    }
}