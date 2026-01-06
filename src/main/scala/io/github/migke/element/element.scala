package github.io.migke.site.element

import github.io.migke.site.types.TagType
import github.io.migke.site.utils.escape
import github.io.migke.site.attribute.AttributeMap
import github.io.migke.site.HTMLBuilder

type Element = RawString | VoidElement[TagType] | FullElement[TagType] |
  EscapedText

extension (e: Element) {
  def render = e match {
    case x: RawString      => x.render
    case x: VoidElement[t] => x.render
    case x: FullElement[t] => x.render
    case x: EscapedText    => x.render
  }
}
case class RawString(str: String) {
  inline def render = str
}
case class EscapedText(str: String) {
  inline def render = escape(str)
}
case class FullElement[T <: TagType](
    element: VoidElement[T],
    children: Array[Element]
) {
  def render: String =
    s"${element.render}${children.foldLeft(StringBuilder())((x, y) => x.append(y.render)).toString()}${s"</${element.`type`}>"}"
}
case class VoidElement[T <: TagType](`type`: T, attrs: AttributeMap[T]) {
  def render = s"<${`type`.toString()}${attrs.render}>"
}
