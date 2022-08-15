package com.example.dotpay.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.dotpay.di.launchFragmentInHiltsContainer
import com.example.dotpay.domain.model.Makeup
import com.example.dotpay.presentation.producttypes.MakeUpFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import com.example.dotpay.R
import org.junit.Test
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MakeupFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun test_recycler_is_visible_when_data_is_loaded() {
        launchFragmentInHiltsContainer<MakeUpFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            makeupAdapter.submitList(getListOfMakeup())
        }

        onView(withId(R.id.makeupRecyclerview)).check(matches(isDisplayed()))
    }

    private fun getListOfMakeup(): List<Makeup> {
        return listOf(
            Makeup(
                api_featured_image = "https://",
                brand = "dior",
                description = "nice brush",
                image_link = "https://imagelink",
                name = "dior brush",
                price = "$4",
            )
        )
    }
}
