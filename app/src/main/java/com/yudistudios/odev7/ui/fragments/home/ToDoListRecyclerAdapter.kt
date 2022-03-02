package com.yudistudios.odev7.ui.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yudistudios.odev7.database.entities.ToDoListItem
import com.yudistudios.odev7.databinding.ItemTodoBinding

class ToDoListRecyclerAdapter(
    private val mContext: Context,
    private val toDoListRecyclerItemClickListeners: ToDoListRecyclerItemClickListeners
) : ListAdapter<ToDoListItem, ToDoListRecyclerAdapter.ViewHolder>(DiffCallback) {
    class ViewHolder(
        private val binding: ItemTodoBinding,
        private val toDoListRecyclerItemClickListeners: ToDoListRecyclerItemClickListeners
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ToDoListItem) {
            binding.model = item

            binding.deleteButton.setOnClickListener {
               toDoListRecyclerItemClickListeners.deleteButtonOnClick(item)
            }

            binding.cardView.setOnClickListener {
                toDoListRecyclerItemClickListeners.cardViewClickOnClick(item)
            }
        }

        companion object {
            fun from(
                context: Context,
                parent: ViewGroup,
                toDoListRecyclerItemClickListeners: ToDoListRecyclerItemClickListeners
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(context)
                val binding = ItemTodoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, toDoListRecyclerItemClickListeners)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(mContext, parent, toDoListRecyclerItemClickListeners)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    private object DiffCallback : DiffUtil.ItemCallback<ToDoListItem>() {
        override fun areItemsTheSame(oldItem: ToDoListItem, newItem: ToDoListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoListItem, newItem: ToDoListItem): Boolean {
            return oldItem == newItem
        }
    }
}

class ToDoListRecyclerItemClickListeners(
    val deleteButtonOnClick: (ToDoListItem) -> Unit,
    val cardViewClickOnClick: (ToDoListItem) -> Unit
)