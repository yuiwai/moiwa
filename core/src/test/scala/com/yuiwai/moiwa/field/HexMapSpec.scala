package com.yuiwai.moiwa.field

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.chaining._

class HexMapSpec extends AnyFlatSpec with Matchers {
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
  it should "有効なインデックスに対するPointerを返す" in {
    HexMap(2, 2)(i2hex).tap { m =>
      m.point(0) shouldBe Some(Pointer(m, 0))
      m.point(1) shouldBe Some(Pointer(m, 1))
    }
  }

  it should "無効なインデックスに対するPointerを返さない" in {
    HexMap(2, 2)(i2hex).tap { m =>
      m.point(-1) shouldBe None
      m.point(5) shouldBe None
    }
  }
  it should "有効な座標に対するPointerを返す" in {
    HexMap(2, 2)(i2hex).tap { m =>
      m.point(1, 1) shouldBe Some(Pointer(m, 0))
      m.point(2, 1) shouldBe Some(Pointer(m, 1))
      m.point(1, 2) shouldBe Some(Pointer(m, 2))
      m.point(2, 2) shouldBe Some(Pointer(m, 3))
    }
  }
  it should "無効な座標に対するPointerを返さない" in {
    HexMap(2, 2)(i2hex).tap { m =>
      m.point(0, 0) shouldBe None
      m.point(0, 1) shouldBe None
      m.point(1, 0) shouldBe None
      m.point(2, 3) shouldBe None
      m.point(3, 2) shouldBe None
      m.point(3, 3) shouldBe None
    }
  }
}

class PointerTest extends AnyFlatSpec with Matchers {
  val i2hex: Int => Hex[Int] = i => Hex(i, i)
  "Pointerは" should "indexに対応した座標を返す" in {
    HexMap(2, 2)(i2hex).tap { m =>
      m.point(0).get.pos shouldBe ((1, 1))
      m.point(1).get.pos shouldBe ((2, 1))
      m.point(2).get.pos shouldBe ((1, 2))
      m.point(3).get.pos shouldBe ((2, 2))
    }
  }
  it should "指定方向の隣接座標があればPointerとして返す" in {
    // 偶数列
    HexMap(3, 3)(i2hex).point(4).get.tap { p =>
      p.up shouldBe Some(Pointer(p.hexMap, 1))
      p.down shouldBe Some(Pointer(p.hexMap, 7))
      p.upLeft shouldBe Some(Pointer(p.hexMap, 3))
      p.upRight shouldBe Some(Pointer(p.hexMap, 5))
      p.downLeft shouldBe Some(Pointer(p.hexMap, 6))
      p.downRight shouldBe Some(Pointer(p.hexMap, 8))

      p.up.get.down shouldBe Some(p)
      p.down.get.up shouldBe Some(p)
      p.upLeft.get.downRight shouldBe Some(p)
      p.upRight.get.downLeft shouldBe Some(p)
      p.downLeft.get.upRight shouldBe Some(p)
      p.downRight.get.upLeft shouldBe Some(p)
    }
    // 奇数列
    HexMap(4, 4)(i2hex).point(6).get.tap { p =>
      p.up shouldBe Some(Pointer(p.hexMap, 2))
      p.down shouldBe Some(Pointer(p.hexMap, 10))
      p.upLeft shouldBe Some(Pointer(p.hexMap, 1))
      p.upRight shouldBe Some(Pointer(p.hexMap, 3))
      p.downLeft shouldBe Some(Pointer(p.hexMap, 5))
      p.downRight shouldBe Some(Pointer(p.hexMap, 7))

      p.up.get.down shouldBe Some(p)
      p.down.get.up shouldBe Some(p)
      p.upLeft.get.downRight shouldBe Some(p)
      p.upRight.get.downLeft shouldBe Some(p)
      p.downLeft.get.upRight shouldBe Some(p)
      p.downRight.get.upLeft shouldBe Some(p)
    }
  }

  it should "指定方向の隣接座標がなければ結果を返さない" in {
    HexMap(1, 1)(i2hex).point(0).get.tap { p =>
      p.up shouldBe None
      p.down shouldBe None
      p.upLeft shouldBe None
      p.upRight shouldBe None
      p.downLeft shouldBe None
      p.downRight shouldBe None
    }
  }
}
