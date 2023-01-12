package com.depromeet.knockknock.ui.pushdetail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.depromeet.knockknock.R
import com.depromeet.knockknock.base.BaseFragment
import com.depromeet.knockknock.databinding.FragmentPushDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PushDetailFragment :
    BaseFragment<FragmentPushDetailBinding, PushDetailViewModel>(R.layout.fragment_push_detail) {

    private val TAG = "PushDetailFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_makers
    private val navController by lazy { findNavController() }

    override val viewModel: PushDetailViewModel by viewModels()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initToolbar()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigationHandler.collectLatest {
                when (it) {
                }
            }
        }
    }

    private fun initToolbar() {
        with(binding.toolbarMakers) {
            this.title = "주호민의 푸시알림"

            // 뒤로가기 버튼
            this.setNavigationIcon(com.depromeet.knockknock.R.drawable.ic_allow_back)
            this.setNavigationOnClickListener { navController.popBackStack() }
        }
    }

    override fun initAfterBinding() {
    }
}