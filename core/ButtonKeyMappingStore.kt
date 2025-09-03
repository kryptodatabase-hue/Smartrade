package com.klickr.logic

import android.content.Context
import org.json.JSONObject

/**
 * Persists key-region mappings to local storage (as JSON).
 */
class ButtonKeyMappingStore(private val context: Context) {
    private val PREFS_NAME = "button_key_mappings"
    private val KEY_MAPPINGS = "mappings"

    // Save mappings to SharedPreferences
    fun saveMappings(mappings: Map<TradeButtonKey, String>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = JSONObject()
        for ((key, region) in mappings) {
            json.put(key.name, region)
        }
        prefs.edit().putString(KEY_MAPPINGS, json.toString()).apply()
    }

    // Load mappings from SharedPreferences
    fun loadMappings(): Map<TradeButtonKey, String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val mappings = mutableMapOf<TradeButtonKey, String>()
        val jsonStr = prefs.getString(KEY_MAPPINGS, null)
        if (jsonStr != null) {
            val json = JSONObject(jsonStr)
            for (key in TradeButtonKey.values()) {
                if (json.has(key.name)) {
                    mappings[key] = json.getString(key.name)
                }
            }
        }
        return mappings
    }

    // Clear all mappings
    fun clearMappings() {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_MAPPINGS).apply()
    }
}
