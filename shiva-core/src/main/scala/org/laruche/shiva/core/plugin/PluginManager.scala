package org.laruche.shiva.core.plugin

import scala.collection.immutable._
import scala.collection.mutable.ArrayBuffer

/**
  * <p>
  * Trait décrivant toutes les fonctionnalités
  * d'un gestionnaire de plugins.
  * </p>
  *
  * @author Frédéric Moulé
  */
trait PluginManager {

  ///// Fonctions de recherches :

  /**
    * Retourne la liste des plugins.
    *
    * @return liste des plugins
    */
  def plugins: Seq[Plugin]

  /**
    * Méthode permettant de trouver les plugins vérifiant
    * le prédicat passé en paramètre. <br />
    *
    * @param predicate :  prédicat jouant le rôle de critère.
    * @return "Liste" des plugins trouvés
    */
  def findPlugin(predicate: Plugin => Boolean): Seq[Plugin]

  /**
    * Ajoute une liste de plugins
    *
    * @param plugins : plugins
    */
  def +=(plugins: Seq[Plugin]): Unit

  def +=(plugins: Plugin*): Unit = {
    this += plugins.toList
  }
}


///// Simple implémentation

class SimplePluginManager extends PluginManager {
  private val pluginList: collection.mutable.ArrayBuffer[Plugin] = new ArrayBuffer[Plugin]

  //// Méthodes du trait PluginManager :

  override def plugins: Seq[Plugin] = pluginList.toList

  override def findPlugin(predicate: (Plugin) => Boolean): Seq[Plugin] = {
    if (predicate == null || pluginList.isEmpty) {
      return List()
    }
    return pluginList.toStream.find(predicate).toList
  }

  /**
    * Ajoute une liste de plugins
    *
    * @param plugins : plugins
    */
  override def +=(plugins: Seq[Plugin]): Unit = {
    for (plugin <- pluginList ) {
      pluginList += plugin
    }
  }
}

