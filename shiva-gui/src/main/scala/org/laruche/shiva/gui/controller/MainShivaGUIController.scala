package org.laruche.shiva.gui.controller

import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.AnchorPane.{setBottomAnchor, setLeftAnchor, setRightAnchor, setTopAnchor}
import javafx.scene.layout.{AnchorPane, Pane}
import org.laruche.shiva.gui.ShivaApplication
import org.laruche.shiva.gui.component.HasMainPane
import org.slf4j.{Logger, LoggerFactory}
import scalafx.Includes._
import scalafxml.core.macros.sfxml
import scalafxml.core.{DependenciesByType, FXMLLoader}

import scala.reflect.runtime.universe._

///// Interface

/**
  * Trait permettant d'interagir avec le controller
  *
  * @author Frédéric Moulé
  */
trait MainShivaGuiController extends HasMainPane
  with ShivaController {
  protected val LOG: Logger = LoggerFactory.getLogger(classOf[MainShivaGuiController])
}

///// Implémentation

//noinspection VarCouldBeVal
@sfxml
class MainShivaGUIControllerImpl(shivaAppli: ShivaApplication,
                                 theMainPane: AnchorPane) extends MainShivaGuiController {

  /// Variables :
  private var _theMainPane: javafx.scene.layout.AnchorPane = theMainPane
  override protected var _shivaApplication: ShivaApplication = shivaAppli

  /// Constructeur et/ou init
  this._shivaApplication.startPlugins()

  /// Gestion des évènement :

  def onExit(): Unit = {
    LOG.info("==== Arrêt de l'application")
    if (this.mainPane == null) {
      LOG.info("mainPane is null")
    }
    clearChildren()
    System.exit(0)
  }

  def onListPlugins(): Unit = {
    LOG.debug("List the plugins")
    val loader: FXMLLoader = new FXMLLoader(this.getClass.getResource("/fxml/administration/PluginsGUI.fxml"), new DependenciesByType(Map(typeOf[ShivaApplication] -> shivaAppli)))
    val pluginPane: Pane = loader.load()
    setTopAnchor(pluginPane, 0d)
    setBottomAnchor(pluginPane, 0d)
    setLeftAnchor(pluginPane, 0d)
    setRightAnchor(pluginPane, 0d)
    this._theMainPane.children = pluginPane
  }

  ///// Méthodes générales :

  def clearChildren(): Unit = {
    val children: ObservableList[Node] = this._theMainPane.children
    if (children != null) {
      children.forEach {
        case pane: AutoCloseable => pane.close();
        case _ => return
      }
    }
    this.mainPane.children = List()
  }

  ///// Getters & Setters :

  /**
    * Retourne l'espace principale
    *
    * @return espace principal
    */
  override def mainPane: Pane = this._theMainPane
}