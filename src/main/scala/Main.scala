import scala.io.Source

object Main extends App
{

  def readFile(): Array[String] =
  {
    Source.fromFile("/home/qa-admin/Downloads/dictionary.txt").getLines.toArray
  }

  val words = readFile()

  def funnel(word: String): Int =
  {
    val chars = word.toCharArray
    var charsHold :Array[Char] = Array.emptyCharArray
    var holdLength = 0
    //var holdWord = ""

    for (i <- 0 until chars.length by 1)
    {
      charsHold = chars.clone()
      charsHold(i) = ' '
      val tempWord = charsHold.mkString.replaceAll("\\s", "")
      var length = 0
      var count = 0
      var found = true
      while( found && !(count >= words.length) )
      {
        if (words(count).equals(tempWord) )
        {
          length = words(count).length
          //holdWord = tempWord

          found = false
        }
        count = count +1
      }
      if( length > holdLength)
      {
        holdLength = length
      }
    }

    return holdLength
  }

  def funnel2( word :String ) :Int =
  {
    var end = true
    var word2 = word

    while( end )
    {
      var length = funnel(word2)
      var longestLength = funnel(word2)

      if (length > longestLength)
      {
        longestLength = length
      }
    }

    return 0
  }

  def funnel3( word :String, index :Int=0 ) :Int =
  {
    val chars = word.toCharArray
    var depth = 1
    //var charsHold :Array[Char] = Array.emptyCharArray
    //var holdLength = 0

    if( words.contains(word) )
    {
      for (i <- 0 until chars.length by 1)
      {
        var charsHold = chars.clone()
        charsHold(i) = ' '
        val tempWord = charsHold.mkString.replaceAll("\\s", "")
        if( words.contains(tempWord) )
        {
          depth = depth + funnel3(tempWord)
        }
      }
    }

    return depth
  }

  //println( funnel("gnash") )
  //println( funnel2("turntables") )
  println( funnel3("gnash") )
  //println( "gnash".slice(1, 60) )
}
