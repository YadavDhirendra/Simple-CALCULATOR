package lordDking.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot:Boolean = false
    private var tvInput:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    tvInput = findViewById(R.id.tvCalc)


    }

    fun onDigit(view:View)
    {
        tvInput?.append((view as Button).text)
        lastDot = false
        lastNumeric = true
    }

    fun onClear(view:View){
        tvInput?.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onOperator(view: View){
       tvInput?.text.let{
           if(lastNumeric && !isOperatorAdded(it.toString()))
           {
               tvInput?.append((view as Button).text)
               lastDot = false
               lastNumeric = false
           }
           else if(isOperatorAdded(it.toString()))
           {
               if(lastNumeric) {
                   onEqual(view)
                   tvInput?.append((view as Button).text)
                   lastDot = false
                   lastNumeric = false
               }
           }
       }
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")) {
            false
        }else
        {
            value.contains("+")
                    || value.contains("*")
                    || value.contains("/")
                    || value.contains("-")
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")//"99-1"
                    var one = splitValue[0]//"99"
                    var two = splitValue[1]//"1"

                    if(prefix == "-")
                    {
                        one = prefix + one
                    }

                    var result:Double = (one.toDouble() - two.toDouble())
                    var formattedResult:Double = String.format("%.12f",result).toDouble()
                    tvInput?.text = removeZeroDot(formattedResult.toString())
                }
                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")//"99+1"
                    var one = splitValue[0]//"99"
                    var two = splitValue[1]//"1"

                    if(prefix == "-")
                    {
                        one = prefix + one
                    }
                    var result:Double = (one.toDouble() + two.toDouble())
                    var formattedResult:Double = String.format("%.12f",result).toDouble()
                    tvInput?.text = removeZeroDot(formattedResult.toString())
                }
                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")//"99/1"
                    var one = splitValue[0]//"99"
                    var two = splitValue[1]//"1"

                    if(prefix == "-")
                    {
                        one = prefix + one
                    }

                    var result:Double = (one.toDouble() / two.toDouble())
                    var formattedResult:Double = String.format("%.12f",result).toDouble()
                    tvInput?.text = removeZeroDot(formattedResult.toString())
                }
                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")//"99*1"
                    var one = splitValue[0]//"99"
                    var two = splitValue[1]//"1"

                    if(prefix == "-")
                    {
                        one = prefix + one
                    }

                    var result:Double = (one.toDouble() * two.toDouble())
                    var formattedResult:Double = String.format("%.12f",result).toDouble()
                    tvInput?.text = removeZeroDot(formattedResult.toString())
                }
                } catch (e:ArithmeticException){
                    e.printStackTrace()
                }
        }
    }

    private fun removeZeroDot(result:String):String{
        var value = result
        if(result.endsWith(".0")) {
            value = result.substring(0, result.length - 2)

        }
        return value
    }

    fun onDelete(view: View){

        var tvValue = tvInput?.text
        if(tvValue!!.endsWith("-") || tvValue!!.endsWith("+") || tvValue!!.endsWith("/") || tvValue!!.endsWith("*"))
            lastNumeric = true


        tvValue = tvValue?.substring(0,tvValue.length-1)
        tvInput?.text = tvValue.toString()

    }
}