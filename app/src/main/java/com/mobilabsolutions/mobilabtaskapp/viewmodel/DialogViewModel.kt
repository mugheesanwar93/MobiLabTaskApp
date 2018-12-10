package com.mobilabsolutions.mobilabtaskapp.viewmodel

import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import com.mobilabsolutions.mobilabtaskapp.listener.GallerySelectionListener


class DialogViewModel(private val listener: GallerySelectionListener, val view: View) {

    //Apply filters and send values to function
    fun onApplyPressed(rgSection: RadioGroup, rgSort: RadioGroup, cbViral: CheckBox, spWindow: Spinner) {
        listener.onSelected(getRadioButton(rgSection.checkedRadioButtonId).toString().toLowerCase(),
                getRadioButton(rgSort.checkedRadioButtonId).toString().toLowerCase(),
                spWindow.selectedItem.toString().toLowerCase(),
                cbViral.isChecked
        )
    }

    //Getting selected Radio Button Text
    private fun getRadioButton(sectionId: Int): CharSequence? {
        val rb = view.findViewById<RadioButton>(sectionId)
        return rb.text
    }

    //Setting Radio Group Checks
    fun onRadioChanged(radioGroup: RadioGroup, radioButton: RadioButton) {
        radioGroup.clearCheck()
        radioButton.isChecked = true
    }
}

