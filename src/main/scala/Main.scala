import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Main extends App
{
  val function :Function = new Function()

  println( function.start("gnash") )
  println( function.start("princesses") )
  println( function.start("turntables") )
  println( function.start("implosive") )
  println( function.start("programmer") )

  //println( start("implosive") )
  //println( funnel("implosive", true) )
}


class Function()
{
  val words :Array[String] = readFile()
  var wordExclusion :ArrayBuffer[String] = ArrayBuffer.empty[String]

  def readFile(): Array[String] =
  {
    Source.fromFile("/home/qa-admin/Downloads/dictionary.txt").getLines.toArray
  }

  def funnel( word :String, exclusion :Boolean) :Int =
  {
    val chars = word.toCharArray
    var depth = 1

    var run = true
    if( words.contains(word) )
    {
      var j = 0
      while( run )
      {
        var charsHold = chars.clone()
        charsHold(j) = ' '
        val tempWord = charsHold.mkString.replaceAll("\\s", "")
        if( words.contains(tempWord) && !wordExclusion.contains(tempWord) )
        {
          depth = depth + funnel(tempWord, exclusion)
          if( exclusion ){ wordExclusion += tempWord }
          run = false
        }

        if( j >= chars.length-1 ){ run = false }
        else{ j = j + 1 }
      }
    }

    depth
  }

  def start( word :String ) :Int =
  {
    funnel(word, true)
    var largest = 0
    var depth = 0
    wordExclusion = ArrayBuffer.empty[String]

    depth = funnel(word, true)
    for( i <- 0 until wordExclusion.length by 1 )
    {
      depth = funnel( wordExclusion(i), false ) + i+1

      if( depth > largest )
      {
        largest = depth
      }
    }
    if( depth > largest )
    {
      largest = depth
    }

    largest
  }
}