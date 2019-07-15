package ru.skillbranch.devintensive

import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2")
        val user3 = User("3")
        println("$user\n$user2\n$user3")
    }

    @Test
    fun test_factory() {
        val user = User.makeUser("John Cena")
        val user2 = User.makeUser("John Wick")
        val user3 = User.makeUser("John Silverhand")
        val user4 = User.makeUser(" ")
        val user5 = User.makeUser(null)
        val user6 = User.makeUser("John ")
        println("" +
                "${user.id} ${user.firstName} ${user.lastName} \n" +
                "${user2.id} ${user2.firstName} ${user2.lastName} \n" +
                "${user3.id} ${user3.firstName} ${user3.lastName} \n" +
                "${user4.id} ${user4.firstName} ${user4.lastName} \n" +
                "${user5.id} ${user5.firstName} ${user5.lastName} \n" +
                "${user6.id} ${user6.firstName} ${user6.lastName}")
    }

    @Test
    fun test_dataMapping() {
        val user = User.makeUser("Елена Космова").apply { lastVisit = Date().add(-4, TimeUnits.DAY) }
        println("$user")
        user.toUserView().apply { printMe() }
    }

    /**
     * Реализуй метод Utils.parseFullName(fullName) принимающий в качестве аргумента полное имя пользователя
     * (null, пустую строку) и возвращающий пару значений Pair(firstName, lastName) при невозможности распарсить
     * полное имя или его часть вернуть null null/"firstName" null
     * Пример:
     *     Utils.parseFullName(null) //null null
     *     Utils.parseFullName("") //null null
     *     Utils.parseFullName(" ") //null null
     *     Utils.parseFullName("John") //John null
     */
    @Test
    fun test_fullNameParsing() {
        assertEquals(null to null, Utils.parseFullName(null))
        assertEquals(null to null, Utils.parseFullName(""))
        assertEquals(null to null, Utils.parseFullName(" "))
        assertEquals("John" to null, Utils.parseFullName("John "))
        assertEquals(null to "Wick", Utils.parseFullName(" Wick"))
        assertEquals("John" to null, Utils.parseFullName("John"))
        assertEquals("John" to "Wick", Utils.parseFullName("John Wick"))
        assertEquals("John" to "Wick", Utils.parseFullName("John  Wick"))
    }

    /**
     *  Реализуй extension Date.add(value, TimeUnits) добавляющий или вычитающий значение переданное первым аргументом
     *  в единицах измерения второго аргумента (enum TimeUnits [SECOND, MINUTE, HOUR, DAY]) и возвращающий
     *  модифицированный экземпляр Date
     *  Пример:
     *      Date().add(2, TimeUnits.SECOND) //Thu Jun 27 14:00:02 GST 2019
     *      Date().add(-4, TimeUnits.DAY) //Thu Jun 23 14:00:00 GST 2019
     */
//    @Test
//    fun test_dateExtensionAdd() {
//        val pattern = "HH:mm:ss dd.MM.yy"
//        val dateString = "14:00:02 27.06.19"
//        assertEquals("14:00:00 27.06.19", dateString.toDate(pattern)?.add(-2, TimeUnits.SECOND)?.format(pattern))
//        assertEquals("16:00:02 27.06.19", dateString.toDate(pattern)?.add(2, TimeUnits.HOUR)?.format(pattern))
//        assertEquals("14:00:02 23.06.19", dateString.toDate(pattern)?.add(-4, TimeUnits.DAY)?.format(pattern))
//    }

    /**
     *  Необходимо реализовать утилитный метод transliteration(payload divider) принимающий в качестве аргумента строку
     *  и возвращающий преобразованную строку из латинских символов
     *  Пример:
     *      Utils.transliteration("Иван Стереотипов") //Ivan Stereotipov
     *      Utils.transliteration("Amazing Петр","_") //Amazing_Petr
     */
    @Test
    fun test_transliteration() {
        assertEquals("Ivan Stereotipov", Utils.transliteration("Иван Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр", "_"))
        assertEquals("Privet mir", Utils.transliteration("Привет мир"))
        assertEquals("    Privet    mir   ", Utils.transliteration("    Привет    мир   "))
        assertEquals("pRIvet mir", Utils.transliteration("pRIвет мир"))
        assertEquals("PRIvet Mir", Utils.transliteration("PRIвет Mир"))
        assertEquals("PRIvet1345 Mir", Utils.transliteration("PRIвет1345 Mир"))
        assertEquals("[]{}PRIvet Mir/", Utils.transliteration("[]{}PRIвет Mир/"))
        assertEquals("[]{}PRIvet____Mir/", Utils.transliteration("[]{}PRIвет    Mир/", "_"))
        assertEquals("[_444__444__444__444_]{}PRIvet_444__444_Mir/", Utils.transliteration("[    ]{}PRIвет  Mир/", "_444_"))
    }

    /**
     * Реализуй метод Utils.toInitials(firstName lastName) принимающий в качестве аргументов имя и
     * фамилию пользователя (null, пустую строку) и возвращающий строку с первыми буквами имени и фамилии
     * в верхнем регистре (если один из аргументов null то вернуть один инициал, если оба аргумента null вернуть null)
     * Пример:
     *      Utils.toInitials("john" ,"doe") //JD
     *      Utils.toInitials("John", null) //J
     *      Utils.toInitials(null, null) //null
     *      Utils.toInitials(" ", "") //null
     */
    @Test
    fun test_toInitials() {
        assertEquals("JD", Utils.toInitials("john", "doe"))
        assertEquals("J", Utils.toInitials("John", null))
        assertEquals(null, Utils.toInitials(null, null))
        assertEquals(null, Utils.toInitials(" ", ""))
    }

    /**
     * Реализуй паттерн Builder для класса User.
     * User.Builder()
     *      .id(s)
     *      .firstName(s)
     *      .lastName(s)
     *      .avatar(s)
     *      .rating(n)
     *      .respect(n)
     *      .lastVisit(d)
     *      .isOnline(b)
     *      .build()
     *  должен вернуть объект User
     */
    @Test
    fun test_builder() {
        val user = User.Builder()
                .id("123")
                .firstName("Vasya")
                .lastName("Пупкин")
                .avatar(null)
                .rating(1)
                .respect(1)
                .lastVisit(null)
                .isOnline(false)
                .build()
        println(user)
        val user2 = User.Builder()
                .firstName("Vasya")
                .lastName("Пупкин")
                .avatar(null)
                .rating(1)
                .respect(1)
                .lastVisit(null)
                .isOnline(false)
                .build()
        println(user2)
        val user3 = User.Builder()
                .firstName("Vasya")
                .lastName("Пупкин")
                .avatar(null)
                .rating(1)
                .respect(1)
                .lastVisit(null)
                .isOnline(false)
                .build()
        println(user3)
        assertThat(user, instanceOf(User::class.java))
    }

    /**
     * Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени) для форматирования
     * вывода разницы между датами в человекообразном формате, с учетом склонения числительных.
     * Пример:
     *      Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
     *      Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
     *      Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
     *      Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
     *      Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
     *      Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год
     */
