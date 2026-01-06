package github.io.migke.site.attribute

import github.io.migke.site.types.TagType
import github.io.migke.site.utils.escape

case class Attr[T <: TagType](
    name: String,
    value: Any,
    escape: Boolean = true
) {
  def :=(value: Any) = this.copy[T](value = value)
}
class AttributeMap[T <: TagType](attributes: Attr[T]*) {
  private val attrs: Map[String, Attr[T]] =
    attributes.foldLeft(Map[String, Attr[T]]())((x, y) => x + (y.name -> y))
  inline def +=(inline attrs: Seq[Attr[T]] | AttributeMap[T]) = AttributeMap(
    (attrs match {
      case y: AttributeMap[T] => y.attrs.values.toSeq
      case x: Seq[Attr[T]]    => x
    }) ++ this.attrs.values.toSeq*
  )
  def get(name: String) = attrs.get(name)
  def render: String = attributes
    .foldLeft(StringBuilder())((x, y) =>
      x.append(" ")
        .append(if (y.escape) escape(y.name) else y.name)
        .append(y.value match {
          case value: String =>
            s"=\"${
                if (y.escape) escape(value)
                else value
              }\""
          case value: Boolean => ""
          case value: Int     => s"=${value}"
          case _              =>
            s"${
                if (y.escape) escape(y.value.toString())
                else y.value.toString()
              }"
        })
    )
    .toString()
}
