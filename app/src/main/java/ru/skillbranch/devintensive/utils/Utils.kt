package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = when (parts?.getOrNull(0)) {
            "", " " -> null
            else -> parts?.getOrNull(0)
        }

        val partsSize = when(parts?.size) {
            1, 2 -> 1
            else -> parts?.lastIndex
        }

        val lastName = when (partsSize?.let { parts?.getOrNull(it) }) {
            "", " " -> null
            else -> partsSize?.let { parts?.getOrNull(it) }
        }
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val map: HashMap<String, String> = hashMapOf()
        map["а"] = "a"; map["А"] = "A"
        map["б"] = "b"; map["Б"] = "B"
        map["в"] = "v"; map["В"] = "V"
        map["г"] = "g"; map["Г"] = "G"
        map["д"] = "d"; map["Д"] = "D"
        map["е"] = "e"; map["Е"] = "E"
        map["ё"] = "e"; map["Ё"] = "E"
        map["ж"] = "zh"; map["Ж"] = "Zh"
        map["з"] = "z"; map["З"] = "Z"
        map["и"] = "i"; map["И"] = "I"
        map["й"] = "i"; map["Й"] = "I"
        map["к"] = "k"; map["К"] = "K"
        map["л"] = "l"; map["Л"] = "L"
        map["м"] = "m"; map["М"] = "M"
        map["н"] = "n"; map["Н"] = "N"
        map["о"] = "o"; map["О"] = "O"
        map["п"] = "p"; map["П"] = "P"
        map["р"] = "r"; map["Р"] = "R"
        map["с"] = "s"; map["С"] = "S"
        map["т"] = "t"; map["Т"] = "T"
        map["у"] = "u"; map["У"] = "U"
        map["ф"] = "f"; map["Ф"] = "F"
        map["х"] = "h"; map["Х"] = "H"
        map["ц"] = "c"; map["Ц"] = "C"
        map["ч"] = "ch"; map["Ч"] = "Ch"
        map["ш"] = "sh"; map["Ш"] = "Sh"
        map["щ"] = "sh'"; map["Щ"] = "Sh'"
        map["ъ"] = ""; map["Ъ"] = ""
        map["ы"] = "i"; map["Ы"] = "I"
        map["ь"] = ""; map["Ь"] = ""
        map["э"] = "e"; map["Э"] = "E"
        map["ю"] = "yu"; map["Ю"] = "Yu"
        map["я"] = "ya"; map["Я"] = "Ya"
        var outString = ""
        payload.forEach { key ->
            val value: String? = when (key.toString()) {
                !in map.keys.toString() -> key.toString()
                "[", "]" -> key.toString()
                " " -> divider
                else -> map[key.toString()]
            }
            outString += value
        }
        return outString
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val outFirstName: String? = when (firstName) {
            null, "", " " -> ""
            else -> firstName.substring(0, 1).toUpperCase()
        }
        val outLastName: String? = when (lastName) {
            null, "", " " -> ""
            else -> lastName.substring(0, 1).toUpperCase()
        }
        return if (outFirstName == "" && outLastName == "") null
        else "$outFirstName$outLastName"
    }
}