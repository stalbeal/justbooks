package com.saba.categoryselector

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.saba.categoryselector.databinding.ActivityCategorySelectorBinding
import com.saba.categoryselector.di.DaggerCategorySelectorComponent
import com.saba.categoryselector.mvi.CategorySelectorViewState
import com.saba.categoryselector.mvi.CategorySelectorWish
import com.saba.core.adapter.CategoryClickListener
import com.saba.core.adapter.CategorySelectorAdapter
import com.saba.core.adapter.ViewCategory
import com.saba.core.di.CoreComponentProvider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalStdlibApi
class CategorySelectorActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: CategorySelectorViewModel

    private lateinit var binding: ActivityCategorySelectorBinding

    private lateinit var currentState : CategorySelectorViewState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorySelectorBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        DaggerCategorySelectorComponent.builder()
            .coreComponent((application as CoreComponentProvider).provideCoreComponent())
            .build()
            .inject(this)

        setup()
        setupViewModelInteractions()
    }

    private fun setup() {

        with(binding.rvCategories) {
            val colorsList = arrayOf(R.color.red, R.color.pink, R.color.deep_purple, R.color.indigo, R.color.blue, R.color.teal, R.color.green, R.color.deep_orange)
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = CategorySelectorAdapter(colorsList, object : CategoryClickListener {

                override fun onClick(categoryText: String) {
                    onCategorySelected(categoryText)
                }
            })
        }

        binding.btnSave.setOnClickListener {
            onSaveCategoriesSelected()
        }
    }

    private fun setupViewModelInteractions() {
        viewModel.state.onEach { state ->
            currentState = state
            when {
                state.isIdling -> onIdling()
                state.isLoading -> onIdling()//cambiar
                state.categoriesSaved -> goToHome()
                state.shownCategories != null -> showCategories(state.shownCategories)
            }
        }.launchIn(lifecycleScope)
        viewModel.onWish(CategorySelectorWish.GetCategories)

    }

    private fun showCategories(categories: Collection<ViewCategory>) {
        (binding.rvCategories.adapter as CategorySelectorAdapter).submitList(categories.toList())
    }

    private fun onCategorySelected(text: String){
        require(currentState.shownCategories != null)
        viewModel.onWish(CategorySelectorWish.SelectCategory(text, currentState.shownCategories!!))
    }

    private fun onSaveCategoriesSelected(){
        viewModel.onWish(CategorySelectorWish.SaveSelection(currentState.shownCategories!!))
    }

    private fun goToHome(){
        finish()
    }

    private fun onIdling() {
        // DO_NOTHING
    }
}

