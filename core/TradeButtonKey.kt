package com.klickr.logic

enum class TradeButtonKey(val displayName: String) {
    TrigAmt("Trigger Amount"),
    TrigTF("Trigger Timeframe"),
    IncAmt("Increase Amount"),
    DecAmt("Decrease Amount"),
    Buy("Buy"),
    Sell("Sell"),
    TF1("Timeframe 1m"),
    TF5("Timeframe 5m"),
    AssetTrigger("Asset Trigger"),
    SearchTrigger("Search Trigger"),
    Paste("Paste"),
    ConfirmTrade("Confirm Trade");

    companion object {
        fun fromDisplayName(name: String): TradeButtonKey? =
            values().find { it.displayName == name }
    }
}
