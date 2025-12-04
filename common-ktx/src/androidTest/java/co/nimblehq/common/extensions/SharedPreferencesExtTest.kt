package co.nimblehq.common.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
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
    private val testKey = "TEST_KEY"

    @Before
    fun setup() {
        sharedPreferences = context.getSharedPreferences(
            "test_${this.javaClass.simpleName}",
            Context.MODE_PRIVATE
        )
    }

    @Test
    fun when_set_with_boolean_value_then_return_the_correct_one() {
        val testValue = true
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, false)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_float_value_then_return_the_correct_one() {
        val testValue = 2.1f
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, 0f)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_double_value_then_return_the_correct_one() {
        val testValue = 2.2
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, 0.0)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_int_value_then_return_the_correct_one() {
        val testValue = 9
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, 0)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_long_value_then_return_the_correct_one() {
        val testValue = 1000L
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, 0L)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_string_value_then_return_the_correct_one() {
        val testValue = "Nimble"
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, "")
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_set_string_value_then_return_the_correct_one() {
        val testValue = setOf("Nimble", "Family")
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.get(testKey, setOf<String>())
        assertThat(test, `is`(testValue))
    }

    @Test
    fun when_set_with_object_value_then_return_the_correct_one() {
        val testValue = TestClass()
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.getObject<TestClass>(testKey)
        assertThat(test, isA(TestClass::class.java))
    }

    @Test
    fun when_get_with_wrong_type_then_return_null_value() {
        val testValue = Integer.valueOf(1)
        sharedPreferences[testKey] = testValue
        val test = sharedPreferences.getObject<Integer>(testKey)
        assert(test == null)
    }

    @Test(expected = Exception::class)
    fun when_get_with_wrong_object_type_then_return_exception() {
        val testValue = mapOf("one" to 1)
        sharedPreferences[testKey] = testValue
        sharedPreferences.get(testKey, mapOf<String, Int>())
    }

    @Test
    fun when_calling_clear_all_then_all_data_should_be_cleard() {
        val testValue = "Nimble"
        sharedPreferences[testKey] = testValue
        var test = sharedPreferences.get(testKey, "")
        assertThat(test, `is`(testValue))
        sharedPreferences.clearAll()
        test = sharedPreferences.get(testKey, "")
        assertThat(test, `is`(""))
    }

    class TestClass
}