//    @Test
//    fun test_humanizeDiff() {
//        assertEquals("2 часа назад", Date().add(-2, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
//        assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("через 7 дней", Date().add(7, TimeUnits.DAY).humanizeDiff())
//        assertEquals("более года назад", Date().add(-400, TimeUnits.DAY).humanizeDiff())
//        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())
//        assertEquals("только что", Date().add(-1, TimeUnits.SECOND).humanizeDiff())
//        assertEquals("несколько секунд назад", Date().add(-45, TimeUnits.SECOND).humanizeDiff())
//        assertEquals("минуту назад", Date().add(-46, TimeUnits.SECOND).humanizeDiff())
//        assertEquals("1 минуту назад", Date().add(-76, TimeUnits.SECOND).humanizeDiff())
//        assertEquals("минуту назад", Date().add(-1, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("2 минуты назад", Date().add(-2, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("3 минуты назад", Date().add(-3, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("45 минут назад", Date().add(-45, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("час назад", Date().add(-1, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("1 час назад", Date().add(-76, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("2 часа назад", Date().add(-120, TimeUnits.MINUTE).humanizeDiff())
//        assertEquals("3 часа назад", Date().add(-3, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("4 часа назад", Date().add(-4, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("5 часов назад", Date().add(-5, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("день назад", Date().add(-26, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("1 день назад", Date().add(-27, TimeUnits.HOUR).humanizeDiff())
//        assertEquals("4 дня назад", Date().add(-4, TimeUnits.DAY).humanizeDiff())
//        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
//        assertEquals("360 дней назад", Date().add(-360, TimeUnits.DAY).humanizeDiff())
//        assertEquals("более года назад", Date().add(-361, TimeUnits.DAY).humanizeDiff())
//    }

    /**
     * Необходимо создать абстрактный класс BaseMessage содержащий сделующие свойства:
     *      val id: String,
     *      val from: User?,
     *      val chat: Chat,
     *      val isIncoming: Boolean = false,
     *      val date: Date = Date()
     *  и абстрактный метод formatMessage() - возвращает строку содержащюю информацию о id сообщения, имени получателя/отправителя,
     *  виде сообщения ("получил/отправил") и типе сообщения ("сообщение"/"изображение")
     * Реализуй паттерн AbstractFactory с методом makeMessage(from, chat, date, type, payload, isIncoming = false) принимающий
     * в качесте аргументов пользователя создавшего сообщение, чат к которому относится сообщение,
     * дату сообщения и его тип ("text/image"), полезную нагрузку
     *     Пример:
     *          BaseMessage.makeMessage(user, chat, date, "any text message", "text") //Василий отправил сообщение "any text message" только что
     *          BaseMessage.makeMessage(user, chat, date, "https://anyurl.com", "image",true) //Василий получил изображение "https://anyurl.com" 2 часа назад
     */
//    @Test
//    fun test_abstractFactoryForMessages() {
//        val user = User.Builder()
//                .id("123")
//                .firstName("Василий")
//                .lastName("Пупкин")
//                .avatar(null)
//                .rating(1)
//                .respect(1)
//                .lastVisit(null)
//                .isOnline(false)
//                .build()
//        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
//        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), Date().add(-2, TimeUnits.HOUR), "image", "https://anyurl.com", true)
//        println(textMessage.formatMessage())
//        println(imageMessage.formatMessage())
//        assertEquals("id:0 Василий отправил сообщение \"any text message\" только что", textMessage.formatMessage())
//        assertEquals("id:1 Василий получил изображение \"https://anyurl.com\" 2 часа назад", imageMessage.formatMessage())
//    }
}

