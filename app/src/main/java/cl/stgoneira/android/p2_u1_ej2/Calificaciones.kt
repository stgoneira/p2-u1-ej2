package cl.stgoneira.android.p2_u1_ej2

class Calificaciones {

    val notas = FloatArray(7) {1.0f}

    fun setNota(notaNum:Int, nota:Float): Boolean {
        if(nota in 1.0f..7.0f && notaNum in 1..7) {
            val indice = notaNum - 1
            notas[indice] = nota
            return true
        }
        return false
    }

    fun calcularNotaFinal(): Float {
        var notaFinal = 0.0f
        for((i, nota) in notas.withIndex()) {
            when(i) {
                in listOf(0, 2, 4)  -> notaFinal += nota * 0.15f
                in listOf(1, 3, 5)  -> notaFinal += nota * 0.05f
                6                   -> notaFinal += nota * 0.4f
            }
        }
        return notaFinal
    }
}


