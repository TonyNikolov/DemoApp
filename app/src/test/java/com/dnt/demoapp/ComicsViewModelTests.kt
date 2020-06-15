package com.dnt.demoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dnt.demoapp.dagger2.modules.coroutines.IOCoroutineScope
import com.dnt.demoapp.data.models.Comic
import com.dnt.demoapp.data.models.responses.ApiResponse
import com.dnt.demoapp.data.models.responses.Response
import com.dnt.demoapp.data.repositories.IComicsRepository
import com.dnt.demoapp.features.comics.ComicsViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.coroutineContext

class ComicsViewModelTests {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    lateinit var viewModel: ComicsViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestPolymorphicCoroutineScopeImpl(testDispatcher)
    @MockK
    lateinit var comicsRepository: IComicsRepository

    @Before
    fun init() {
        // Arrange

        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)

        viewModel = ComicsViewModel(comicsRepository, testScope)
    }

    @After
    fun clean() {
        Dispatchers.resetMain()
        // Reset Coroutine Dispatcher and Scope.
        testDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun test() {
        // Arrange
        val apiResponse = mockk<ApiResponse<Response<Comic>>>(relaxed = true)
        coEvery { comicsRepository.getAll() } returns apiResponse
        // Act
        viewModel.getComics()

        // Assert
        coVerify(exactly = 1) { comicsRepository.getAll() }
    }
}
