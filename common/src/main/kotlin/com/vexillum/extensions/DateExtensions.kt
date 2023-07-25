package com.vexillum.extensions

import java.time.ZoneId
import java.time.ZoneId.systemDefault
import java.util.Date

fun Date.toLocalDateTime(zone: ZoneId = systemDefault()) =
    this.toInstant()
        .atZone(zone)
        .toLocalDateTime()

fun Date.toLocalDate(zone: ZoneId = systemDefault()) =
    this.toInstant()
        .atZone(zone)
        .toLocalDate()