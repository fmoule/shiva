package org.laruche.shiva.core.plugin.log

import org.laruche.shiva.core.plugin.{Plugin, PluginException}

class LogPlugin(id: String) extends Plugin {
  override protected val theId = id
  override protected var desc = "Login Plugin"
  override def name: String = id

  /**
    * Démarrre le plugin. <br />
    *
    * @throws PluginException : En cas de problème
    */
  override def start(): Unit = super.start()

  /**
    * Arrête le plugin
    *
    * @throws PluginException : En cas de problème
    */
  override def stop(): Unit = super.stop()
}
