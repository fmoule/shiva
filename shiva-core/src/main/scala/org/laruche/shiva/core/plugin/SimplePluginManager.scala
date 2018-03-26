package org.laruche.shiva.core.plugin

import scala.collection.mutable

/**
  * Simple gestionnaire de plugins.
  *
  * @author Frédéric Moulé
  */
class SimplePluginManager(id: String = "", theDesc: String = "") extends PluginManager {
  override protected val theId: String = id
  override protected var desc: String = theDesc
  private val pluginList: mutable.Set[Plugin] = new mutable.HashSet[Plugin]()

  ///// Méthode du trait Plugin

  /**
    * Démarrre le plugin. <br />
    *
    * @throws PluginException : En cas de problème
    */
  override def start(): Unit = {
    super.start()
  }

  /**
    * Arrête le plugin
    *
    * @throws PluginException : En cas de problème
    */
  override def stop(): Unit = {
    super.stop()
    this.pluginList.clear()
  }

  //// Méthode du trait Iterable

  override def iterator: Iterator[Plugin] = pluginList.iterator

  //// Méthodes du trait PluginManager :

  override def plugins: Seq[Plugin] = pluginList.toList

  override def findPlugin(predicate: Plugin => Boolean): Seq[Plugin] = {
    if (predicate == null || pluginList.isEmpty) {
      return List()
    }
    return pluginList.toStream.find(predicate).toList
  }

  ///// Opérateurs :

  override def +=(plugins: Plugin*): PluginManager = {
    for (plugin <- plugins) {
      pluginList += plugin
    }
    return this
  }

  override def -=(plugins: Plugin*): PluginManager = {
    for (plugin <- plugins) {
      pluginList -= plugin
    }
    return this
  }
}

/**
  * Objet compagnon
  *
  * @author Frédéric Moulé
  */
object SimplePluginManager {

  def apply(id: String, plugins: Plugin*): SimplePluginManager = {
    val pluginManager = new SimplePluginManager(id)
    pluginManager.plugins = plugins
    return pluginManager
  }

  def unapply(pluginManager: SimplePluginManager): Option[(String, Seq[Plugin])] = {
    if (pluginManager == null) {
      return None
    }
    return Some(pluginManager.id, pluginManager.plugins)
  }
}