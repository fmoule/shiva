package org.laruche.shiva.core.plugin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.laruche.shiva.core.plugin.PluginManagerTest.TestPlugin
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.immutable

class PluginManagerTest {

  @Test
  def test_simplePluginManager(): Unit = {
    val pluginManager: SimplePluginManager = new SimplePluginManager
    var plugins: Seq[Plugin] = pluginManager.plugins
    assertThat(plugins).isNotNull
    assertThat(plugins.isEmpty).isTrue
    plugins = pluginManager.findPlugin(null)
    assertThat(plugins).isNotNull
    assertThat(plugins.isEmpty).isTrue
  }

  def test_addPlugin(): Unit = {
    val pluginManager: SimplePluginManager = new SimplePluginManager
    pluginManager += (new TestPlugin("1"), new TestPlugin("2"))
    val plugins: Seq[Plugin] = pluginManager.findPlugin(plugin => plugin.id == "1")
    assertThat(plugins).isNotNull
    assertThat(plugins.size).isEqualTo(1)
  }

}

object PluginManagerTest {
  private val LOGGER: Logger = LoggerFactory.getLogger(classOf[PluginManagerTest])

  class TestPlugin(id: String = "", theDesc: String = "") extends Plugin {
    override protected val theId: String = id
    override protected var desc: String = theDesc

    ///// Méthode de la classe Object

    override def hashCode(): Int = id.hashCode

    override def equals(obj: scala.Any): Boolean = {
      obj match {
        case testPlugin: TestPlugin => this.id == testPlugin.id
        case _ => return false
      }
    }

    ///// Méthode du trait Plugin

    /**
      * Démarrre le plugin. <br />
      *
      * @throws PluginException : En cas de problème
      */
    override def start(): Unit = {
      LOGGER.info("")
    }

    /**
      * Arrête le plugin
      *
      * @throws PluginException : En cas de problème
      */
    override def stop(): Unit = {
      LOGGER.info("")
    }
  }

}
