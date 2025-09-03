package com.klickr.logic

/**
 * Handles trade automation using mapped keys.
 */
class TradeAutomationEngine(
    private val keyMapper: ButtonKeyMapper
) {
    /**
     * Executes a signal using mapped keys.
     * signal: e.g., "BUY TF1", "SELL TF5", "INCAMT", etc.
     * Returns list of region actions to perform in correct order (including triggers).
     */
    fun processSignal(signal: String): List<String> {
        val actions = mutableListOf<String>()

        // Example: parse the signal and map to keys, handling triggers
        val parts = signal.split(" ")
        for (i in parts.indices) {
            val part = parts[i]
            val key = when (part.uppercase()) {
                "TRIGAMT" -> TradeButtonKey.TrigAmt
                "INCAMT" -> TradeButtonKey.IncAmt
                "DECAMT" -> TradeButtonKey.DecAmt
                "BUY" -> TradeButtonKey.Buy
                "SELL" -> TradeButtonKey.Sell
                "TRIGTF" -> TradeButtonKey.TrigTF
                "TF1" -> TradeButtonKey.TF1
                "TF5" -> TradeButtonKey.TF5
                "ASSETTRIGGER" -> TradeButtonKey.AssetTrigger
                "SEARCHTRIGGER" -> TradeButtonKey.SearchTrigger
                "PASTE" -> TradeButtonKey.Paste
                "CONFIRMTRADE" -> TradeButtonKey.ConfirmTrade
                else -> null
            }
            key?.let {
                // For TF1/TF5, insert TrigTF before if not already present
                if (it == TradeButtonKey.TF1 || it == TradeButtonKey.TF5) {
                    keyMapper.getRegionForKey(TradeButtonKey.TrigTF)?.let { trigTfRegionId ->
                        if (!actions.contains(trigTfRegionId)) {
                            actions.add(trigTfRegionId)
                        }
                    }
                }
                keyMapper.getRegionForKey(it)?.let { regionId ->
                    actions.add(regionId)
                }
            }
        }
        return actions
    }
}
