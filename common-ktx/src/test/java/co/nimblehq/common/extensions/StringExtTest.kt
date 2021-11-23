package co.nimblehq.common.extensions

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class StringExtTest {

    @Test
    fun `When given a string, isNotNullOrEmpty should return true`() {
        val given = "given string"
        val result = given.isNotNullOrEmpty()
        assertTrue(result)
    }

    @Test
    fun `When given a null string, isNotNullOrEmpty should return false`() {
        val given: String? = null
        val result = given.isNotNullOrEmpty()
        assertFalse(result)
    }

    @Test
    fun `When given a empty string, isNotNullOrEmpty should return false`() {
        val given = ""
        val result = given.isNotNullOrEmpty()
        assertFalse(result)
    }

    @Test
    fun `When given a blank space, isNotNullOrEmpty should return true`() {
        val given = " "
        val result = given.isNotNullOrEmpty()
        assertTrue(result)
    }

    @Test
    fun `When given a string, isNotNullOrBlank should return true`() {
        val given = "given string"
        val result = given.isNotNullOrBlank()
        assertTrue(result)
    }

    @Test
    fun `When given a null string, isNotNullOrBlank should return false`() {
        val given: String? = null
        val result = given.isNotNullOrBlank()
        assertFalse(result)
    }

    @Test
    fun `When given a empty string, isNotNullOrBlank should return false`() {
        val given = ""
        val result = given.isNotNullOrBlank()
        assertFalse(result)
    }

    @Test
    fun `When given a blank space, isNotNullOrBlank should return false`() {
        val given = " "
        val result = given.isNotNullOrBlank()
        assertFalse(result)
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

    @Test
    fun `When given a string in email format, it returns true`() {
        val result = "email@email.com".isEmailValid()
        assertTrue(result)
    }

    @Test
    fun `When given a string lacks of @, it returns false`() {
        val result = "emailemail.com".isEmailValid()
        assertFalse(result)
    }

    @Test
    fun `When given a string lacks of the top-level domains, it returns false`() {
        val result = "email@email".isEmailValid()
        assertFalse(result)
    }

    @Ignore
    @Test
    fun `When given a Thai string, it returns true`() {
        assertTrue("นามสกุลภาษาไทย".isThai())
    }

    @Ignore
    @Test
    fun `When given not a Thai string, it returns false`() {
        assertFalse("".isThai())
        assertFalse("นามสกุa".isThai())
        assertFalse("的นาม".isThai()) // chinese character
        assertFalse("ふりがなนาม".isThai()) // japanese characters
    }
}
