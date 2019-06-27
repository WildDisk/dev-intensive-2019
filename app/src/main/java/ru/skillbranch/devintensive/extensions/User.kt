package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

fun User.toUserView(): UserView {
    val nickName = Utils.trasliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if (lastVisit == null) "Ещё ни разу не был" else if (isOnline) "online" else "Последий раз был ${lastVisit.humanizeDiff()}"

        return UserView(
            id,
            fullName = "$firstName $lastName",
            avatar = avatar,
            nickname = nickName,
            initials = initials,
            status = status)
}


