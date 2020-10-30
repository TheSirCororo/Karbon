package com.karbonpowered.commons.ticking

class TPSMonitor {
    private var lastUpdateTime: Long = 0
    private var elapsedTime: Long = 0
    private var frameCount = 0

    /**
     * Returns the TPS.
     *
     * @return The TPS
     */
    var tps = 0
        private set

    /**
     * Starts the TPS monitor.
     */
    fun start() {
        lastUpdateTime = System.currentTimeMillis()
    }

    /**
     * Updates the TPS.
     */
    fun update() {
        val time = System.currentTimeMillis()
        elapsedTime += time - lastUpdateTime
        lastUpdateTime = time
        frameCount++
        if (elapsedTime >= 1000) {
            tps = frameCount
            frameCount = 0
            elapsedTime = 0
        }
    }
}