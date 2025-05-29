package com.yourapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R
import com.yourapp.model.Maintenance

class MaintenanceAdapter(
    ctx: Context,
    private val items: MutableList<Maintenance>,
    private val onDelete: (Maintenance) -> Unit
) : ArrayAdapter<Maintenance>(ctx, 0, items) {

    private val inflater = LayoutInflater.from(ctx)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.row_maintenance, parent, false)
        val m = items[position]

        view.findViewById<TextView>(R.id.tvMaintDesc).text = m.description
        view.findViewById<Button>(R.id.btnMaintAction).apply {
            text = "Delete"
            setOnClickListener { onDelete(m) }
        }

        return view
    }
}
