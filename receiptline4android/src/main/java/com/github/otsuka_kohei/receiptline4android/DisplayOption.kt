package com.github.otsuka_kohei.receiptline4android

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Display option of receiptline
 *
 * @property charactersPerLine characters per line (default: 48)
 * @property encoding default: multilingual (including cp437, cp852, cp858, cp866, cp1252)
 * @property spacing default: no line spacing
 */
@Serializable
data class DisplayOption(
    @SerialName("cpl")
    val charactersPerLine: Int = 48,

    @SerialName("encoding")
    val encoding: String = "multilingual",

    @SerialName("spacing")
    val spacing: Boolean = false
)
