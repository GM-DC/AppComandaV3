package com.owlcode.appcomandav3.common

import com.emh.thermalprinter.EscPosPrinter
import com.emh.thermalprinter.connection.tcp.TcpConnection
import com.owlcode.appcomandav3.domain.orders.model.OrderModel
import java.util.concurrent.CountDownLatch

class PrintOrder {
    fun printTcp(ip: String?, port: Int, item: OrderModel): Boolean {
        var executedSuccessfully = true
        val latch = CountDownLatch(1)

        try {
            val printer = EscPosPrinter(TcpConnection(ip, port), 203, 65f, 42)
            val builder = StringBuilder()
            item.detalle.forEach { it ->
                builder.append("[L] <font size='tall'>").append(it.cantidad)
                    .append(" ").append(it.producto).append("</font>[L]\n")
                if (!it.observacion.isEmpty()) {
                    builder.append("[L] <font size='tall'> Obs: ")
                        .append(it.observacion).append("</font>[L]\n")
                    builder.append("[L] _______________\n")
                }
            }
            printer.printFormattedTextAndCut(
                "[C]<b><u><font size='big'>" + item.destino + "</font></u></b>\n[L]\n" +
                        "[L]<b><font size='tall'> ZONA: " + item.zona + "</font></b>[L]\n" +
                        "[L]<b><font size='tall'> MESA: " + item.mesa + "</font></b>[L]\n" +
                        "[L]<b><font size='tall'> MOZO: " + item.mesero + "</font></b>[L]\n" +
                        "[L]<font size='tall'> PEDIDO: " + item.numerO_PEDIDO + "</font>[L]\n" +
                        "[L]<font size='tall'> FECHAR Y HORA:" + item.fechayhora + "</font>[L]\n" +
                        "[L] \n" +
                        "[L]<font size='tall'> PRODUCTOS [L]</font>\n" +
                        builder +
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
