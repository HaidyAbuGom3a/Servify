package org.haidy.servify.app.utils

import com.facebook.CallbackManager
import org.haidy.servify.app.MainActivity

object CallbackManagerProvider {
    val callbackManager = CallbackManager.Factory.create()
    lateinit var activity: MainActivity
}
