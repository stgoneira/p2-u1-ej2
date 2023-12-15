package cl.stgoneira.android.p2_u1_ej2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton = findViewById<Button>(R.id.btnCalcularNotaFinal)
        boton.setOnClickListener {
            val calificaciones = Calificaciones()
            var errores = 0
            for(i in 1..7) {
                val viewId  = resources.getIdentifier("etNota$i", "id", packageName)
                val etNota  = findViewById<EditText>(viewId)
                try {
                    val notaStr = etNota.text.toString()
                    val nota    = notaStr.toFloat()
                    val resultado = calificaciones.setNota(i, nota)
                    if( !resultado ) {
                        throw NumberFormatException("La nota debe estar entre 1 y 7")
                    }
                    etNota.setTextColor( ContextCompat.getColor(this, R.color.black) )
                } catch (e:Exception) {
                    errores += 1
                    etNota.setTextColor( ContextCompat.getColor(this, R.color.red) )
                }
            }
            if( errores > 0 ) {
                Toast.makeText(this, "Corrija las notas marcadas, " +
                        "deben estar entre 1 y 7", Toast.LENGTH_LONG).show()
                findViewById<EditText>(R.id.etNotaFinal).setText("")
            } else {
                val notaFinal = calificaciones.calcularNotaFinal()
                findViewById<EditText>(R.id.etNotaFinal).setText(notaFinal.toString())
            }
        }
    }
}