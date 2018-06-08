package org.laruche.shiva.gui.controller

import javafx.embed.swing.JFXPanel
import org.assertj.core.api.Assertions.assertThat
import org.junit.{Before, Test}
import org.laruche.shiva.gui.ShivaApplication
import scalafxml.core.{DependenciesByType, FXMLLoader}

import scala.reflect.runtime.universe._

class MainShivaGuiControllerTest {

  private var application: ShivaApplication = new ShivaApplication

  @Before
  def before(): Unit = {
//    application
    // Init du moteur
    new JFXPanel
  }

  @Test
  def test_defaultProperties(): Unit = {
    val loader: FXMLLoader = new FXMLLoader(classOf[MainShivaGuiControllerTest].getResource("/fxml/MainShivaGUI.fxml"), new DependenciesByType(Map(typeOf[ShivaApplication] -> application )))
    loader.load()
    val mainShivaController: MainShivaGuiController = loader.getController()
    assertThat(mainShivaController).isNotNull
    assertThat(mainShivaController.mainPane).isNotNull
  }

}