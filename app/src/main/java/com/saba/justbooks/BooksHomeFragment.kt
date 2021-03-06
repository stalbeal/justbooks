package com.saba.justbooks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saba.core.adapter.CategoryClickListener
import com.saba.core.adapter.CategorySelectorAdapter
import com.saba.core.adapter.ViewCategory
import com.saba.core.di.CoreComponentProvider
import com.saba.core.models.ViewBook
import com.saba.justbooks.home.mvi.BooksHomeViewState
import com.saba.justbooks.databinding.FragmentBooksHomeBinding
import com.saba.justbooks.home.adapter.BooksAdapter
import com.saba.justbooks.home.adapter.BooksClickListener
import com.saba.justbooks.home.di.DaggerHomeComponent
import com.saba.justbooks.home.mvi.BooksHomeWish
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [BooksHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalStdlibApi
class BooksHomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: BooksHomeViewModel

    private lateinit var binding : FragmentBooksHomeBinding

    private lateinit var currentStateBooks : BooksHomeViewState

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerHomeComponent.builder()
            .coreComponent((requireActivity().application as CoreComponentProvider).provideCoreComponent())
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBooksHomeBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupViewModelInteractions()
    }

    @ExperimentalStdlibApi
    private fun setup() {

        with(binding.rvCategories) {
            val colorsList = arrayOf(R.color.red, R.color.pink, R.color.deep_purple, R.color.indigo, R.color.blue, R.color.teal, R.color.green, R.color.deep_orange)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL ,false)
            adapter = CategorySelectorAdapter(colorsList, object : CategoryClickListener {

                override fun onClick(categoryText: String) {
                    onCategorySelected(categoryText)
                }
            }, R.layout.item_category_horizontal)
        }

        with(binding.rvBooks) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
            adapter = BooksAdapter(object : BooksClickListener {
                override fun onClick(book: ViewBook) {

                    val action = BooksHomeFragmentDirections.actionBooksHomeFragmentToBookDetail(book.googleBooksId)
                    view?.findNavController()?.navigate(action)
                }
            })
        }

    }

    private fun onCategorySelected(category: String) {
        viewModel.onWish(BooksHomeWish.GetBooksByCategory(category))
    }

    private fun setupViewModelInteractions() {
        viewModel.state.onEach { state ->
            currentStateBooks = state
            when {
                state.isIdling -> onIdling()
                state.isLoading -> onLoading()
                state.categories != null -> showCategories(state.categories)
                state.books != null -> showBooks(state.books)
            }
        }.launchIn(lifecycleScope)
        viewModel.onWish(BooksHomeWish.GetCategories)
        viewModel.onWish(BooksHomeWish.GetBooks)

    }

    private fun showBooks(books: Collection<ViewBook>) {
        (binding.rvBooks.adapter as BooksAdapter).submitList(books.toList())
    }


    private fun showCategories(categories: Collection<ViewCategory>) {
        (binding.rvCategories.adapter as CategorySelectorAdapter).submitList(categories.toList())

    }

    private fun onLoading() {
        // DO_NOTHING
    }

    private fun onIdling() {
        // DO_NOTHING
    }

}

