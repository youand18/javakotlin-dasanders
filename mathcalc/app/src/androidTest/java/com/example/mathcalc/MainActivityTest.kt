package com.example.mathcalc

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkCalculation() {
        onView(withId(R.id.numberInput)).perform(typeText("5,4,3,2,1"), closeSoftKeyboard())
        onView(withId(R.id.calculateButton)).perform(click())

        onView(withId(R.id.minResult)).check(matches(withText("Min: 1.0")))
        onView(withId(R.id.maxResult)).check(matches(withText("Max: 5.0")))
        onView(withId(R.id.avgResult)).check(matches(withText("Average: 3.0")))
        onView(withId(R.id.stdDevResult)).check(matches(withText("Standard Deviation: 1.4142135623730951")))
    }
}