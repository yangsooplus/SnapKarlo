package com.yangsooplus.snapkarlo.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Base64

object Base64Converter {

    fun string2Bitmap(base64: String): Bitmap {
        val byte = Base64.getDecoder().decode(base64)
        return BitmapFactory.decodeByteArray(byte, 0, byte.size)
    }
}
