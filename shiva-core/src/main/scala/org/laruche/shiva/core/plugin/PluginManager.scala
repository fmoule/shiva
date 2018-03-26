package org.laruche.shiva.core.plugin

/**
  * <p>
  * Trait décrivant toutes les fonctionnalités
  * d'un gestionnaire de plugins.
  * </p>
  *
  * @author Frédéric Moulé
  */
trait PluginManager extends Plugin
  with Iterable[Plugin] {

  ///// Fonctions générales :

  /**
    * Retourne la liste des plugins.
    *
    * @return liste des plugins
    */
  def plugins: Seq[Plugin]

  def plugins_=(plugins: Seq[Plugin]): Unit = this.findPlugin(plugin => true)

  /**
    * Méthode permettant de trouver les plugins vérifiant
    * le prédicat passé en paramètre. <br />
    *
    * @param predicate :  prédicat jouant le rôle de critère.
    * @return "Liste" des plugins trouvés
    */
  def findPlugin(predicate: Plugin => Boolean): Seq[Plugin]

  ///// Définitions des opérateurs :

  /**
    * Ajoute une liste de plugins
    *
    * @param plugins : plugins
    */
  def +=(plugins: Plugin*): PluginManager

  /**
    * Permet de supprimer une liste de plugin
    * au gestionnaire.
    *
    * @param plugins : Liste de plugins à supprimer
    */
  def -=(plugins: Plugin*): PluginManager

}
