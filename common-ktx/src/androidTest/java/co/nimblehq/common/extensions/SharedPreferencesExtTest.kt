package co.nimblehq.common.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.isA
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesExtTest {
    private var context: Context = InstrumentationRegistry.getInstrumentation().context
    private lateinit var sharedPreferences: SharedPreferences
    private val TEST_KEY = "TEST_KEY"

    @Before
    fun setup() {
        sharedPreferences = context.getSharedPreferences(
            "test_${this.javaClass.simpleName}",
            Context.MODE_PRIVATE
        )
    }

    @Test
    fun testSetterAndGetterBooleanValue() {
        val testValue = true
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, false)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterFloatValue() {
        val testValue = 2.1f
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0f)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterDoubleValue() {
        val testValue = 2.2
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0.0)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterIntValue() {
        val testValue = 9
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterLongValue() {
        val testValue = 1000L
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0L)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterStringValue() {
        val testValue = "Nimble"
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterSetStringValue() {
        val testValue = setOf("Nimble", "Family")
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, setOf<String>())
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterObjectValue() {
        val testValue = TestClass()
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.getObject<TestClass>(TEST_KEY)
        assertThat(test, isA(TestClass::class.java))
    }

    @Test
    fun testWithWrongSaveUnsupportedObject() {
        val testValue = Integer(1)
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.getObject<Integer>(TEST_KEY)
        assert(test == null)
    }

    @Test(expected = Exception::class)
    fun testWithWrongGetObject() {
        val testValue = mapOf("one" to 1)
        sharedPreferences[TEST_KEY] = testValue
        sharedPreferences.get(TEST_KEY, mapOf<String, Int>())
    }

    @Test
    fun testClearAll() {
        val testValue = "Nimble"
        sharedPreferences[TEST_KEY] = testValue
        var test = sharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(testValue))
        sharedPreferences.clearAll()
        test = sharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(""))
    }

    inner class TestClass
}
