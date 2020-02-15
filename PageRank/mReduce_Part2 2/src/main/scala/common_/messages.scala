package common_

case class Word(word: String, title: String)
case class Book(word: String, title: String)
case class Capitalized(word: String, title: String)
case class WordCount(word: String, count: Int)
case class Simple_Content(url: String)
case class incomingHyperLinks(link: String, url: String)
case object Flush
case object Done
