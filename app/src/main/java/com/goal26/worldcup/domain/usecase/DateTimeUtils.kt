package com.goal26.worldcup.domain.usecase

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateTimeUtils {

    private val utcFormatter = DateTimeFormatter.ISO_INSTANT
    private val displayDateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d", Locale.ENGLISH)
    private val displayTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
    private val fullFormatter = DateTimeFormatter.ofPattern("EEE, MMM d • HH:mm z", Locale.ENGLISH)

    fun parseUtc(utcString: String): Instant {
        return try {
            Instant.parse(utcString)
        } catch (e: Exception) {
            try {
                // Handle formats like "2026-06-11T20:00:00Z"
                Instant.parse(utcString.replace(" ", "T").let {
                    if (!it.endsWith("Z") && !it.contains("+")) "${it}Z" else it
                })
            } catch (e2: Exception) {
                Instant.now()
            }
        }
    }

    fun formatLocalDate(utcString: String, zoneId: ZoneId = ZoneId.systemDefault()): String {
        val instant = parseUtc(utcString)
        val local = ZonedDateTime.ofInstant(instant, zoneId)
        return local.format(displayDateFormatter)
    }

    fun formatLocalTime(utcString: String, zoneId: ZoneId = ZoneId.systemDefault()): String {
        val instant = parseUtc(utcString)
        val local = ZonedDateTime.ofInstant(instant, zoneId)
        return local.format(displayTimeFormatter)
    }

    fun formatFull(utcString: String, zoneId: ZoneId = ZoneId.systemDefault()): String {
        val instant = parseUtc(utcString)
        val local = ZonedDateTime.ofInstant(instant, zoneId)
        return local.format(fullFormatter)
    }

    fun getCountdown(utcString: String): CountdownData {
        val instant = parseUtc(utcString)
        val now = Instant.now()
        val duration = Duration.between(now, instant)

        if (duration.isNegative) {
            return CountdownData(0, 0, 0, 0, isExpired = true)
        }

        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60

        return CountdownData(
            days = days.toInt(),
            hours = hours.toInt(),
            minutes = minutes.toInt(),
            seconds = seconds.toInt(),
            isExpired = false
        )
    }

    fun getRelativeTime(utcString: String): String {
        val countdown = getCountdown(utcString)
        return when {
            countdown.isExpired -> "Started"
            countdown.days > 0 -> "In ${countdown.days}d ${countdown.hours}h"
            countdown.hours > 0 -> "In ${countdown.hours}h ${countdown.minutes}m"
            countdown.minutes > 0 -> "In ${countdown.minutes}m"
            else -> "Starting soon"
        }
    }
}

data class CountdownData(
    val days: Int,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
    val isExpired: Boolean = false
)
