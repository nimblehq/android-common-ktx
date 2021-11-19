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
    private fun setup() {
        sharedPreferences = context.getSharedPreferences(
            "test_${this.javaClass.simpleName}",
            Context.MODE_PRIVATE
        )
    }

    @Test
    private fun when_set_with_boolean_value_then_return_the_correct_one() {
        val testValue = true
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, false)
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_float_value_then_return_the_correct_one() {
        val testValue = 2.1f
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0f)
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_double_value_then_return_the_correct_one() {
        val testValue = 2.2
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0.0)
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_int_value_then_return_the_correct_one() {
        val testValue = 9
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0)
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_long_value_then_return_the_correct_one() {
        val testValue = 1000L
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, 0L)
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_string_value_then_return_the_correct_one() {
        val testValue = "Nimble"
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_set_string_value_then_return_the_correct_one() {
        val testValue = setOf("Nimble", "Family")
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.get(TEST_KEY, setOf<String>())
        assertThat(test, `is`(testValue))
    }

    @Test
    private fun when_set_with_object_value_then_return_the_correct_one() {
        val testValue = TestClass()
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.getObject<TestClass>(TEST_KEY)
        assertThat(test, isA(TestClass::class.java))
    }

    @Test
    private fun when_get_with_wrong_type_then_return_null_value() {
        val testValue = Integer(1)
        sharedPreferences[TEST_KEY] = testValue
        val test = sharedPreferences.getObject<Integer>(TEST_KEY)
        assert(test == null)
    }

    @Test(expected = Exception::class)
    private fun when_get_with_wrong_object_type_then_return_exception() {
        val testValue = mapOf("one" to 1)
        sharedPreferences[TEST_KEY] = testValue
        sharedPreferences.get(TEST_KEY, mapOf<String, Int>())
    }

    @Test
    private fun when_calling_clear_all_then_all_data_should_be_cleard() {
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
