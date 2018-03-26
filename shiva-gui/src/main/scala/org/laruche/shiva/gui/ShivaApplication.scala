package org.laruche.shiva.gui

import org.laruche.shiva.core.plugin.log.LogPlugin
import org.laruche.shiva.core.plugin.{PluginManager, SimplePluginManager}

import scala.reflect.runtime.universe._
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView}

class ShivaApplication {
  private var _pluginManager: PluginManager = new SimplePluginManager("pluginManager")

  /// Constructeur :

  this.pluginManager += new LogPlugin("logPlugin")

  ///// Méthodes :

  /**
    * Retourne le gestionnaire de plugins
    *
    * @return gestionnaire de plugins
    */
  def pluginManager: PluginManager = this._pluginManager
  def pluginManager_=(pluginManager: PluginManager): Unit = this._pluginManager = pluginManager

}

/**
  * Classe permettant de démarrer l'application graphique
  *
  * @author Frédéric Moulé
  */
object ShivaApplication extends JFXApp {
  private val TITLE = "Shiva"
  private val DEPENDENCIES: Map[Type, Any] = Map(typeOf[ShivaApplication] -> new ShivaApplication)

  stage = new JFXApp.PrimaryStage {
    title = TITLE
    scene = new Scene(FXMLView(getClass.getResource("/fxml/MainShivaGUI.fxml"), new DependenciesByType(DEPENDENCIES)))
  }
}
