package com.utek.android.utekapp.homeguest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.utek.android.utekapp.databinding.AppmemberViewItemBinding
import com.utek.android.utekapp.network.AppMember

class MemberItemAdapter(val onClickListener: OnClickListener) :
    ListAdapter<AppMember, MemberItemAdapter.AppMemberViewHolder>(DiffCallback) {
    class AppMemberViewHolder(private var binding: AppmemberViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(appMember: AppMember) {
            binding.appMember = appMember
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AppMember>() {
        override fun areItemsTheSame(oldItem: AppMember, newItem: AppMember): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AppMember, newItem: AppMember): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppMemberViewHolder {
        return AppMemberViewHolder(AppmemberViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AppMemberViewHolder, position: Int) {
        val appMember = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(appMember)
        }

        holder.bind(appMember)
    }

    class OnClickListener(val clickListener: (appMember: AppMember) -> Unit) {
        fun onClick(appMember: AppMember) = clickListener(appMember)
    }

}