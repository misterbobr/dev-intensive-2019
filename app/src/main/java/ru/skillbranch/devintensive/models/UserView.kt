package ru.skillbranch.devintensive.models

import java.util.*

data class UserView(val id : String,
                    var firstName : String?,
                    var lastName : String?,
                    var avatar : String?,
                    var rating : Int = 0,
                    var respect : Int = 0,
                    var lastVisit : Date? = Date(),
                    var isOnline : Boolean = false) {
}