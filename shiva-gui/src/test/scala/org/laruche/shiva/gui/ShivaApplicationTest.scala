package org.laruche.shiva.gui

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.laruche.shiva.core.plugin.SimplePluginManager

class ShivaApplicationTest {


  @Test
  def test_appli(): Unit = {
    val application = new ShivaApplication
    application.pluginManager = new SimplePluginManager("pluginManager")
    assertThat(application.pluginManager.id).isEqualTo("pluginManager")
  }

}
