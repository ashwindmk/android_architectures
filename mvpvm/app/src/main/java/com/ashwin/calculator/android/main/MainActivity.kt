package com.ashwin.calculator.android.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.ashwin.calculator.android.Constant
import com.ashwin.calculator.android.R
import com.ashwin.calculator.android.di.MainInjector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainInjector.provideMainPresenter(this)
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun onStart() {
        super.onStart()
        presenter.onEvent(ViewEvent.OnStart)
    }

    override fun bindEventListener() {
        btn_evaluate.setOnClickListener { presenter.onEvent(ViewEvent.OnEvaluateClick) }
        btn_decimal.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.DECIMAL)) }
        btn_display_delete.setOnClickListener { presenter.onEvent(ViewEvent.OnDeleteClick) }
        btn_display_delete.setOnLongClickListener {
            presenter.onEvent(ViewEvent.OnLongDeleteClick)
            //true is required to avoid calling onClick immediately after onLongClick
            true
        }

        btn_number_one.setOnClickListener {
            Log.d(Constant.DEBUG_TAG, "MainView: one click")
            presenter.onEvent(ViewEvent.OnOperandClick(Input.ONE))
        }
        btn_number_two.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.TWO)) }
        btn_number_three.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.THREE)) }
        btn_number_four.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.FOUR)) }
        btn_number_five.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.FIVE)) }
        btn_number_six.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.SIX)) }
        btn_number_seven.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.SEVEN)) }
        btn_number_eight.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.EIGHT)) }
        btn_number_nine.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.NINE)) }
        btn_number_zero.setOnClickListener { presenter.onEvent(ViewEvent.OnOperandClick(Input.ZERO)) }

        btn_operator_add.setOnClickListener { presenter.onEvent(ViewEvent.OnOperatorClick(Input.PLUS)) }
        btn_operator_subtract.setOnClickListener { presenter.onEvent(ViewEvent.OnOperatorClick(Input.MINUS)) }
        btn_operator_multiply.setOnClickListener { presenter.onEvent(ViewEvent.OnOperatorClick(Input.MULTIPLY)) }
        btn_operator_divide.setOnClickListener { presenter.onEvent(ViewEvent.OnOperatorClick(Input.DIVIDE)) }
    }

    override fun getExpression(): String = lbl_display.text.toString()

    override fun setDisplay(value: String) {
        lbl_display?.text = value
    }

    override fun showError(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }

    override fun restartFeature() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        this.finish()
    }
}
