package gg.lol.android.network

import java.io.IOException

class NetworkException(code: Int, message: String) : IOException(message)