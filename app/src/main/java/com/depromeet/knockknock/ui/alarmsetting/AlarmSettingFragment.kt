package com.depromeet.knockknock.ui.alarmsetting

import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.depromeet.knockknock.R
import com.depromeet.knockknock.base.BaseFragment
import com.depromeet.knockknock.databinding.FragmentAlarmSettingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlarmSettingFragment : BaseFragment<FragmentAlarmSettingBinding, AlarmSettingViewModel>(R.layout.fragment_alarm_setting) {

    private val TAG = "AlarmSettingFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_alarm_setting

    override val viewModel : AlarmSettingViewModel by viewModels()

    private val navController: NavController by lazy { findNavController() }

    override fun initStartView() {
        binding.apply {
            this.vm = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initToolbar()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initToolbar() {
        with(binding.toolbar) {
            // 뒤로가기 버튼
//            this.setNavigationIcon(R.drawable.ic_back)
            this.setNavigationOnClickListener { navController.popBackStack() }
        }
    }
}