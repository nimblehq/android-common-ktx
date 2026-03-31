package co.nimblehq.common.extensions.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ModifierExtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    // region throttleClickable (basic)

    @Test
    fun `When clicking once, it registers the click`() {
        var clickCount = 0
        composeTestRule.setContent {
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button").performClick()

        assertEquals(1, clickCount)
    }

    @Test
    fun `When clicking rapidly, it registers only the first click`() {
        var clickCount = 0
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button")
            .performClick()
            .performClick()
            .performClick()

        assertEquals(1, clickCount)
    }

    @Test
    fun `When clicking after the interval has passed, it registers the click again`() {
        var clickCount = 0
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable(intervalMillis = 100L) { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button").performClick()
        assertEquals(1, clickCount)

        composeTestRule.mainClock.advanceTimeBy(150L)

        composeTestRule.onNodeWithTag("button").performClick()
        assertEquals(2, clickCount)
    }

    @Test
    fun `When disabled, it does not register the click`() {
        var clickCount = 0
        composeTestRule.setContent {
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable(enabled = false) { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button").performClick()

        assertEquals(0, clickCount)
    }

    // endregion

    // region throttleClickable (with interactionSource and indication)

    @Test
    fun `When clicking once with interactionSource, it registers the click`() {
        var clickCount = 0
        composeTestRule.setContent {
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button").performClick()

        assertEquals(1, clickCount)
    }

    @Test
    fun `When clicking rapidly with interactionSource, it registers only the first click`() {
        var clickCount = 0
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable(
                        interactionSource = interactionSource,
                        indication = null,
                    ) { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button")
            .performClick()
            .performClick()
            .performClick()

        assertEquals(1, clickCount)
    }

    @Test
    fun `When clicking after the interval has passed with interactionSource, it registers the click again`() {
        var clickCount = 0
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable(
                        interactionSource = interactionSource,
                        indication = null,
                        intervalMillis = 100L,
                    ) { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button").performClick()
        assertEquals(1, clickCount)

        composeTestRule.mainClock.advanceTimeBy(150L)

        composeTestRule.onNodeWithTag("button").performClick()
        assertEquals(2, clickCount)
    }

    @Test
    fun `When disabled with interactionSource, it does not register the click`() {
        var clickCount = 0
        composeTestRule.setContent {
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .testTag("button")
                    .throttleClickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = false,
                    ) { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("button").performClick()

        assertEquals(0, clickCount)
    }

    // endregion
}
