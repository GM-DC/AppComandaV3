package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.DodgerBlue
import com.owlcode.appcomandav3.ui.theme.DodgerBlueBackground
import com.owlcode.appcomandav3.ui.theme.Scarlet
import com.owlcode.appcomandav3.ui.theme.ScarletDegradeBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoItem(
    item: ListOrdersModel? = null,
    onClickMas: () -> Unit,
    onClickMenos: () -> Unit,
    onClickObservacion: () -> Unit,
    onSwipeComplete: () -> Unit,
) {
    val color = when {
        item?.estadoPedido.orEmpty() == "PENDIENTE" -> {
            DodgerBlue
        }
        else -> {
            Scarlet
        }
    }
    val colorbackground = when {
        item?.estadoPedido.orEmpty() == "PENDIENTE" -> {
            DodgerBlueBackground
        }
        else -> {
            ScarletDegradeBackground
        }
    }
    val stateComponent = when {
        item?.estadoPedido.orEmpty() == "PENDIENTE" -> {
            true
        }
        else -> {
            false
        }
    }

    val dismissState = rememberDismissState(
        initialValue = DismissValue.Default,
        confirmValueChange = { dismissValue ->
                    when(dismissValue){
                        DismissValue.Default -> {
                            if(item?.estadoPedido.orEmpty() == "PENDIENTE"){
                                onSwipeComplete()
                            }
                        }
                        DismissValue.DismissedToEnd -> {
                            if(item?.estadoPedido.orEmpty() == "PENDIENTE"){
                                onSwipeComplete()
                            }
                        }
                        DismissValue.DismissedToStart -> {
                            if(item?.estadoPedido.orEmpty() == "PENDIENTE"){
                                onSwipeComplete()
                            }
                        }
                    }
                    false//
                },
        positionalThreshold = { swipeActivationFloat -> swipeActivationFloat / 2}
    )

    SwipeToDismiss(
        state = dismissState,
        background = {},
        dismissContent = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .border(1.dp, color, RoundedCornerShape(8.dp))
                    .background(colorbackground,RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(6.5f),
                    ) {
                        TextPrimary(text = item?.namePlato.orEmpty())
                        TextPrimary(text = item?.precio.toString())
                        TextPrimary(text = item?.precioTotal.toString())
                    }
                    Row(
                        modifier = Modifier.weight(3.5f)
                    ) {
                        if(stateComponent){
                            Box(
                                modifier = Modifier
                                    .border(1.dp, Color.Red, RoundedCornerShape(8.dp))
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(vertical = 5.dp, horizontal = 15.dp)
                                    .clickable {
                                        onClickObservacion()
                                    }
                            ) {
                                TextPrimary(text = "N")
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .border(1.dp, Color.Red, RoundedCornerShape(8.dp))
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(vertical = 5.dp, horizontal = 15.dp)
                                    .clickable {
                                        onClickMas()
                                    }
                            ) {
                                TextPrimary(text = "+")
                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        TextPrimary(
                            modifier = Modifier
                                .padding(5.dp),
                            text = if(stateComponent){
                                item?.cantidad.toString()
                            }else{
                                "Cantidad: "+item?.cantidad.toString()
                            }
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        if(stateComponent){
                            Box(
                                modifier = Modifier
                                    .border(1.dp, Color.Red, RoundedCornerShape(8.dp))
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(vertical = 5.dp, horizontal = 15.dp)
                                    .clickable {
                                        onClickMenos()
                                    }
                            ) {
                                TextPrimary(text = "-")
                            }
                        }
                    }
                }
            }
        }
    )

}

enum class SwipeState { Start, End }