package edu.quinnipiac.ser210.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class CalcActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // adding the question marks in the event nothing is entered.
    var inputBoxOne: EditText? = null
    var inputBoxTwo: EditText? = null
    var resultText: TextView? = null

    // onCreate calls super implementation methods and setContentView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // getting reference of the UI elements in UI components
        // Look for them in the layout
        // Inflate -> drop objects
        // this is the Kotlin equivalent of the what was initialized in the XML files
        inputBoxOne = findViewById<EditText>(R.id.input1)
        inputBoxTwo = findViewById<EditText>(R.id.input2)
        resultText = findViewById<TextView>(R.id.result_text)


        // getting a reference of the addition button and calling the performOp function
        findViewById<Button>(R.id.add_button).setOnClickListener{
            performOp('+')
        }

        // getting a refernce of the spinner and telling the spinner it's onItemSelectedListener is that implementation of the calculator
        findViewById<Spinner>(R.id.spinner).onItemSelectedListener = this


    }

    private fun performOp(op: Char){

        // ensure that box inputs are not empty
        if (inputBoxOne != null && inputBoxTwo != null){
            // gets the inputs from the editText fields and then converts them to a double
            val numberOne: Double =  inputBoxOne?.getText().toString().toDouble()
            val numberTwo: Double =  inputBoxTwo?.getText().toString().toDouble()

            // instance variable that will keep track of the operations occurring (+,-,/,*)
            var result = 0.0
            when(op){
                '+' -> result = numberOne + numberTwo
                '-' -> result = numberOne - numberTwo
                '*' -> result = numberOne * numberTwo
                '/' -> result = numberOne / numberTwo
            }
            // modify the text of the resultText
            resultText?.setText(java.lang.Double.toString(result))
        }

    }
    // function will just perform the subtraction operation by getting a of an onClick
    fun subtraction(view:View?){
        this.performOp('-')
    }

    // we want read in the operation that was selected
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        // add question mark if the operation happens to be null
        val operation = p0?.getItemAtPosition(p2).toString()
        // if the operation does not equal "none" (it kept defaulting to the first operation which was multiplacation, so "none" had to be added)
        if (!operation.equals("none")){
            // gets a reference of the character from the string that's passed in
            this.performOp(operation[0])
        }

    }

    // won't be used
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}