package com.example.tasktylium.model

data class Data(
    val status: String,
    val _ip: String,
    val _ips: List<String>,
    val instruments: Map<String, Instruments>,
    val prices: Map<String, Prices>
)
