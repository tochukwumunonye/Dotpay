package com.example.dotpay.data.remote.repository

import com.example.dotpay.data.remote.api.APIService
import com.example.dotpay.data.remote.makeupdto.MakeupDtoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MakeupRepositoryImplementationTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val api: APIService = mock()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when successful return a list of makeup items`() {
        val makeupItemsResponse =  getResponseFromAPI()

        runTest {
            whenever(api.getMakeUpItems("dior", "brush")).thenReturn(makeupItemsResponse )
            val repository = MakeupRepositoryImplementation(api)
            val makeupListItems = repository.getMakeupItems("dior", "brush")
            assertEquals(makeupListItems.first().brand, "dior")
        }
    }
    private fun getResponseFromAPI(): ArrayList<MakeupDtoItem> {
        return arrayListOf(
            MakeupDtoItem(
                api_featured_image = "https://",
                brand = "dior",
                description = "nice brush",
                image_link = "https://imagelink",
                name = "dior brush",
                price = "$10"
            )
        )
    }

}

