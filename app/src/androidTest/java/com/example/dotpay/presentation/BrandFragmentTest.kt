package com.example.dotpay.presentation

import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.filters.MediumTest
import com.example.dotpay.di.launchFragmentInHiltsContainer
import com.example.dotpay.presentation.brands.BrandFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import com.example.dotpay.R
import com.example.dotpay.presentation.brands.BrandFragmentDirections
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.containsString
import org.mockito.Mockito
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class BrandFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun check_if_selected_right_brand_in_spinner() {
        launchFragmentInHiltsContainer<BrandFragment>(
            fragmentFactory = testFragmentFactory
        )
        onView(withId(R.id.spinner)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("dior"))))
    }


    @Test
    fun click_go_button_to_go_to_makeupFragment() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltsContainer<BrandFragment>(
            fragmentFactory = testFragmentFactory
        ){
            androidx.navigation.Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.btngo)).perform(click())
        verify(navController).navigate(
            BrandFragmentDirections.actionBrandFragmentToMakeUp("dior")
        )
    }

}
