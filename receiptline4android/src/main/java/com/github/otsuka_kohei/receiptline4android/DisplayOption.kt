package com.github.otsuka_kohei.receiptline4android

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisplayOption(
    @SerialName("cpl")
    val charactersPerLine: Int = 48,

    @SerialName("encoding")
    val encoding: String = "multilingual",

    @SerialName("spacing")
    val spacing: Boolean = false
)
