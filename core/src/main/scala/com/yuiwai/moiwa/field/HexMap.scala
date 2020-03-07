package com.yuiwai.moiwa.field

final class HexMap[T] private(val width: Int, val hexes: Seq[Hex[T]]) {
  def point(index: Int): Option[Pointer[T]] =
    if (index < 0 || index >= hexes.size) None
    else Some(Pointer(this, index))
  def point(x: Int, y: Int): Option[Pointer[T]] =
    if (x <= 0 || y <= 0) None
    else point(x - 1 + (y - 1) * width)
}
object HexMap {
  def apply[T](width: Int, hexes: Seq[Hex[T]]): Option[HexMap[T]] =
    if (hexes.size % width == 0) Some(new HexMap(width, hexes))
    else None
  def apply[T](width: Int, height: Int)(gen: Int => Hex[T]): HexMap[T] =
    new HexMap(width, Seq.tabulate(width * height)(gen))
}

final case class Pointer[T](hexMap: HexMap[T], index: Int) {
  private def ifEvenX(isEvenY: Int, isOddY: Int): Int =
    if (x % 2 == 0) isEvenY else isOddY
  lazy val pos: (Int, Int) = (index % hexMap.width + 1, index / hexMap.width + 1)
  def x: Int = pos._1
  def y: Int = pos._2
  def up: Option[Pointer[T]] = hexMap.point(x, y - 1)
  def down: Option[Pointer[T]] = hexMap.point(x, y + 1)
  def upLeft: Option[Pointer[T]] = hexMap.point(x - 1, ifEvenX(y, y - 1))
  def upRight: Option[Pointer[T]] = hexMap.point(x + 1, ifEvenX(y, y - 1))
  def downLeft: Option[Pointer[T]] = hexMap.point(x - 1, ifEvenX(y + 1, y))
  def downRight: Option[Pointer[T]] = hexMap.point(x + 1, ifEvenX(y + 1, y))
}

final case class Hex[T](index: Int, data: T)
