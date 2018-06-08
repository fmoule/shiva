package org.laruche.shiva.gui.controller.administration

import javafx.beans.{property => jxbp}
import javafx.scene.layout.Pane
import org.laruche.shiva.core.plugin.{Plugin, PluginManager}
import org.laruche.shiva.gui.ShivaApplication
import org.laruche.shiva.gui.component.HasMainPane
import org.laruche.shiva.gui.controller.ShivaController
import org.slf4j.{Logger, LoggerFactory}
import scalafx.beans.property.{BooleanProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.TableView
import scalafxml.core.macros.sfxml


/**
  * Bean utilisé en tant que modèle.
  */
class PluginModel(plugin: Plugin) {
  private val _nameProperty: StringProperty = new StringProperty(plugin, "name")
  private val _descProperty: StringProperty = new StringProperty(plugin, "description")
  private val _isActivatedProperty: BooleanProperty = new BooleanProperty(plugin, "isActive")

  // Constructeur
  this.name = if (plugin == null) "" else plugin.name
  this.description = if (plugin == null) "" else plugin.description
  this.activated = if (plugin == null) false else plugin.started

  // Getters & Setters :

  final def nameProperty: jxbp.StringProperty = this._nameProperty
  final def name: String = this._nameProperty.get()
  final def name_=(name: String): Unit = this._nameProperty.set(name)
  final def descriptionProperty: jxbp.StringProperty = this._descProperty
  final def description: String = this._descProperty.get()
  final def description_=(desc: String): Unit = this._descProperty.set(desc)
  final def isActivatedProperty: jxbp.BooleanProperty = this._isActivatedProperty
  final def activated: Boolean = this._isActivatedProperty.get()
  final def activated_=(isActivated: Boolean): Unit = this._isActivatedProperty.set(isActivated)
}

/**
  * <p>
  * Trait représentant le controller et servant de façade à l'implémentation
  * ci-dessous. <br />
  * </p>
  */
trait PluginController extends ShivaController with HasMainPane {
  protected val LOG: Logger = LoggerFactory.getLogger(classOf[PluginController])
}

/////////////////////
///// Implémentations
/////////////////////

@sfxml
class PluginControllerImpl(shivaAppli: ShivaApplication,
                           theMainPane: Pane,
                           pluginTable: TableView[PluginModel]) extends PluginController {

  override protected var _shivaApplication: ShivaApplication = shivaAppli
  private val _mainPane: Pane = theMainPane
  private val _pluginTable: TableView[PluginModel] = pluginTable

  ///// Initialisation :
  require(_shivaApplication != null, "Le ShivaApplication ne doit pas être nul")
  require(_mainPane != null)
  require(_pluginTable != null, "La table de plugin ne doit pas être nulle")
  this.init()

  ///// Gestion évenementielle

  def onQuit(): Unit = {
    LOG.info("")
    val pane: Pane = this._mainPane.getParent.asInstanceOf[Pane]
    pane.getChildren.remove(this._mainPane)
  }

  ///// Méthodes générales :

  /**
    * Initialisation de l'écran.
    */
  private def init(): Unit = {
    val pluginManager: PluginManager = _shivaApplication.pluginManager
    val pluginModels = new ObservableBuffer[PluginModel]()
    pluginModels ++= pluginManager.plugins.toStream.map(plugin => new PluginModel(plugin)).toList
    LOG.debug("== Chargement des plugins")
    for (plugin <- pluginModels) {
      LOG.debug("Plugin " + plugin.description)
    }
    _pluginTable.items = pluginModels
  }

  // Accesseurs :

  /**
    * Retourne l'espace principal
    *
    * @return
    */
  override def mainPane: Pane = this._mainPane
}
