package com.dnt.demoapp.features

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import org.hamcrest.core.IsNot.not
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.dnt.demoapp.R
import com.dnt.demoapp.TestDemoApp
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTests {
    @get:Rule
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java, true, false
    )

    private var webServer: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setup() {
        val app: TestDemoApp =
            InstrumentationRegistry.getInstrumentation()
                .targetContext
                .applicationContext as TestDemoApp
        app.testAppComponent.inject(this)
        webServer = MockWebServer()
        webServer?.start(8080)
        Intents.init()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer?.shutdown()
        Intents.release()
    }

    @Test
    fun test_main_screen() {
        // Act
        rule.launchActivity(Intent())

        // Assert
        // is Bottom Navigation Shown
        val check = Espresso.onView(withId(R.id.bottom_navigation_view))
            .check(ViewAssertions.matches(isDisplayed()))
//
//        val mainActivity = (getCurrentlyVisibleActivity() as MainActivity?)!!
    }
}