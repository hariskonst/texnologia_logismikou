package com.example.myapplication

import android.content.Context

object PaymentStorage {
    private const val PREF_NAME = "payment_prefs"
    private const val KEY_LIST = "payment_list"

    fun saveList(context: Context, list: List<String>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putStringSet(KEY_LIST, list.toSet()).apply()
    }

    fun loadList(context: Context): MutableList<String> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedSet = prefs.getStringSet(KEY_LIST, setOf(
            "1 April 2025 - Common Charges - 100$ - Pending",
            "1 March 2025 - Common Charges - 100$ - Pending",
            "1 February 2025 - Common Charges - 100$ - Pending",
            "1 January 2025 - Common Charges - 45$ - Done",
            "1 December 2024 - Common Charges - 65$ - Done",
            "1 November 2024 - Common Charges - 120$ - Done"
        ))
        return savedSet?.toMutableList() ?: mutableListOf()
    }
}