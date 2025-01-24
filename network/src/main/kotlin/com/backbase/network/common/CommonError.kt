package com.backbase.network.common

import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isConnectionError() = this is SocketException || this is UnknownHostException || this is SocketTimeoutException
