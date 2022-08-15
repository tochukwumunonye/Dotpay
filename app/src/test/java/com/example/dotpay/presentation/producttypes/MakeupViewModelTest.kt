package com.example.dotpay.presentation.producttypes

import com.example.dotpay.data.remote.repository.MakeupRepositoryImplementation
import com.example.dotpay.domain.model.Makeup
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
class MakeupViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var makeupViewModel: MakeupViewModel
    private val mockMakeupRepository: MakeupRepositoryImplementation = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given products are loaded When data source returns success Then emit success view state`() {
        val expectedProducts = getListOfMakeup()
        runTest {
            whenever(mockMakeupRepository.getMakeupItems("dior", "brush")).thenReturn(expectedProducts)
            makeupViewModel = MakeupViewModel(mockMakeupRepository)
            makeupViewModel.search("dior", "brush")
            val stateFlow = makeupViewModel.viewState.value
            assertEquals(stateFlow, MakeupListViewState.Success(expectedProducts))
        }
    }

    @Test
    fun `When data source returns error Then emit error view state`() {
        runTest {
            whenever(mockMakeupRepository.getMakeupItems("dior", "brush")).thenThrow(RuntimeException(""))
            makeupViewModel = MakeupViewModel(mockMakeupRepository)
            makeupViewModel.search("dior", "brush")
            val stateFlow = makeupViewModel.viewState.value
            assertEquals(stateFlow,MakeupListViewState.Error(""))
        }
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
