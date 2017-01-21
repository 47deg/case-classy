/* -
 * Case Classy [classy-tests]
 */

package classy
package generic

import org.scalacheck._
import org.scalacheck.Prop._

import derive._

class StringSplitterTests extends Properties("StringSplitter") {

  case class Entry(input: String, split: List[String])
  object Entry {
    def apply(input: String, split: String*): Entry = Entry(input, split.toList)
  }

  val entries: List[Entry] = List(
    Entry("helloWorld", "hello", "World"),
    Entry("FooBarBaz", "Foo", "Bar", "Baz"),
    Entry("ThisISSparta", "This", "IS", "Sparta"),
    Entry("Math15Cool", "Math15", "Cool"),
    Entry("what_is_love", "what", "is", "love"),
    Entry("WHAT_IS_LOVE", "WHAT", "IS", "LOVE"),
    Entry("ZIP__ZAP", "ZIP", "ZAP"),
    Entry("zip__ZAP", "zip", "ZAP"),
    Entry("ZIP__zap", "ZIP", "zap"),
    Entry("zip__zap", "zip", "zap"),
    Entry("_fleeb_er", "fleeb", "er"),
    Entry("__fleeb_er", "fleeb", "er"),
    Entry("a100_B200", "a100", "B200"),
    Entry("a1B2c3D4e5F6G7", "a1", "B2", "c3", "D4", "e5", "F6G7"),
    Entry("222Hello", "222", "Hello"),
    Entry("222hello", "222hello"),
    Entry("Hello222world", "Hello222world"),
    Entry("Hello222World", "Hello222", "World")
  )

  entries.foreach { entry =>
    StringSplitter.split(entry.input)
  }

  property("StringSplitter.split") =
    entries
      .map(entry => StringSplitter.split(entry.input) ?= entry.split)
      .reduce(_ && _)

  property("StringSplitter.split does't throw exceptions on arbitrary strings") =
    forAll { (input: String) =>
      StringSplitter.split(input)
      true
    }

}
