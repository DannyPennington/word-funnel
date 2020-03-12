import scala.io.Source

object Main extends App
{

  def readFile(): Array[String] =
  {
    Source.fromFile("/home/qa-admin/Downloads/dictionary.txt").getLines.toArray
  }

  val words = readFile()

  def funnel( word :String, index :Int=0 ) :Int =
  {
    val chars = word.toCharArray
    var depth = 1
    //var charsHold :Array[Char] = Array.emptyCharArray
    //var holdLength = 0

    for( i <- 0 until words.length by 1 )
    {
      var run = true
      if( words(i).equals(word) )
      {
        //for( i <- 0 until chars.length by 1 )
        var j = 0
        while( run )
        {
          var charsHold = chars.clone()
          charsHold(j) = ' '
          val tempWord = charsHold.mkString.replaceAll("\\s", "")
          for( k <- 0 until words.length by 1 )
          {
            if( words(k).equals(tempWord) )
            {
              depth = depth + funnel(tempWord)
              run = false
            }
          }

          if( j >= chars.length-1 ){ run = false }
          else
          {
            j = j + 1
          }
        }
      }
    }

    return depth
  }

  println( funnel("princesses") )
}
