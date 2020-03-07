package com.yuiwai.moiwa.field

final class HexMap[T] private(val width: Int, private[field] val hexes: Seq[Hex[T]])
object HexMap {
  def apply[T](width: Int, hexes: Seq[Hex[T]]): Option[HexMap[T]] =
    if (hexes.size % width == 0) Some(new HexMap(width, hexes))
    else None
  def apply[T](width: Int, height: Int)(gen: Int => Hex[T]): HexMap[T] =
    new HexMap(width, Seq.tabulate(width * height)(gen))
}

final case class Pointer[T](hexMap: HexMap[T], index: Int)

final case class Hex[T](index: Int, data: T)
