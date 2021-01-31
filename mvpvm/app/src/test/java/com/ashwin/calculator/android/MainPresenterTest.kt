package com.ashwin.calculator.android

import com.ashwin.calculator.android.di.DispatcherProvider
import com.ashwin.calculator.android.main.MainContract
import com.ashwin.calculator.android.main.MainPresenter
import com.ashwin.calculator.android.main.ViewEvent
import com.ashwin.calculator.usecase.Evaluator
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainPresenterTest {
    private val dispatcher: DispatcherProvider = mockk()

    private var view: MainContract.View = mockk(relaxed = true)
    private var viewModel: MainContract.ViewModel = mockk(relaxed = true)

    private val evaluator: Evaluator = mockk(relaxed = true)

    val presenter: MainPresenter = MainPresenter(view, viewModel, dispatcher, evaluator)

    @BeforeEach
    fun setUpMocks(){
        // clear mock interactions
        clearAllMocks()

        // mockk response functions
        every { dispatcher.ioContext() } returns Dispatchers.Unconfined
        every { dispatcher.uiContext() } returns Dispatchers.Unconfined
    }

    @Test
    fun onStartTest() {
        presenter.onEvent(ViewEvent.OnStart)

        verify { view.bindEventListener() }
        verify { viewModel.setDisplayObserver(any(), any()) }
    }

    @Test
    fun onDeleteClickTest() {
        val TEST_ORIGINAL = "2+2"
        val TEST_RESULT = "2+"

        every { viewModel.getDisplayState() } returns TEST_ORIGINAL

        presenter.onEvent(ViewEvent.OnDeleteClick)

        verify { viewModel.setDisplayState(TEST_RESULT) }
    }
}
