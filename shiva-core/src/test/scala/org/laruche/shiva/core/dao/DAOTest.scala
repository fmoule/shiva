package org.laruche.shiva.core.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.{After, Test}

import scala.collection.mutable.ListBuffer

class DAOTest {
  var setDao: SetDao[String] = new SetDao[String]

  @After
  def after(): Unit = {
    setDao.clear()
  }

  @Test
  def test_setDao_add(): Unit = {
    setDao.insert("fred", "matthieu", "arnaud")
    var strings = setDao.select()
    assertThat(strings.size).isEqualTo(3)
    strings = setDao.select(str => str.startsWith("fr"))
    assertThat(strings.size).isEqualTo(1)
  }

  @Test
  def test_setDao_clear(): Unit = {
    setDao.values = List("1", "2", "3")
    var strings: Seq[String] = setDao.select()
    assertThat(strings.isEmpty).isFalse
    assertThat(strings.size).isEqualTo(3)
    setDao.clear()
    strings = setDao.select()
    assertThat(strings).isNotNull
    assertThat(strings.isEmpty).isTrue
  }

  @Test
  def test_setDao_iterator(): Unit = {
    setDao.values = List("1", "2", "3", "4")
    val buffer : ListBuffer[String] = ListBuffer()
    for (bean <- setDao) {
      buffer += bean
    }
    val list = buffer.toList
    assertThat(list.size).isEqualTo(4)
    for (i <- 1 to 4) {
      assertThat(list.contains(i.toString)).isTrue
    }
  }

}
