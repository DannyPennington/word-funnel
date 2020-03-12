import Main.funnel

class Tests extends UnitSpec{

  "Funnel function" should "return the length of the longest funnel it starts" in {
    assert(funnel("gnash") == 4)
    assert(funnel("princesses") == 9)
    assert(funnel("turntables") == 5)
    assert(funnel("implosive") == 1)
    assert(funnel("programmer") == 2)
  }


}
