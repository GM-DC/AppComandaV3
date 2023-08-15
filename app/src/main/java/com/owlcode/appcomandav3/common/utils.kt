package com.owlcode.appcomandav3.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserResponseModel
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.domain.zones.model.ZoneModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class utils {

    companion object{
        var URLBASE = ""
        var PORT = ""
        var ZONA = ""
        var NOMBRE_USURIO = ""
        var NOMBRE_MOZO = ""
        var DATA_ZONA = ZoneModel(
            nombreZonas = "", idZona = "")
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
        var DATA_USER = LoginUserResponseModel(
            cdgmoneda = "",
            cdgpago = "",
            codigO_EMPRESA = "",
            descuento = "",
            estadopedido = "",
            facturA_ADELANTADA = "",
            iD_CLIENTE = 0,
            iD_COTIZACION = "",
            jwtToken = "",
            nombreMozo = "",
            nombreUsuario = "",
            poR_IGV = "",
            puntO_VENTA = "",
            redondeo = "",
            refreshToken = "",
            seriepedido = "",
            sucursal = "",
            tipocambio = "",
            usuario = "",
            usuarioautoriza = "",
            usuariocreacion = "",
            validez = "",
            cdG_VENDEDOR = ""
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

    fun getFecha(): String {
        return "${LocalDateTime.now()}"
    }

}


fun isValidIP(text: String):Boolean{
    val pattern = """^[0-9.]*$""".toRegex()
    val isValid = pattern.matches(text)
    return isValid
}