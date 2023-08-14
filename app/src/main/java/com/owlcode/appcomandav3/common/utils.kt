package com.owlcode.appcomandav3.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class utils {

    companion object{
        var URLBASE = ""
        var PORT = ""
        var MESA = ""
        var NOMBRE_USURIO = ""
        var NOMBRE_MOZO = ""
        var DATA_TABLE = TableModel(
            idMesa = 0,
            estado = "",
            estadoTrans = "",
            idZona = "",
            secuencia = null,
            tipo = "",
            idPedido = null,
            NombreMozo = null
        )
    }

    fun pricetostringformat(valuenumeric: Double): String {
        return String.format("%,.2f", valuenumeric)
    }

    fun priceSubTotal(price: Double): Double {
        //val igv = prefs.getIGV().toDouble()
        //(price.div(1 + (igv.div(100))))
        val montoBase = 100*price/118
        return montoBase
    }

    fun priceIGV(price: Double): Double {
        //val igv = prefs.getIGV().toDouble()
        //price.minus(price.div(1 + (igv.div(100))))
        val IGV = price*18/118
        return IGV
    }

}