package org.laruche.shiva.core.plugin

import org.laruche.shiva.commons.beans.{HasDescription, HasId}

/**
  * Trait représentant les plugins. <br />
  *
  * @author Frédéric Moulé
  */
trait Plugin extends HasId
             with HasDescription {

  /**
    * Démarrre le plugin. <br />
    *
    * @throws PluginException : En cas de problème
    */
  @throws(classOf[PluginException])
  def start() : Unit

  /**
    * Arrête le plugin
    *
    * @throws PluginException : En cas de problème
    */
  @throws(classOf[PluginException])
  def stop() : Unit
}
