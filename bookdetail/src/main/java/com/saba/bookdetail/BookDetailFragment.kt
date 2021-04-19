package com.saba.bookdetail

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.saba.bookdetail.databinding.FragmentBookDetailBinding
import com.saba.bookdetail.di.DaggerBookDetailComponent
import com.saba.core.di.CoreComponentProvider
import com.saba.core.extensions.setImage
import com.saba.core.models.ViewBook
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookDetailFragment : Fragment() {

    @Inject
    lateinit var bookDetailViewModel: BookDetailViewModel

    private lateinit var binding: FragmentBookDetailBinding

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerBookDetailComponent.builder()
            .coreComponent((requireActivity().application as CoreComponentProvider).provideCoreComponent())
            .build()
            .inject(this)

        observeState()

        var args = ""
        args = requireArguments().getString("isbn")!!
        bookDetailViewModel.onCreate(args)

    }

    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            bookDetailViewModel.copyState.collect { state ->
                when {
                    //state.isIdling -> onIdling()
                    state.isLoading -> onIdling()//cambiar
                    state.book != null -> setUpView(state.book)
                    state.error != null -> showError(state.error)
                }
            }
        }
    }

    private fun showError(error: Exception) {
        //showError
    }

    private fun setUpView(viewBook: ViewBook) {
        viewBook.imageLinks?.let { image ->
            when {
                image.medium != null -> binding.ivImage.setImage(image.medium!!)
                image.large != null -> binding.ivImage.setImage(image.large!!)
                image.small != null -> binding.ivImage.setImage(image.small!!)
                image.extraLarge != null -> binding.ivImage.setImage(image.extraLarge!!)
                image.thumbnail != null -> binding.ivImage.setImage(image.thumbnail!!)
                image.smallThumbnail != null -> binding.ivImage.setImage(image.smallThumbnail!!)
            }
        }
        binding.tvTitle.text = viewBook.title
        binding.tvSubtitle.text = viewBook.subtitle
        viewBook.description?.let {
            binding.tvDescription.text = Html.fromHtml(it)
        }
        binding.tvAuthor.text = viewBook.authors.joinToString(", ")
        binding.tvPagesQuantity.text = viewBook.pageCount.toString()
        binding.tvPublishedDate.text = viewBook.publishedDate

    }

    private fun onIdling() {
        //onIdling
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookDetailBinding
            .inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

}