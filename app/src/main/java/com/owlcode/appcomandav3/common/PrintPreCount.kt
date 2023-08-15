package com.owlcode.appcomandav3.common

import android.content.Context
import com.emh.thermalprinter.EscPosPrinter
import com.emh.thermalprinter.connection.tcp.TcpConnection
import com.owlcode.appcomandav3.domain.orders.model.PreCount
import java.util.concurrent.CountDownLatch

class PrintPreCount {
    fun printTcp(ip: String?, port: Int, item: PreCount, context: Context?): Boolean {
        var executedSuccessfully = true
        val latch = CountDownLatch(1)

        try {
            val printer = EscPosPrinter(TcpConnection(ip, port), 203, 65f, 42)
            val builder = StringBuilder()
            item.detalle.forEach {
                builder.append("[L]<font size='tall'>").append(it.cantidad)
                    .append(" ").append(it.nombre).append("[R]")
                    .append(it.importe).append("</font>\n")
            }

            // imprimes
            printer.printFormattedTextAndCut(
                "[C]<b><u><font size='big'>PRE-CUENTA</font></u></b>\n[L]\n" +
                        "[L]<b><font size='tall'> PEDIDO: " + item.numerO_PEDIDO + "</font></b>[L]\n" +
                        "[L]<font size='tall'> ZONA: " + item.zona + "</font>[L]\n" +
                        "[L]<font size='tall'> MESA: " + item.mesa + "</font>[L]\n" +
                        "[L]<font size='tall'> MOZO: " + item.mesero + "</font>[L]\n" +
                        "[L] _______________\n" +
                        "[L]<font size='tall'> Descripcion [R]Importe \n</font>[L]\n" +
                        builder +
                        "[L] _______________\n" +
                        "[L]<font size='tall'> SUBTOTAL [R]" + item.subtotal + "\n" +
                        "[L]<font size='tall'> IGV [R]" + item.igv + "\n" +
                        "[L]<font size='tall'> IMPORTE TOTAL [R]" + item.total + "\n" +
                        "[L]\n" +
                        "[L] _______________\n" +
                        "[L]\n"
            )
        } catch (e: Exception) {
            e.printStackTrace()
            executedSuccessfully = false
        } finally {
            latch.countDown()
        }

        try {
            latch.await()  // This will make the main thread wait until the secondary thread finishes
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return executedSuccessfully
    }
}
