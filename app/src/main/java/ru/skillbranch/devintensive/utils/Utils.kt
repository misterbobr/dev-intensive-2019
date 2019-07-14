package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName:String?): Pair<String?, String?> {
        val list: List<String>? = fullName?.split(' ')

        var firstName = list?.getOrNull(0)
        var lastName = list?.getOrNull(1)
        if (firstName == "") firstName = null
        if (lastName == "") lastName = null
        return firstName to lastName
    }
    fun toInitials(firstName:String?, lastName: String?): String?
    {
        var initials:String? = null
        if (!(firstName==null||firstName?.replace("\\s".toRegex(), "")==""))
            initials = "${firstName?.replace("\\s".toRegex(), "")?.firstOrNull()?.toUpperCase()}"
        else
            initials = ""
        if (!(lastName==null||lastName?.replace("\\s".toRegex(), "")==""))
            initials += "${lastName?.replace("\\s".toRegex(), "").firstOrNull()?.toUpperCase()}"
        if (initials?.replace("\\s".toRegex(), "")=="")
            return null
        else
        return "$initials"
    }
}