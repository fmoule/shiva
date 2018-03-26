package org.laruche.shiva.core.plugin.log

import org.assertj.core.api.Assertions.assertThat
import org.scalatest.FunSuite

class LogPluginTest extends FunSuite {

  test("Plugin id") {
    val plugin = new LogPlugin("logPlugin")
    assertThat(plugin.id).isEqualTo("logPlugin")
    assert(plugin.id == "logPlugin")
  }

  test("test the name") {
    val plugin: LogPlugin = new LogPlugin("logPlugin")
    assertThat(plugin.name).isEqualTo("logPlugin")
  }
}
