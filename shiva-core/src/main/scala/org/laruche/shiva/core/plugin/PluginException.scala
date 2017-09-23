package org.laruche.shiva.core.plugin

/**
  * Exception emise par les différents plugins
  *
  * @author Frédéric Moulé
  */
class PluginException(message: String = "",
                      throwable: Throwable = null)
  extends Exception(message, throwable) {
  // EMPTY
}
