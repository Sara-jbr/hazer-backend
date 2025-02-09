package com.haazer.school.util

import jakarta.enterprise.context.ApplicationScoped
import java.time.LocalDate
import java.time.ZonedDateTime


@ApplicationScoped
object CommonUtil {

    fun gregorianToJalali(gregorianDate: ZonedDateTime): String {
        val gDate = LocalDate.of(gregorianDate.year, gregorianDate.monthValue, gregorianDate.dayOfMonth)
        val baseJalaliYear = 1348
        val diffYears = gDate.year - 1969
        val jalaliYear = baseJalaliYear + diffYears

        return "$jalaliYear/${gregorianDate.monthValue}/${gregorianDate.dayOfMonth} ${gregorianDate.hour}:${gregorianDate.minute}:${gregorianDate.second}"
    }


}