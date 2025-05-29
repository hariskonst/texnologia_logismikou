package com.yourapp.manager

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yourapp.model.Maintenance

object ManageMaint {
    private const val PREFS_NAME = "maint_prefs"
    private const val KEY_MAINTS  = "key_maints"

    private fun prefs(ctx: Context) =
        ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    /* Φορτώνει όλη τη λίστα */
    fun queryMaintenance(ctx: Context): MutableList<Maintenance> {
        val json = prefs(ctx).getString(KEY_MAINTS, null)
        return if (json.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(json, object: TypeToken<MutableList<Maintenance>>(){}.type)
        }
    }

    /* Αποθηκεύει (overwrite) τη λίστα */
    private fun save(ctx: Context, list: List<Maintenance>) {
        prefs(ctx).edit()
            .putString(KEY_MAINTS, gson.toJson(list))
            .apply()
    }

    /* Προσθέτει νέο maintenance */
    fun addMaintenance(ctx: Context, m: Maintenance) {
        val list = queryMaintenance(ctx)
        list.add(m)
        save(ctx, list)
    }

    /* Διαγράφει ένα maintenance */
    fun deleteMaintenance(ctx: Context, m: Maintenance) {
        val list = queryMaintenance(ctx)
        list.removeAll { it.id == m.id }
        save(ctx, list)
    }

    /*  default data στην πρώτη εκτέλεση */
    fun seedIfNeeded(ctx: Context) {
        val list = queryMaintenance(ctx)
        if (list.isEmpty()) {
            val defaults = mutableListOf(
                Maintenance(1, "Check boiler pressure"),
                Maintenance(2, "Clean air filters"),
                Maintenance(3, "Inspect fire alarms")
            )
            save(ctx, defaults)
        }
    }
}
