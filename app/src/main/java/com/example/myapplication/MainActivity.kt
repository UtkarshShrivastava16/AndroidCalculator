package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var numOneTxt: EditText
    private lateinit var numTwoTxt: EditText
    private lateinit var ansValTxt: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numOneTxt = findViewById(R.id.numOneTxt)
        numTwoTxt = findViewById(R.id.numTwoTxt)
        ansValTxt = findViewById(R.id.ansValTxt)
        calculateButton = findViewById(R.id.calculateBtn)
        radioGroup = findViewById(R.id.radioGrp)

        calculateButton.setOnClickListener{
            if(checkInput(it)){
                calculateResult(it)
            }
            else {
                Log.d(TAG, "invalid")
                ansValTxt.setText("?")
            }
        }
    }

    private fun calculateResult(view: View){
        var radioId =  radioGroup.checkedRadioButtonId
        radioButton = findViewById(radioId)

        if(radioButton.text.toString() == getString(R.string.add)){

            var ans = (numOneTxt.text.toString().toFloat() + numTwoTxt.text.toString()
                .toFloat()).toString()
            Log.d(TAG, ans)
            ansValTxt.setText(ans)
        }
        else if(radioButton.text.toString() == getString(R.string.sub)){
            var ans = (numOneTxt.text.toString().toFloat() - numTwoTxt.text.toString()
                .toFloat()).toString()
            ansValTxt.setText(ans)
        }
        else if(radioButton.text.toString() == getString(R.string.mul)){
            var ans = (numOneTxt.text.toString().toFloat() * numTwoTxt.text.toString()
                .toFloat()).toString()
            ansValTxt.setText(ans)
        }
        else if(radioButton.text.toString() == getString(R.string.div)){
            if(checkInputDiv(view)) {
                var ans = (numOneTxt.text.toString().toFloat() / numTwoTxt.text.toString()
                    .toFloat()).toString()
                ansValTxt.setText(ans)
            }
            else
                ansValTxt.setText("?")
        }
        else if(radioButton.text.toString() == getString(R.string.mod)) {
            var ans = (numOneTxt.text.toString().toFloat() % numTwoTxt.text.toString()
                .toFloat()).toString()
            ansValTxt.setText(ans)
        }
    }


    private fun checkInput(view: View): Boolean {
        if(numOneTxt.text.toString()=="" || numTwoTxt.text.toString()==""){
            Log.d(TAG, "input invalid")

            Snackbar.make(view, R.string.inputCheck, Snackbar.LENGTH_SHORT).show()
            return false
        }
        Log.d(TAG, "input valid")
        return true
    }

    private fun checkInputDiv(view: View): Boolean{
        if(numOneTxt.text.toString()=="" || numTwoTxt.text.toString()==""){
            Log.d(TAG, "input invalid")

            Snackbar.make(view, R.string.inputCheck, Snackbar.LENGTH_SHORT).show()
            return false
        }

        if(numTwoTxt.text.toString().toFloat()  == 0.0F) {
            Snackbar.make(view, R.string.divZero, Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
