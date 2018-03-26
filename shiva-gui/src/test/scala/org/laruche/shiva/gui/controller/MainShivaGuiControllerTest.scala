package org.laruche.shiva.gui.controller

import javafx.embed.swing.JFXPanel

import org.assertj.core.api.Assertions.assertThat
import org.junit.{Before, Test}

import scalafxml.core.{FXMLLoader, NoDependencyResolver}

class MainShivaGuiControllerTest {

  @Before
  def before(): Unit = {
    new JFXPanel
  }

  @Test
  def test_defaultProperties() : Unit = {
    val loader: FXMLLoader = new FXMLLoader(classOf[MainShivaGuiControllerTest].getResource("/fxml/MainShivaGUI.fxml"), NoDependencyResolver)
    loader.load()
    val mainShivaController: IMainShivaGuiController = loader.getController()
    assertThat(mainShivaController).isNotNull
    assertThat(mainShivaController.mainPane).isNotNull
  }

}