package org.laruche.shiva.core.plugin

import scala.collection.mutable.ArrayBuffer

/**
  * <p>
  * Trait décrivant toutes les fonctionnalités
  * d'un gestionnaire de plugins.
  * </p>
  *
  * @author Frédéric Moulé
  */
trait PluginManager extends Plugin {

    ///// Fonctions générales :

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
      * Retourne le nombre de plugin
      *
      * @return nombre de plugins
      */
    def size: Int = plugins.size


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

///// Simple implémentation

/**
  * Simple gestionnaire de plugins.
  *
  * @author Frédéric Moulé
  */
class SimplePluginManager(id: String = "", theDesc: String = "") extends PluginManager {
    override protected val theId: String = id
    override protected var desc: String = theDesc
    private val pluginList: ArrayBuffer[Plugin] = new ArrayBuffer[Plugin]

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

