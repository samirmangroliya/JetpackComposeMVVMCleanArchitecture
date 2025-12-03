package com.samir.jetpackcomposemvvmclean.network

sealed class NetworkStatus {
    object Connected : NetworkStatus()
    object Disconnected : NetworkStatus()
}