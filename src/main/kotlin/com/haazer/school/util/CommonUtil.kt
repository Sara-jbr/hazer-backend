package com.haazer.school.util

import ir.huri.jcal.JalaliCalendar
import jakarta.enterprise.context.ApplicationScoped
import java.time.LocalDate
import java.time.Month
import java.time.ZonedDateTime


@ApplicationScoped
object CommonUtil {

    fun gregorianToJalali(gregorianDate: ZonedDateTime): String {
        val jalaliDate = JalaliCalendar(gregorianDate.toLocalDate())
        return "${jalaliDate.year}/${jalaliDate.month}/${jalaliDate.day} " +
                "${String.format("%02d", gregorianDate.hour)}:" +
                "${String.format("%02d", gregorianDate.minute)}:" +
                "${String.format("%02d", gregorianDate.second)}"
    }
}