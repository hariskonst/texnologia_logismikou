package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.DataManager

class TaskAdapter(
    tasks: List<String>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val tasks = tasks.toMutableList()

    inner class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tv: TextView = view.findViewById(R.id.tvTask)
        private val ivFavorite: ImageView = view.findViewById(R.id.ivFavorite)

        fun bind(task: String) {
            tv.text = task

            // Εμφάνιση καρδούλας γεμάτης ή άδειας
            val isFav = DataManager.getFavorites().contains(task)
            ivFavorite.setImageResource(
                if (isFav) R.drawable.ic_favorite_24
                else R.drawable.ic_favorite_border_24
            )

            // Click στο task → εμφάνιση λεπτομερειών
            view.setOnClickListener { clickListener(task) }

            // Click στην καρδούλα → προσθήκη/αφαίρεση από αγαπημένα
            ivFavorite.setOnClickListener {
                if (isFav) {
                    DataManager.removeFavorite(task)
                } else {
                    DataManager.addFavorite(task)
                }
                notifyItemChanged(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    fun updateData(newTasks: List<String>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
}
