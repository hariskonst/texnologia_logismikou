package com.yourapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R
import com.yourapp.model.Tenant

class TenantAdapter(
    ctx: Context,
    private val items: MutableList<Tenant>,
    private val onDelete: (Tenant) -> Unit
) : ArrayAdapter<Tenant>(ctx, 0, items) {

    private val inflater = LayoutInflater.from(ctx)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.row_tenant, parent, false)
        val tenant = items[position]

        view.findViewById<TextView>(R.id.tvTenantName).text = tenant.tenantName
        view.findViewById<TextView>(R.id.tvTenantId).text   = "ID: ${tenant.tenantId}"
        view.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            onDelete(tenant)
        }

        return view
    }
}
