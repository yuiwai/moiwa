package com.yuiwai.moiwa.field

import org.scalatest.{FlatSpec, Matchers}

class HexMapSpec extends FlatSpec with Matchers {
  val i2hex: Int => Hex[Int] = i => Hex(i, i)
  "HexMapは" should "縦横のサイズに応じた数のHexを持つ" in {
    HexMap(5, 5)(i2hex).hexes.size shouldBe 25
    HexMap(2, 3)(i2hex).hexes.size shouldBe 6
  }
  it should "地形リストが横幅で割り切れる場合生成できる" in {
    val Some(hm) = HexMap(2, Seq.tabulate(4)(i2hex))
    hm.width shouldBe 2
    hm.hexes shouldBe Seq.tabulate(4)(i2hex)
  }
  it should "地形リストを横幅で割り切れなかったら生成失敗" in {
    HexMap(2, Seq.tabulate(3)(i2hex)) shouldBe None
  }
}
