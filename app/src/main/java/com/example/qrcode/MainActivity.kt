package com.example.qrcode

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class MainActivity : AppCompatActivity() {
    var textoGerarQRC: EditText? = null
    var button_confirmar: Button? = null
    var imagem_QRC: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponentes()
        button_confirmar!!.setOnClickListener {
            if(TextUtils.isEmpty(textoGerarQRC!!.text.toString())){

                textoGerarQRC!!.error="*"
                textoGerarQRC!!.requestFocus()

            } else {
                //outro cdigo
            gerarQrc(textoGerarQRC!!.text.toString())

            }
        }

    }

    private fun gerarQrc(conteudoQrCode: String) {
        val qrCode = QRCodeWriter()

        try {
            val bitMatrix = qrCode.encode(conteudoQrCode, BarcodeFormat.QR_CODE, 196, 196)

            val tamanho = bitMatrix.width
            val altura = bitMatrix.height

            val bitmap = Bitmap.createBitmap(tamanho, altura, Bitmap.Config.RGB_565)

            for (x in 0 until tamanho) {
                for (y in 0 until altura)
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }

            imagem_QRC!!.setImageBitmap(bitmap)

        }catch (e: WriterException){
        }


    }

    fun initComponentes() {
        textoGerarQRC = findViewById(R.id.edit_qrcode)
        button_confirmar = findViewById(R.id.button_confirmar)
        imagem_QRC = findViewById(R.id.imagem_QRC)
    }
}