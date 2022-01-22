package com.example.aadhaarpannoocr

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions

class MainViewModel : ViewModel() {
    private var textRecognizer: TextRecognizer =
        TextRecognition.getClient(ChineseTextRecognizerOptions.Builder().build())

    private var _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    fun processText(bitmap: Bitmap?) {
        bitmap ?: return

        val imageInput = InputImage.fromBitmap(bitmap, 0)
        textRecognizer.process(imageInput)
            .addOnSuccessListener { text ->
                Log.d(MainActivity.TAG, "Text is: " + text.text)
                for (textBlock in text.textBlocks) {
                    for (line in textBlock.lines) {
                        for (e in textBlock.lines) {
                            if (
                                e.text.validateAadhaarCard() ||
                                e.text.validateAadhaarCardWithoutSpace() ||
                                e.text.validatePanCard()
                            )
                                _text.value = e.text
                            else
                                _text.value = "Scan Again :)"
                        }
                    }
                }
            }
            .addOnFailureListener {
                _text.value = "Failed to process. Error: " + it.localizedMessage
            }
    }
}