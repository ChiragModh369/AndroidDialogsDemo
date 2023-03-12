package com.example.alertdialogdemo

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Timer

class MainActivity : AppCompatActivity() {
//Alert Dialog
    lateinit var btnAlert:Button
    lateinit var alertDialog: AlertDialog

    //Process Dialog

    lateinit var btnProgress:Button
    lateinit var pdialog: ProgressDialog
    var Count:Int = 0

    //List Dialog

    lateinit var  btnSimpleList:Button
    lateinit var listAlertDialog: AlertDialog

    //Custom Login Page
    lateinit var btnLogin:Button
    lateinit var LoginDialog:AlertDialog

    //DatePicker
    lateinit var btnDatePicker:Button
    lateinit var datePickerDialog:DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        btnAlert = findViewById(R.id.btnAlert)
        btnProgress = findViewById(R.id.btnProgress)
        btnSimpleList = findViewById(R.id.btnSimpleList)
        btnLogin = findViewById(R.id.btnLogin)
        btnDatePicker = findViewById(R.id.btnDatePicker)

        btnAlert.setOnClickListener {
            init()
            alertDialog.show()
        }

        btnProgress.setOnClickListener {
            createProgress()
            pdialog.show()
        }

        btnSimpleList.setOnClickListener {
            createListDialog()
            listAlertDialog.show()
        }

        btnLogin.setOnClickListener {
            createCustomView()
            LoginDialog.show()
        }

        btnDatePicker.setOnClickListener {
            createDatePicker()
            datePickerDialog.show()
        }
    }



    fun init()
    {
        alertDialog = AlertDialog.Builder(this)
            .setPositiveButton("Yes",DialogInterface.OnClickListener({dialog, which ->
                Toast.makeText(this,"Clicked on Positive Button",Toast.LENGTH_LONG).show()
            }))
            .setNeutralButton("No",DialogInterface.OnClickListener({dialog, which ->
                Toast.makeText(this,"Clicked on Neutral Button",Toast.LENGTH_LONG).show()
            }))
            .setNegativeButton("Cancel",DialogInterface.OnClickListener({dialog, which ->
                Toast.makeText(this,"Clicked on Negative Button",Toast.LENGTH_LONG).show()
            }))
            .setMessage("You Selected")
            .setTitle("Alert Dialog")
            .setCancelable(false)
            .create()
    }

    fun createProgress()
    {
        pdialog = ProgressDialog(this)
        pdialog.setMessage("It is taking longer than usual")
        pdialog.setTitle("Downloading")
        pdialog.setCancelable(false)
        pdialog.setButton(ProgressDialog.BUTTON_NEGATIVE,"Cancel"){dialog,which ->
            Toast.makeText(this,"You Canceled",Toast.LENGTH_LONG).show()
        }

        val timer = object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
pdialog.setTitle("Downloading ...."+Count + "/100%")
                Count = Count+10
            }

            override fun onFinish() {
                pdialog.dismiss()
            }
        }
        timer.start()

    }

    fun createListDialog() {
        listAlertDialog = AlertDialog.Builder(this)
//            .setItems(R.array.Semester,DialogInterface.OnClickListener({dialog, which ->
//                Toast.makeText(this, "Selection is : " + which, Toast.LENGTH_LONG).show()
//            }))
//            .setSingleChoiceItems(R.array.Semester, 2,
//                { dialog, which ->
//                    Toast.makeText(this, "Selection is : " + which, Toast.LENGTH_LONG).show()
//                    dialog.dismiss()
//                })
            .setMultiChoiceItems(R.array.Semester,null,DialogInterface.OnMultiChoiceClickListener({dialog, which, isChecked ->
                Toast.makeText(this, "$which::$isChecked", Toast.LENGTH_LONG).show()
            }))
            .setNegativeButton("Cancel",DialogInterface.OnClickListener({dialog, which ->
                Toast.makeText(this, "Closed", Toast.LENGTH_LONG).show()
            }))
            .setTitle("Choose Your Semester")
            .setCancelable(false)
            .create()
    }

    fun createCustomView() {
        LoginDialog = AlertDialog.Builder(this).create()
        var customView = this.layoutInflater.inflate(R.layout.logindialog,null)
        var loginButton =customView.findViewById<View>(R.id.btnLogin) as  Button
        loginButton.setOnClickListener {
            Toast.makeText(this, "Haa, Custom Dialog avdi gayu", Toast.LENGTH_LONG).show()
        }
        LoginDialog.setView(customView)
        LoginDialog.setTitle("Sign In")

    }


    fun createDatePicker() {
        datePickerDialog = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            Toast.makeText(this, "$dayOfMonth:$month:$year", Toast.LENGTH_LONG).show()
        },2023,2,12)

        datePickerDialog.setTitle("Select Your DOB")

        var datetime1 = LocalDateTime.of(2021,3,5,6,43,14)
        val minsec = datetime1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val datetime2 = LocalDateTime.of(2023,2,12,6,43,14)
        val maxsec = datetime2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        datePickerDialog.datePicker.minDate = minsec
        datePickerDialog.datePicker.maxDate = maxsec

    }


}