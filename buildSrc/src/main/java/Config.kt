object Config {

    object Android {
        const val MINSDK = 29
        const val TARGETSDK = 33
        const val COMPILESDK = 33
    }

    object Release {
        const val APPLICATION_ID = "gg.lol.android"
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
        const val BASEURL = "https://790b-218-51-73-161.jp.ngrok.io/"
    }

    object Debug {
        const val BASEURL = "https://790b-218-51-73-161.jp.ngrok.io/"
    }

    object Staging {
        const val BASEURL = "https://790b-218-51-73-161.jp.ngrok.io/"
    }

    object ComposeOption {
        const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.4.2"
    }
}