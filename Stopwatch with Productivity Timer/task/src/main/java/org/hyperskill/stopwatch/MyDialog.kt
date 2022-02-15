package org.hyperskill.stopwatch

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class MyDialog(private val message: String): DialogFragment() {
    private var listener: MyDialogListener? = null
    private var editTextLimit: EditText? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val inflater = requireActivity().layoutInflater
            val myLayout: View = inflater.inflate(R.layout.dialog_input, null)

            val builder = AlertDialog.Builder(it)
            builder.setView(myLayout)
                .setTitle(message)
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        //TODO: Check for wrong values (e.g. empty)
                        var userDefinedLimit: String = editTextLimit?.text.toString()
                        listener?.applyTexts(userDefinedLimit)
                        // FIRE ZE MISSILES!
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        dialog.dismiss()
                    })
            // Create the AlertDialog object and return it

            editTextLimit = myLayout.findViewById(R.id.upperLimitEditText)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as MyDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString().toString() +
                        "must implement MyDialogListener"
            )
        }
    }

    interface MyDialogListener {
        fun applyTexts(userDefinedLimit: String?)
    }
}