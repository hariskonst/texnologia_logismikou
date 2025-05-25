package com.example.myapplication

import android.content.Context

object Receipt {

    private const val PREF_NAME = "Receipt_prefs"
    private const val KEY_LIST = "Receipt_list"

    // Αποθήκευση λίστας ως Set
    fun saveList(context: Context, list: List<String>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putStringSet(KEY_LIST, list.toSet()).apply()
    }

    // Φόρτωση λίστας από SharedPreferences
    fun loadList(context: Context): MutableList<String> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedSet = prefs.getStringSet(KEY_LIST, emptySet())
        return savedSet?.toMutableList() ?: mutableListOf()
    }
}