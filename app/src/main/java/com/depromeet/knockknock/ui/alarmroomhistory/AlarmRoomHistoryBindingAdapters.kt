package com.depromeet.knockknock.ui.alarmroomhistory

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.depromeet.domain.model.Admission
import com.depromeet.knockknock.ui.alarmroomhistory.adapter.AlarmInviteRoomAdapter


@BindingAdapter("alarmInviteRoomBundleAdapter")
fun RecyclerView.bindAlarmInviteRoom(itemList: List<Admission>) {
    val boundAdapter = this.adapter
    if (boundAdapter is AlarmInviteRoomAdapter) {
        boundAdapter.submitList(itemList)
    }
}

@BindingAdapter("bindViewVisible")
fun ConstraintLayout.bindViewVisible(data : String) {
    if (data == "") this.visibility = View.GONE
    else this.visibility = View.VISIBLE
}


@BindingAdapter("bindViewVisible")
fun ConstraintLayout.bindViewVisible(data : List<Admission>) {
    if (data.isEmpty()) this.visibility = View.GONE
    else this.visibility = View.VISIBLE
}

@BindingAdapter("bindViewVisible")
fun ImageView.bindViewVisible(isPublicAccess : Boolean) {
    if (isPublicAccess) this.visibility = View.GONE
    else this.visibility = View.VISIBLE
}

@BindingAdapter("isMessage", "isPublicAccess")
fun ImageView.bindViewMessageVisible(isMessage : Boolean, isPublicAccess : Boolean) {
    if (isPublicAccess){
        if (isMessage) this.visibility = View.GONE
        else this.visibility = View.VISIBLE
    }
}

@BindingAdapter("isPublicAccess", "participation")
fun ConstraintLayout.bindViewVisible(isPublicAccess : Boolean, participation : Boolean) {
    if (!participation){
        if (isPublicAccess) this.visibility = View.GONE
        else this.visibility = View.VISIBLE
    }
}