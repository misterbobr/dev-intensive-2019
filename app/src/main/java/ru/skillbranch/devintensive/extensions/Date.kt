package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val DateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return DateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits) : Date{
    var time = this.time

    time += when(units)
    {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff() : String
{
    var timeValue:Long = (this.time - Date().time)/1000
    val one = arrayListOf<Long>()
    one.add(1.toLong())
    one.add(21.toLong())
    one.add(31.toLong())
    one.add(41.toLong())
    one.add(51.toLong())
    val twoFour = arrayListOf<LongRange>()
    twoFour.add(2.toLong()..4.toLong())
    twoFour.add(22.toLong()..24.toLong())
    twoFour.add(32.toLong()..34.toLong())
    twoFour.add(42.toLong()..44.toLong())
    twoFour.add(52.toLong()..54.toLong())
    val fiveNine = arrayListOf<LongRange>()
    fiveNine.add(0.toLong()..0.toLong())
    fiveNine.add(5.toLong()..20.toLong())
    fiveNine.add(25.toLong()..30.toLong())
    fiveNine.add(35.toLong()..40.toLong())
    fiveNine.add(45.toLong()..50.toLong())
    fiveNine.add(55.toLong()..59.toLong())

    var timeType:String = ""
    val timeValueModified:Long = when(abs(timeValue))
    {
        in 0.toLong()..59.toLong() -> abs(timeValue)
        in 60.toLong()..3599.toLong() -> abs(timeValue)/60
        in 3600.toLong()..(3600*24-1).toLong() -> abs(timeValue)/60/60
        in (3600*24).toLong()..(3600*24*365-1).toLong() -> abs(timeValue)/60/60/24
        else -> abs(timeValue)/60/60/24/365
    }
    if (timeValueModified in one) timeType = when(timeValue)
    {
        in 1.toLong()..59.toLong() -> "через ${++timeValue} секунду"
        in 60.toLong()..3599.toLong() -> "через ${(++timeValue/60)} минуту"
        in 3600.toLong()..(3600*24-1).toLong() -> "через ${(++timeValue/60/60)} час"
        in (3600*24).toLong()..(3600*24*365-1).toLong() -> "через ${(++timeValue/60/60/24)} день"
        in (3600*24*365).toLong()..Long.MAX_VALUE -> "более чем через ${(++timeValue/60/60/24/365)} год"
        in (-59).toLong()..(-1).toLong() -> "${-(timeValue)} секунду назад"
        in (-3599).toLong()..(-60).toLong() -> "${-(timeValue/60)} минуту назад"
        in (-3600*24+1).toLong()..(-3600).toLong() -> "${-(timeValue/60/60)} час назад"
        in (-3600*24*365+1).toLong()..(-3600*24).toLong() -> "${-(timeValue/60/60/24)} день назад"
        else -> "более ${-(timeValue/60/60/24/365)} года назад"
    }
    else if (timeValueModified in twoFour[0]||timeValueModified in twoFour[1]||timeValueModified in twoFour[2]||timeValueModified in twoFour[3]||timeValueModified in twoFour[4]) timeType = when(timeValue)
    {
        in 1.toLong()..59.toLong() -> "через ${++timeValue} секунды"
        in 60.toLong()..3599.toLong() -> "через ${(++timeValue/60)} минуты"
        in 3600.toLong()..(3600*24-1).toLong() -> "через ${(++timeValue/60/60)} часа"
        in (3600*24).toLong()..(3600*24*365-1).toLong() -> "через ${(++timeValue/60/60/24)} дня"
        in (3600*24*365).toLong()..Long.MAX_VALUE -> "более чем через ${(++timeValue/60/60/24/365)} года"
        in (-59).toLong()..(-1).toLong() -> "${-(timeValue)} секунды назад"
        in (-3599).toLong()..(-60).toLong() -> "${-(timeValue/60)} минуты назад"
        in (-3600*24+1).toLong()..(-3600).toLong() -> "${-(timeValue/60/60)} часа назад"
        in (-3600*24*365+1).toLong()..(-3600*24).toLong() -> "${-(timeValue/60/60/24)} дня назад"
        else -> "более ${-(timeValue/60/60/24/365)} года назад"
    }
    else if (timeValueModified in fiveNine[0]||timeValueModified in fiveNine[1]||timeValueModified in fiveNine[2]||timeValueModified in fiveNine[3]||timeValueModified in fiveNine[4]||timeValueModified in fiveNine[5]) timeType = when(timeValue)
    {
        in 0.toLong()..0.toLong() -> "только что"
        in 1.toLong()..59.toLong() -> "через ${++timeValue} секунд"
        in 60.toLong()..3599.toLong() -> "через ${(++timeValue/60)} минут"
        in 3600.toLong()..(3600*24-1).toLong() -> "через ${(++timeValue/60/60)} часов"
        in (3600*24).toLong()..(3600*24*365-1).toLong() -> "через ${(++timeValue/60/60/24)} дней"
        in (3600*24*365).toLong()..Long.MAX_VALUE -> "более чем через ${(++timeValue/60/60/24/365)} лет"
        in (-59).toLong()..(-1).toLong() -> "${-(timeValue)} секунд назад"
        in (-3599).toLong()..(-60).toLong() -> "${-(timeValue/60)} минут назад"
        in (-3600*24+1).toLong()..(-3600).toLong() -> "${-(timeValue/60/60)} часов назад"
        in (-3600*24*365+1).toLong()..(-3600*24).toLong() -> "${-(timeValue/60/60/24)} дней назад"
        else -> "более ${-(timeValue/60/60/24/365)} лет назад"
    }
    println("$timeValue")
    return "$timeType"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}