import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Main extends App
{

  def readFile(): Array[String] =
  {
    Source.fromFile("/home/qa-admin/Downloads/dictionary.txt").getLines.toArray
  }

  val words = readFile()
  val wordExclusion = ArrayBuffer.empty[String]

  def funnel( word :String, index :Int=0 ) :Int =
  {
    val chars = word.toCharArray
    var depth = 1
    //var charsHold :Array[Char] = Array.emptyCharArray
    //var holdLength = 0

    var run = true
    if( words.contains(word) )
    {
      //for( i <- 0 until chars.length by 1 )
      var j = 0
      while( run )
      {
        var charsHold = chars.clone()
        charsHold(j) = ' '
        val tempWord = charsHold.mkString.replaceAll("\\s", "")
        if( words.contains(tempWord) )
        {
          depth = depth + funnel(tempWord)
          wordExclusion += tempWord
          run = false
        }

        if( j >= chars.length-1 ){ run = false }
        else{ j = j + 1 }
      }
    }

    return depth
  }

  def start( word :String ) :Int =
  {
    var largest = 0
    for( i <- 0 until word.length by 1 )
    {
      val depth = funnel(word)

      if( depth > largest )
      {
        largest = depth
      }
    }

    return largest
  }

  println( start("princesses") )
}
