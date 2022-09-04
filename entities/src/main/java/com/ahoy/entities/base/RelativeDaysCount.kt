package com.ahoy.entities.base

sealed class RelativeDaysCount {
    override fun equals(other: Any?): Boolean {
        return if (other is DaysCount && this is DaysCount) true
        else if (other is Zero && this is Zero && other.numOfDays == this.numOfDays) true
        else if (other is OneDays && this is OneDays && other.numOfDays == this.numOfDays) true
        else if (other is TwoDays && this is TwoDays && other.numOfDays == this.numOfDays) true
        else other is MoreThanThreeDays && this is MoreThanThreeDays && other.numOfDays == this.numOfDays
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

object DaysCount : RelativeDaysCount()

class Zero(val numOfDays: Int) : RelativeDaysCount()

class OneDays(val numOfDays: Int) : RelativeDaysCount()

class TwoDays(val numOfDays: Int) : RelativeDaysCount()

class MoreThanThreeDays(val numOfDays: Int) : RelativeDaysCount()