/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.owlcode.appcomandav3.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF4477CE)
val PurpleGrey40 = Color(0xFF91C8E4)
val Pink40 = Color(0xFF749BC2)


val Shark = Color(0xFF272829)
val Transparent = Color(0xFFFFFF)
val BlackTransparent = Color(0x8A000000)
val While = Color(0xFFFFFFFF)
val DodgerBlue = Color(0xFF5463FF)
val DodgerBlueBackground = Color(0xFF8D97FC)
val DodgerBlueDesgrade = Color(0x606C79FD)
val Scarlet = Color(0xFFFF1818)
val ScarletDegrade = Color(0xA1FF3F3F)
val ScarletDegradeBackground = Color(0xA1FF7676)
val Amber = Color(0xFFD8842C)
val AmberDesgrade = Color(0xD7CEA071)
val Gallery = Color(0xFFECECEC)
val SoftPeach = Color(0xFFF9F5F6)
val AquaSqueeze = Color(0xFFE3F4F4)
val Nepal = Color(0xFF96B6C5)



fun generateDarkColor(): Color {
    val red = Random.nextInt(0, 128)
    val green = Random.nextInt(0, 128)
    val blue = Random.nextInt(0, 128)
    return Color(red, green, blue)
}

fun generateLightColor(): Color {
    val red = Random.nextInt(128, 256)
    val green = Random.nextInt(128, 256)
    val blue = Random.nextInt(128, 256)
    return Color(red, green, blue)
}