package com.fx.mvvm.data.responses

data class TokenResponse(
    val code: Int,
    val `data`: Data,
    val msg: String
)