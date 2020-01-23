package br.pro.lmit.marvelcharacters


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ViewCharacterListTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun viewCharacterListTest() {

        onView(withText("MarvelCharacters")).check(matches(isDisplayed()))
        onView(withId(R.id.loading_layout)).check(matches(isDisplayed()))

        Thread.sleep(3000)

        onView(withId(R.id.list_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(hasMinimumChildCount(1)))

    }


    @Test
    fun selectCharacterFromListTest() {
        Thread.sleep(3000)

        onView(childAtPosition(withId(R.id.recycler), 3)).perform(click())

        Thread.sleep(500)

        onView(withId(R.id.loading_layout)).check(matches(isDisplayed()))

        Thread.sleep(3000)

        onView(withId(R.id.content)).check(matches(isDisplayed()))

        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.comics)).check(matches(isDisplayed()))
        onView(withId(R.id.stories)).check(matches(isDisplayed()))
        onView(withId(R.id.series)).check(matches(isDisplayed()))
        onView(withId(R.id.events)).check(matches(isDisplayed()))
        onView(withId(R.id.details_button)).check(matches(isDisplayed()))
        onView(withId(R.id.wiki_button)).check(matches(isDisplayed()))

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
