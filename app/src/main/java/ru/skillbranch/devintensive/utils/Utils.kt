package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        when (fullName) {
            "", " " -> {
                firstName = null; lastName = null
            }
        }
        return firstName to lastName
    }

    fun trasliteration(payload: String, divider: String = " "): String {
        val map: HashMap<String, String> = hashMapOf()
        map.put("а", "a"); map.put("А", "A")
        map.put("б", "b"); map.put("Б", "B")
        map.put("в", "v"); map.put("В", "V")
        map.put("г", "g"); map.put("Г", "G")
        map.put("д", "d"); map.put("Д", "D")
        map.put("е", "e"); map.put("Е", "E")
        map.put("ё", "e"); map.put("Ё", "E")
        map.put("ж", "zh"); map.put("Ж", "Zh")
        map.put("з", "z"); map.put("З", "Z")
        map.put("и", "i"); map.put("И", "I")
        map.put("й", "i"); map.put("Й", "I")
        map.put("к", "k"); map.put("К", "K")
        map.put("л", "l"); map.put("Л", "L")
        map.put("м", "m"); map.put("М", "M")
        map.put("н", "n"); map.put("Н", "N")
        map.put("о", "o"); map.put("О", "O")
        map.put("п", "p"); map.put("П", "P")
        map.put("р", "r"); map.put("Р", "R")
        map.put("с", "s"); map.put("С", "S")
        map.put("т", "t"); map.put("Т", "T")
        map.put("у", "u"); map.put("У", "U")
        map.put("ф", "f"); map.put("Ф", "F")
        map.put("х", "h"); map.put("Х", "H")
        map.put("ц", "c"); map.put("Ц", "C")
        map.put("ч", "ch"); map.put("Ч", "Ch")
        map.put("ш", "sh"); map.put("Ш", "Sh")
        map.put("щ", "sh'"); map.put("Щ", "Sh'")
        map.put("ъ", ""); map.put("Ъ", "")
        map.put("ы", "i"); map.put("Ы", "I")
        map.put("ь", ""); map.put("Ь", "")
        map.put("э", "e"); map.put("Э", "E")
        map.put("ю", "yu"); map.put("Ю", "Yu")
        map.put("я", "ya"); map.put("Я", "Ya")
        map.put(" ", " ")
        var outString: String? = ""
        payload.forEach { key ->
            val value: String? = when(key.toString()) {
                " " -> map.put(key.toString(), divider)
                else -> key.toString()
            }
            if (map.get(value) == null) map.put(key.toString(), key.toString())
            outString += map.get(value)
        }
        return "$outString"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val outFirstName: String? = when (firstName) {
            null -> null
            "" -> null
            else -> firstName.substring(0, 1).toUpperCase()
        }
        val outLastName: String? = when (lastName) {
            null -> null
            "" -> null
            else -> lastName.substring(0, 1).toUpperCase()
        }
        return "$outFirstName $outLastName"
    }
}