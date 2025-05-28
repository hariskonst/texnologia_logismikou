package com.yourapp.manager

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yourapp.model.Tenant


object AddTntManage {
    private const val PREFS_NAME = "tenant_prefs"
    private const val KEY_TENANTS = "key_tenants"

    private fun prefs(ctx: Context) =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()

    /** Φορτώνει τη λίστα από SharedPreferences */
    fun queryTenant(ctx: Context): MutableList<Tenant> {
        val json = prefs(ctx).getString(KEY_TENANTS, null)
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(json, object: TypeToken<MutableList<Tenant>>(){}.type)
        }
    }

    /** Αποθηκεύει (overwrite) τη λίστα */
    private fun save(ctx: Context, list: List<Tenant>) {
        prefs(ctx).edit()
            .putString(KEY_TENANTS, gson.toJson(list))
            .apply()
    }

    /** Διαγράφει έναν tenant */
    fun deleteTenant(ctx: Context, t: Tenant) {
        val list = queryTenant(ctx)
        list.removeAll { it.tenantId == t.tenantId }
        save(ctx, list)
    }

    /** Προσθέτει έναν tenant */
    fun addTenant(ctx: Context, t: Tenant) {
        val list = queryTenant(ctx)
        list.add(t)
        save(ctx, list)
    }

}
