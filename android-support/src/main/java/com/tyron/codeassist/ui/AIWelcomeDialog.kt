package com.tyron.codeassist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tyron.codeassist.R

class AIWelcomeDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ai_welcome, container, false)

        val etApiKey = view.findViewById<EditText>(R.id.etGeminiApiKey)
        val btnSave = view.findViewById<Button>(R.id.btnSaveAndConnect)

        val sharedPref = requireActivity().getSharedPreferences("codeassist_user_prefs", Context.MODE_PRIVATE)
        
        val currentKey = sharedPref.getString("GEMINI_API_KEY", "")
        if (!currentKey.isNullOrEmpty()) {
            etApiKey.setText(currentKey)
        }

        btnSave.setOnClickListener {
            val key = etApiKey.text.toString().trim()
            if (key.isNotEmpty()) {
                sharedPref.edit().putString("GEMINI_API_KEY", key).apply()
                Toast.makeText(context, "تم حفظ الـ API Key وتفعيل المساعد! 🤖", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "عافاك دخل المفتاح أولاً ⚠️", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
