package com.klickr.logic

/**
 * Maps trading keys to UI regions (identified by region IDs or coordinates).
 */
class ButtonKeyMapper {
    private val keyToRegion: MutableMap<TradeButtonKey, String> = mutableMapOf()

    // Assign a key to a region (regionId could be a coordinate, view ID, etc.)
    fun assignKeyToRegion(key: TradeButtonKey, regionId: String) {
        keyToRegion[key] = regionId
    }

    // Remove mapping
    fun removeKeyMapping(key: TradeButtonKey) {
        keyToRegion.remove(key)
    }

    // Get region by key
    fun getRegionForKey(key: TradeButtonKey): String? =
        keyToRegion[key]

    // Get all mappings
    fun getAllMappings(): Map<TradeButtonKey, String> =
        keyToRegion.toMap()

    // Set all mappings at once
    fun setAllMappings(mappings: Map<TradeButtonKey, String>) {
        keyToRegion.clear()
        keyToRegion.putAll(mappings)
    }
}
