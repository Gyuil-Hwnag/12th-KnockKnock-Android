package com.depromeet.knockknock.ui.alarmroomsearch

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.depromeet.domain.model.GroupBriefInfo
import com.depromeet.knockknock.R
import com.depromeet.knockknock.base.BaseFragment
import com.depromeet.knockknock.databinding.FragmentAlarmRoomSearchBinding
import com.depromeet.knockknock.databinding.FragmentFriendListBinding
import com.depromeet.knockknock.ui.alarmroomsearch.adapter.AlarmRoomSearchAdapter
import com.depromeet.knockknock.ui.alarmroomsearch.adapter.PopularCategoryAdapter
import com.depromeet.knockknock.ui.alarmroomsearch.model.AlarmRoom
import com.depromeet.knockknock.ui.category.model.Category
import com.depromeet.knockknock.ui.editroomdetails.model.Background
import com.depromeet.knockknock.ui.friendlist.adapter.FriendListAdapter
import com.depromeet.knockknock.ui.friendlist.bottom.BottomFriendMore
import com.depromeet.knockknock.ui.friendlist.bottom.FriendMoreType
import com.depromeet.knockknock.util.customOnFocusChangeListener
import com.depromeet.knockknock.util.hideKeyboard
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class AlarmRoomSearchFragment : BaseFragment<FragmentAlarmRoomSearchBinding, AlarmRoomSearchViewModel>(R.layout.fragment_alarm_room_search) {

    private val TAG = "AlarmRoomSearchFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_alarm_room_search

    override val viewModel : AlarmRoomSearchViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        initEditText()
        countEditTextMessage()
    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigationHandler.collectLatest {
                when(it) {
                    is AlarmRoomSearchNavigationAction.NavigateToRoom -> { moveToRoom(roomId = it.roomId) }
                    is AlarmRoomSearchNavigationAction.NavigateToHidePopularCategory -> {}
                    is AlarmRoomSearchNavigationAction.NavigateToMakeRoom -> {}
                }
            }
        }
    }

    override fun initAfterBinding() {
        binding.searchEditText.requestFocus()
        val alarmRoomAdapter = AlarmRoomSearchAdapter(viewModel)
        binding.alarmRoomRecycler.adapter = alarmRoomAdapter

        val test1 = GroupBriefInfo(
            category = com.depromeet.domain.model.Category(
                content = "취업",
                emoji = "https://t1.daumcdn.net/cfile/tistory/996333405A8280FC23",
                id = 1
            ),
            description = "취업을 위한 방 어쩌구 저쩌구 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            group_id = 1,
            group_type = "OPEN",
            member_count = 10,
            public_access = true,
            thumbnail_path = "https://t1.daumcdn.net/cfile/tistory/996333405A8280FC23",
            title = "방 제목"
        )

        val test2 = GroupBriefInfo(
            category = com.depromeet.domain.model.Category(
                content = "취업",
                emoji = "https://t1.daumcdn.net/cfile/tistory/996333405A8280FC23",
                id = 1
            ),
            description = "취업을 위한 방 어쩌구 저쩌구 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            group_id = 2,
            group_type = "OPEN",
            member_count = 10,
            public_access = true,
            thumbnail_path = "https://t1.daumcdn.net/cfile/tistory/996333405A8280FC23",
            title = "방 제목"
        )

        val test3 = GroupBriefInfo(
            category = com.depromeet.domain.model.Category(
                content = "취업",
                emoji = "https://t1.daumcdn.net/cfile/tistory/996333405A8280FC23",
                id = 1
            ),
            description = "취업을 위한 방 어쩌구 저쩌구 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            group_id = 3,
            group_type = "OPEN",
            member_count = 10,
            public_access = true,
            thumbnail_path = "https://t1.daumcdn.net/cfile/tistory/996333405A8280FC23",
            title = "방 제목"
        )

        val alarmRoomList = listOf(test1, test2, test3)

        alarmRoomAdapter.submitList(alarmRoomList)

        val popularCategoryAdapter = PopularCategoryAdapter(viewModel)
        binding.popularCategoryRecycler.adapter = popularCategoryAdapter


        val categoryTest1 = Category(
            categoryId = 1,
            categoryName = "\uD83D\uDCD2 스터디"
        )

        val categoryTest2 = Category(
            categoryId = 2,
            categoryName = "\uD83D\uDCD2 스터디"
        )

        val categoryTest3 = Category(
            categoryId = 3,
            categoryName = "\uD83D\uDCD2 스터디"
        )

        val categoryTest4 = Category(
            categoryId = 4,
            categoryName = "\uD83D\uDCD2 스터디"
        )

        val categoryTest5 = Category(
            categoryId = 5,
            categoryName = "\uD83D\uDCD2 스터디"
        )

        val popularCategoryList = listOf(categoryTest1, categoryTest2, categoryTest3, categoryTest4, categoryTest5)
        popularCategoryAdapter.submitList(popularCategoryList)

        val flexLayoutManager = FlexboxLayoutManager(requireContext()).apply {
            this.flexDirection = FlexDirection.ROW
            this.justifyContent = JustifyContent.FLEX_START
            this.alignItems = AlignItems.FLEX_START
        }

        binding.popularCategoryRecycler.apply {
            this.adapter = popularCategoryAdapter
            this.layoutManager = flexLayoutManager
        }
    }



//  해당하는 방으로 이동하는 로직
    private fun moveToRoom(roomId: Int) {

    }

    private fun hidePopularCategoty(){
        binding.popularCategoryFrame.visibility = View.GONE
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEditText() {
        binding.searchEditText.customOnFocusChangeListener(requireContext())
        binding.layoutMain.setOnTouchListener { _, _ ->
            requireActivity().hideKeyboard()
            binding.searchEditText.clearFocus()
            false
        }
    }


    private fun countEditTextMessage() {
        lifecycleScope.launchWhenStarted {
            viewModel.editTextMessageCountEvent.collectLatest {
                if (it != 0) {
                    binding.popularCategoryFrame.visibility = View.GONE
                    binding.makeRoomLayout.visibility = View.VISIBLE
                    binding.alarmRoomRecycler.visibility = View.VISIBLE
                }
                else{
                    binding.popularCategoryFrame.visibility = View.VISIBLE
                    binding.makeRoomLayout.visibility = View.GONE
                    binding.alarmRoomRecycler.visibility = View.GONE
                }
            }
        }
    }
}
