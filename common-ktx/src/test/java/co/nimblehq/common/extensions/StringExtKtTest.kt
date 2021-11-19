package co.nimblehq.common.extensions

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class StringExtKtTest {

    @Test
    fun `When given a string, isNotNullOrEmpty should return true`() {
        val given = "given string"
        val result = given.isNotNullOrEmpty()
        assertEquals(true, result)
    }

    @Test
    fun `When given a null string, isNotNullOrEmpty should return false`() {
        val given: String? = null
        val result = given.isNotNullOrEmpty()
        assertEquals(false, result)
    }

    @Test
    fun `When given a empty string, isNotNullOrEmpty should return false`() {
        val given = ""
        val result = given.isNotNullOrEmpty()
        assertEquals(false, result)
    }

    @Test
    fun `When given a blank space, isNotNullOrEmpty should return true`() {
        val given = " "
        val result = given.isNotNullOrEmpty()
        assertEquals(true, result)
    }

    @Test
    fun `When given a string, isNotNullOrBlank should return true`() {
        val given = "given string"
        val result = given.isNotNullOrBlank()
        assertEquals(true, result)
    }

    @Test
    fun `When given a null string, isNotNullOrBlank should return false`() {
        val given: String? = null
        val result = given.isNotNullOrBlank()
        assertEquals(false, result)
    }

    @Test
    fun `When given a empty string, isNotNullOrBlank should return false`() {
        val given = ""
        val result = given.isNotNullOrBlank()
        assertEquals(false, result)
    }

    @Test
    fun `When given a blank space, isNotNullOrBlank should return false`() {
        val given = " "
        val result = given.isNotNullOrBlank()
        assertEquals(false, result)
    }

    @Test
    fun `When given a snake case input, it converts to a title-case format correctly`() {
        val result = "snake_case".titleize("_")
        assertEquals("Snake Case", result)
    }

    @Test
    fun `When given a string with spaces, it converts to a snake case format correctly`(){
        val result = "Snake Case".spaceToSnakeCase()
        assertEquals("snake_case", result)
    }

    @Ignore
    @Test
    fun `When given a string in email format, it returns true`() {
        val result = "email@email.com".isEmailValid()
        assertEquals(true, result)
    }

    @Ignore
    @Test
    fun `When given a string lacks of @, it returns false`() {
        val result = "emailemail.com".isEmailValid()
        assertEquals(false, result)
    }

    @Ignore
    @Test
    fun `When given a string lacks of the top-level domains, it returns false`() {
        val result = "email@email".isEmailValid()
        assertEquals(false, result)
    }
}
