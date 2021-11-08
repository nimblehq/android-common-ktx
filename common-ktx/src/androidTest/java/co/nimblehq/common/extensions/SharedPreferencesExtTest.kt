package co.nimblehq.common.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.isA
import org.junit.Before
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class SharedPreferencesExtTest {
    private var mContext: Context = InstrumentationRegistry.getInstrumentation().context
    lateinit var mSharedPreferences: SharedPreferences
    private val TEST_KEY = "TEST_KEY"

    @Before
    fun setup() {
        mSharedPreferences = mContext.myAppPreferences
    }

    @Test
    fun testSetterAndGetterBooleanValue(){
        val testValue = true
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, false)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterFloatValue(){
        val testValue = 2.1f
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, 0f)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterDoubleValue(){
        val testValue = 2.2
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, 0.0)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterIntValue(){
        val testValue = 9
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, 0)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterLongValue(){
        val testValue = 1000L
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, 0L)
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterStringValue(){
        val testValue = "Nimble"
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterSetStringValue(){
        val testValue = setOf("Nimble", "Family")
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, setOf<String>())
        assertThat(test, `is`(testValue))
    }

    @Test
    fun testSetterAndGetterObjectValue(){
        val testValue = TestClass()
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.getObject<TestClass>(TEST_KEY)
        assertThat(test, isA(TestClass::class.java))
    }

    @Test
    fun testWithWrongSaveUnsupportedObject() {
        val testValue = Integer(1)
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.getObject<Integer>(TEST_KEY)
        assert(test == null)
    }

    @Test(expected = Exception::class)
    fun testWithWrongGetObject() {
        val testValue = mapOf("one" to 1)
        mSharedPreferences[TEST_KEY] = testValue
        val test = mSharedPreferences.get(TEST_KEY, mapOf<String, Int>())
    }

    @Test
    fun testClearAll() {
        val testValue = "Nimble"
        mSharedPreferences[TEST_KEY] = testValue
        var test = mSharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(testValue))
        mSharedPreferences.clearAll()
        test = mSharedPreferences.get(TEST_KEY, "")
        assertThat(test, `is`(""))
    }

    inner class TestClass
}
