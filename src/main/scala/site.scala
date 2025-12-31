package migke.site

import scala.collection.mutable.ArrayBuffer
import migke.site.types.*
import migke.site.element.Element
import migke.site.attribute.AttributeMap
import migke.site.element.FullElement
import migke.site.element.VoidElement
import migke.site.element.render
import migke.site.attribute.Attr
import scala.compiletime.constValue

given seqToMap[T <: TagType]: Conversion[Seq[Attr[T]], AttributeMap[T]] with {
  def apply(seq: Seq[Attr[T]]): AttributeMap[T] = AttributeMap(seq*)
}

class HTMLBuilder(body: HTMLBuilder ?=> Unit) {
  private val elements: ArrayBuffer[Element] = ArrayBuffer()
  body(using this)
  def add(element: Element) = elements.addOne(element)
  def render =
    elements
      .foldLeft(StringBuilder())((x, y) => x.append(y.render))
      .toString()
  def get = elements.toArray
}

def tag[T <: TagType](`type`: T, attrs: AttributeMap[T])(using
    builder: HTMLBuilder
) = {
  val e = VoidElement(`type`, attrs)
  builder.add(e.asInstanceOf[Element])
  e
}
def tag[T <: TagType](
    `type`: T,
    attrs: AttributeMap[T],
    body: HTMLBuilder ?=> Unit
)(using
    builder: HTMLBuilder
) = {
  val e = {
    val builder = HTMLBuilder({ x ?=> })
    body(using builder)
    FullElement(VoidElement(`type`, attrs), builder.get)
  }
  builder.add(e.asInstanceOf[Element])
  e
}

def html(attrs: AttributeMap[HTML] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using
    HTMLBuilder
): FullElement[HTML] =
  tag(constValue[HTML].asInstanceOf[HTML], attrs, body)
def input(attrs: AttributeMap[Input] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Input] =
  tag(constValue[Input].asInstanceOf[Input], attrs)

def `type` = Attr[Input]("type", true, true)
