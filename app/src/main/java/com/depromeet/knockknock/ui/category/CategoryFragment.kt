package com.depromeet.knockknock.ui.category

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.depromeet.knockknock.R
import com.depromeet.knockknock.base.BaseFragment
import com.depromeet.knockknock.databinding.FragmentCategoryBinding
import com.depromeet.knockknock.ui.category.adapter.CategoryAdapter
import com.depromeet.knockknock.ui.category.model.Category
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding, CategoryViewModel>(R.layout.fragment_category) {

    private val TAG = "CategoryFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_category

    override val viewModel : CategoryViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val categoryAdapter by lazy { CategoryAdapter(viewModel) }

    override fun initStartView() {
        binding.apply {
            this.viewmodel  = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initToolbar()
        initAdapter()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onSaveSuccess.collectLatest {
                navController.popBackStack()
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun initToolbar() {
        with(binding.toolbar) {
            this.title = getString(R.string.category_title)
            // 뒤로가기 버튼
            this.setNavigationIcon(R.drawable.ic_allow_back)
            this.setNavigationOnClickListener { navController.popBackStack() }
        }
    }

    private fun initAdapter() {
        val test1 = Category(
            categoryId = 1,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val test2 = Category(
            categoryId = 2,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val test3 = Category(
            categoryId = 3,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val test4 = Category(
            categoryId = 4,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val test5 = Category(
            categoryId = 5,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val test6 = Category(
            categoryId = 6,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val test7 = Category(
            categoryId = 7,
            categoryName = "\uD83D\uDCD2 스터디"
        )
        val testList = listOf(test1, test2, test3, test4, test5, test6, test7)
        categoryAdapter.submitList(testList)

        val flexLayoutManager = FlexboxLayoutManager(requireContext()).apply {
            this.flexDirection = FlexDirection.ROW
            this.justifyContent = JustifyContent.FLEX_START
            this.alignItems = AlignItems.FLEX_START
        }

        binding.categoryRecycler.apply {
            this.adapter = categoryAdapter
            this.layoutManager = flexLayoutManager
        }
    }
}