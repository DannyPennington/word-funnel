import Main.function

class Tests extends UnitSpec{

  "Funnel function" should "return the length of the longest funnel it starts" in {
    val function :Function = new Function()

    assert(function.start("gnash") == 4)
    assert(function.start("princesses") == 9)
    assert(function.start("turntables") == 5)
    assert(function.start("implosive") == 1)
    assert(function.start("programmer") == 2)
  }


}
