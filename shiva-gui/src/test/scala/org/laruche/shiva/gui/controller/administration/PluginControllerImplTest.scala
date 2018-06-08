package org.laruche.shiva.gui.controller.administration

import javafx.embed.swing.JFXPanel
import javafx.scene.layout.Pane
import org.assertj.core.api.Assertions.assertThat
import org.junit.{Before, Test}
import org.laruche.shiva.core.plugin.Plugin
import org.laruche.shiva.gui.ShivaApplication
import org.laruche.shiva.gui.controller.administration.PluginControllerImplTest.mockPlugin
import org.mockito.Mockito.{mock, when}
import scalafxml.core.{DependenciesByType, FXMLLoader}

import scala.reflect.runtime.universe._

object PluginControllerImplTest {


  def mockPlugin(name: String): Plugin = {
    val mockedPlugin: Plugin = mock(classOf[Plugin])
    when(mockedPlugin.name).thenReturn(name)
    return mockedPlugin
  }
}

class PluginControllerImplTest {
  private var shivaApplication: ShivaApplication = new ShivaApplication

  @Before
  def before(): Unit = {
    new JFXPanel()
  }

  @Test
  def test_mockedPlugin(): Unit = {
    val plugin: Plugin = mockPlugin("plugin")
    assertThat(plugin.name).isEqualTo("plugin")
    assertThat(plugin.id).isNull()
  }

  @Test
  def test_getController(): Unit = {
    val loader: FXMLLoader = new FXMLLoader(this.getClass.getResource("/fxml/administration/PluginsGUI.fxml"), new DependenciesByType(Map(typeOf[ShivaApplication] -> shivaApplication)))
    assertThat(loader).isNotNull
    val mainPane: Pane = loader.load()
    assertThat(loader.getController() != null).isTrue
  }

  @Test
  def test_pluginModel(): Unit = {
    val plugin = new Plugin {
      override protected var desc: String = "Test"
      override protected val theId: String = "pluginId"
      this.name = "pluginName"
    }
    val pluginModel: PluginModel = new PluginModel(plugin)
    assertThat(pluginModel.name).isEqualTo("pluginName")
    pluginModel.name = "otherName"
    assertThat(pluginModel.name).isEqualTo("otherName")
  }

}
