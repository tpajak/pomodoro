package org.hyperskill.stopwatch

class Time {
    private var seconds: Long
    private var timeLimit: Long? = null

    init {
        seconds = 0
    }

    fun getSeconds(): Long {
        return seconds
    }

    fun resetTime() {
        seconds = 0
    }

    fun countTime() {
        seconds += 1
    }

    fun getTimeLimit(): Long? {
        return timeLimit
    }

    fun resetTimeLimit() {
        timeLimit = null
    }

    fun setTimeLimit(limit: Long?) {
        if (limit != null) {
            timeLimit = limit + 1
        }
    }

    fun getTimeHoursMinsSecondsString(): String {
        val hours = seconds / 3600;
        val minutes = (seconds % 3600) / 60;
        val seconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    fun getTimeMinsSecondsString(): String {
        val minutes = (seconds % 3600) / 60;
        val seconds = seconds % 60;

        return String.format("%02d:%02d", minutes, seconds)
    }
}