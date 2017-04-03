package de.werkelmann.rekote

import android.app.Application
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.test.ApplicationTestCase
import android.test.suitebuilder.annotation.SmallTest

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
class ApplicationTest : ApplicationTestCase<Application>(Application::class.java) {

    @SmallTest
    fun makeTestSuiteNotEmpty() {
        onView(withId(R.id.action_settings))
                .perform(click())
                .check(matches(isDisplayed()))
    }
}