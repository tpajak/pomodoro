package org.hyperskill.stopwatch

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class MyDialog(private val message: String) : DialogFragment() {
    private var listener: MyDialogListener? = null
    private var editTextLimit: EditText? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val myLayout: View = inflater.inflate(R.layout.dialog_input, null)
        val builder = AlertDialog.Builder(requireActivity())

        builder
            .setTitle(message)
            .setView(myLayout)
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    //TODO: Check for wrong values (e.g. empty)
                    val userDefinedLimit: TextView = myLayout.findViewById(R.id.upperLimitEditText)
                    listener?.applyTexts(userDefinedLimit.text.toString())
                    // FIRE ZE MISSILES!
                })
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    dialog.dismiss()
                })

        return builder.create()

        editTextLimit = myLayout.findViewById(R.id.upperLimitEditText)
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