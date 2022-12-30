package com.depromeet.knockknock.ui.home.bottom.select

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.depromeet.knockknock.databinding.DialogBottomHomeSelectRoomBinding
import com.depromeet.knockknock.ui.home.bottom.AlarmMoreType
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BottomHomeSelectRoom(
    val callback: (selectRoom: Int) -> Unit
) : BottomSheetDialogFragment(){

    private lateinit var dlg : BottomSheetDialog

    private val viewModel by viewModels<BottomHomeSelectViewModel>()
    private lateinit var binding: DialogBottomHomeSelectRoomBinding
    private val adapter by lazy { BottomRoomSelectAdapter(viewModel) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면 XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.isDraggable = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        } ) as BottomSheetDialog
        return dlg
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogBottomHomeSelectRoomBinding.inflate(inflater, container, false).apply {
            viewmodel = this@BottomHomeSelectRoom.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // -1: 알림방 탐색, -2: 알림방 생성하기
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.selectRoom.collectLatest {
                callback.invoke(it)
                dismiss()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.roomList.collectLatest {
                adapter.submitData(it)
                viewModel.isEmptyList.value = adapter.snapshot().items.isEmpty()
            }
        }
    }
}
