package com.hashiradev.fancycards.ui


import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.hashiradev.fancycards.App
import com.hashiradev.fancycards.R
import com.hashiradev.fancycards.data.FancyCard
import com.hashiradev.fancycards.databinding.ActivityAddNewFancyCardBinding

class AddNewFancyCard : AppCompatActivity() {

    private val binding by lazy { ActivityAddNewFancyCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListners()
    }

    private fun insertListners() {

        var selectedColor: Int? = null

        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.tieHexColor.setOnClickListener {
            // Kotlin Code
            MaterialColorPickerDialog
                .Builder(this)        					// Pass Activity Instance
                .setTitle(R.string.color_picker_title)           		// Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)   	// Default ColorShape.CIRCLE
                .setColorSwatch(ColorSwatch._300)   	// Default ColorSwatch._500
                .setColorListener { color, _ ->
                    binding.tieHexColor.setBackgroundColor(color)
                    binding.tilHexColor.hint = ""
                    selectedColor = color
                }
                .show()
        }

        binding.btnSave.setOnClickListener {
            saveFancyCard(selectedColor ?: Color.LTGRAY)
        }
    }

    private fun saveFancyCard(selectedColor: Int) {
        val name = binding.tilName.editText?.text.toString()
        val email = binding.tilEmail.editText?.text.toString()
        val phone = binding.tilPhone.editText?.text.toString()
        val company = binding.tilCompany.editText?.text.toString()
        val customBackgroundColor = selectedColor

        if (name.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.empty_name_title), getString(R.string.empty_name_message))
        } else if (phone.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.empty_phone_title), getString(R.string.empty_phone_message))
        } else if (email.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.empty_email_title), getString(R.string.empty_email_message))
        }  else if (company.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.empty_company_title), getString(R.string.empty_company_message))
        } else {
            val fancyCard = FancyCard(
                    name = name,
                    company = company,
                    phone = phone,
                    email = email,
                    customBackgroundColor = customBackgroundColor
            )
            mainViewModel.insert(fancyCard)

            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        //add ok button
        builder?.apply {
            setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { _, _ ->
                        // User clicked OK button
                    })
            // 2. Chain together various setter methods to set the dialog characteristics
            builder?.setMessage(message)
                    ?.setTitle(title)

            // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
            val dialog: AlertDialog? = builder?.create()
            dialog?.show()
        }
    }
}