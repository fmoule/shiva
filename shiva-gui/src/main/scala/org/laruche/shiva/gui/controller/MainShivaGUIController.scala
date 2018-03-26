package org.laruche.shiva.gui.controller

import javafx.scene.layout.Pane

import org.laruche.shiva.gui.ShivaApplication
import org.laruche.shiva.gui.component.HasMainPane
import org.slf4j.{Logger, LoggerFactory}

import scala.reflect.runtime.universe._
import scalafx.Includes._
import scalafxml.core.macros.sfxml
import scalafxml.core.{DependenciesByType, FXMLLoader}

///// Interface

/**
  * Trait permettant d'interagir avec le controller
  *
  * @author Frédéric Moulé
  */
trait IMainShivaGuiController extends HasMainPane
  with ShivaController {
  protected val LOG: Logger = LoggerFactory.getLogger(classOf[MainShivaGUIController])
}

///// Implémentation

//noinspection VarCouldBeVal
@sfxml
class MainShivaGUIController(shivaAppli: ShivaApplication,
                             theMainPane: Pane) extends IMainShivaGuiController {

  /// Variables :

  private var _theMainPane: javafx.scene.layout.Pane = theMainPane
  override protected var _shivaApplication: ShivaApplication = shivaAppli

  /// Gestion des évènement :

  def onExit(): Unit = {
    LOG.info("==== Arrêt de l'application")
    if (this.mainPane == null) {
      LOG.info("mainPane is null")
    }
    System.exit(0)
  }

  def onListPlugins(): Unit = {
    LOG.debug("List the plugins")
    val loader: FXMLLoader = new FXMLLoader(this.getClass.getResource("/fxml/plugins/PluginsGUI.fxml"), new DependenciesByType(Map(typeOf[ShivaApplication] -> shivaAppli)))
    val pluginPane: Pane = loader.load()
    pluginPane.setPrefWidth(this._theMainPane.getWidth)
    pluginPane.setPrefHeight(this._theMainPane.getHeight)
    this._theMainPane.children = pluginPane
  }

  ///// Getters & Setters :

  /**
    * Retourne l'espace principale
    *
    * @return espace principal
    */
  override def mainPane: Pane = this._theMainPane
}