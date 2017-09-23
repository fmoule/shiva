package org.laruche.shiva.core.plugin

import org.assertj.core.api.Assertions.assertThat
import org.junit.{After, Before, Test}
import org.laruche.shiva.core.plugin.PluginManagerTest.TestPlugin
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable.ArrayBuffer

class PluginManagerTest {
    val pluginManager: SimplePluginManager = new SimplePluginManager

    @Before
    def before(): Unit = {
        pluginManager.start()
    }

    @After
    def after(): Unit = {
        pluginManager.stop()
    }

    @Test
    def test_arrayBuffer(): Unit = {
        val buffer = new ArrayBuffer[String]
        buffer += ("str1","str2")
        assertThat(buffer.size).isEqualTo(2)
    }

    @Test
    def test_simplePluginManager(): Unit = {
        var plugins: Seq[Plugin] = pluginManager.plugins
        assertThat(plugins).isNotNull
        assertThat(plugins.isEmpty).isTrue
        plugins = pluginManager.findPlugin(null)
        assertThat(plugins).isNotNull
        assertThat(plugins.isEmpty).isTrue
    }

    @Test
    def test_addPlugin(): Unit = {
        pluginManager += (new TestPlugin("1"), new TestPlugin("2"))
        assertThat(pluginManager.size).isEqualTo(2)
        val plugins: Seq[Plugin] = pluginManager.findPlugin(plugin => plugin.id == "1")
        assertThat(plugins).isNotNull
        assertThat(plugins.size).isEqualTo(1)
    }

    @Test
    def test_addPlugin_withList(): Unit = {
        pluginManager += (List(TestPlugin("1"), TestPlugin("2")):_*)
        assertThat(pluginManager.size).isEqualTo(2)
    }
}

object PluginManagerTest {
    private val LOGGER: Logger = LoggerFactory.getLogger(classOf[PluginManagerTest])

    /**
      * Classe de test implémentant le trait Plugin
      *
      * @param id      : identifiant du plugin
      * @param theDesc : Description du plugin
      */
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


    object TestPlugin {
        def apply(id: String = "", theDesc: String = ""): TestPlugin = new TestPlugin(id, theDesc)
    }

}
