package org.laruche.shiva.core.plugin

import org.laruche.shiva.commons.beans.{HasDescription, HasId}

/**
  * Trait représentant les plugins. <br />
  *
  * @author Frédéric Moulé
  */
trait Plugin extends HasId
  with HasDescription {
    private var isStarted: Boolean = false

    /**
      * Démarrre le plugin. <br />
      *
      * @throws PluginException : En cas de problème
      */
    @throws(classOf[PluginException])
    def start(): Unit = {
        this.isStarted = true
    }

    /**
      * Arrête le plugin
      *
      * @throws PluginException : En cas de problème
      */
    @throws(classOf[PluginException])
    def stop(): Unit = {
        this.isStarted = false
    }

    /**
      * Retourne un booléen montrant si
      * plugin est démarré.
      *
      * @return booléen
      */
    def started: Boolean = this.isStarted

}
