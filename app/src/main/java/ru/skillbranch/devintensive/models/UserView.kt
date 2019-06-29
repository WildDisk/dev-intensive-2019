package ru.skillbranch.devintensive.models

class UserView(
    val id: String,
    val fullName: String,
    val nickname: String,
    var avatar: String? = null,
    var status: String? = "offline",
    val initials: String?
) {
    fun printMe() {
        println("""
            id: $id: 
            fullName: $fullName: 
            avatar: $avatar: 
            nickname: $nickname: 
            status: $status: 
            initials: $initials: 
        """.trimIndent()
        )
    }
}