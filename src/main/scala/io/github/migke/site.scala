package github.io.migke.site

import scala.collection.mutable.ArrayBuffer
import github.io.migke.site.types.*
import github.io.migke.site.element.Element
import github.io.migke.site.attribute.AttributeMap
import github.io.migke.site.element.FullElement
import github.io.migke.site.element.VoidElement
import github.io.migke.site.element.render
import github.io.migke.site.attribute.Attr
import scala.compiletime.constValue
import github.io.migke.site.element.RawString
import github.io.migke.site.element.EscapedText
import github.io.migke.site.css.*

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

// ------------
// Text
// ------------

def raw[T <: TagType](text: String)(using
    builder: HTMLBuilder
) = {
  val e = RawString(text)
  builder.add(e.asInstanceOf[Element])
  e
}
def text[T <: TagType](text: String)(using
    builder: HTMLBuilder
) = {
  val e = EscapedText(text)
  builder.add(e.asInstanceOf[Element])
  e
}

// ------------
// HTML Elements
// ------------
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
)(using HTMLBuilder): FullElement[HTML] =
  tag(constValue[HTML].asInstanceOf[HTML], attrs, body)

def head(attrs: AttributeMap[Head] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Head] =
  tag(constValue[Head].asInstanceOf[Head], attrs, body)

def title(attrs: AttributeMap[Title] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Title] =
  tag(constValue[Title].asInstanceOf[Title], attrs, body)

def meta(attrs: AttributeMap[Meta] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Meta] =
  tag(constValue[Meta].asInstanceOf[Meta], attrs)

def link(attrs: AttributeMap[Link] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Link] =
  tag(constValue[Link].asInstanceOf[Link], attrs)

def script(attrs: AttributeMap[Script] = AttributeMap())(
    body: String
)(using HTMLBuilder): FullElement[Script] =
  tag(
    constValue[Script].asInstanceOf[Script],
    attrs, {
      raw(body)
    }
  )

def base(attrs: AttributeMap[Base] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Base] =
  tag(constValue[Base].asInstanceOf[Base], attrs)

def style(attrs: AttributeMap[Style] = AttributeMap())(
    body: CSSRuleGroup ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Style] =
  val group = CSSRuleGroup({})
  body(using group)
  tag(
    constValue[Style].asInstanceOf[Style],
    attrs, {
      raw(group.render)
    }
  )
// Sectioning
def body(attrs: AttributeMap[Body] = AttributeMap())(
    bodyFn: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Body] =
  tag(constValue[Body].asInstanceOf[Body], attrs, bodyFn)

def header(attrs: AttributeMap[Header] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Header] =
  tag(constValue[Header].asInstanceOf[Header], attrs, body)

def footer(attrs: AttributeMap[Footer] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Footer] =
  tag(constValue[Footer].asInstanceOf[Footer], attrs, body)

def main(attrs: AttributeMap[Main] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Main] =
  tag(constValue[Main].asInstanceOf[Main], attrs, body)

def section(attrs: AttributeMap[Section] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Section] =
  tag(constValue[Section].asInstanceOf[Section], attrs, body)

def article(attrs: AttributeMap[Article] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Article] =
  tag(constValue[Article].asInstanceOf[Article], attrs, body)

def nav(attrs: AttributeMap[Nav] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Nav] =
  tag(constValue[Nav].asInstanceOf[Nav], attrs, body)

def aside(attrs: AttributeMap[Aside] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Aside] =
  tag(constValue[Aside].asInstanceOf[Aside], attrs, body)

def h1(attrs: AttributeMap[H1] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[H1] =
  tag(constValue[H1].asInstanceOf[H1], attrs, body)

def h2(attrs: AttributeMap[H2] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[H2] =
  tag(constValue[H2].asInstanceOf[H2], attrs, body)

def h3(attrs: AttributeMap[H3] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[H3] =
  tag(constValue[H3].asInstanceOf[H3], attrs, body)

def h4(attrs: AttributeMap[H4] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[H4] =
  tag(constValue[H4].asInstanceOf[H4], attrs, body)

def h5(attrs: AttributeMap[H5] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[H5] =
  tag(constValue[H5].asInstanceOf[H5], attrs, body)

def h6(attrs: AttributeMap[H6] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[H6] =
  tag(constValue[H6].asInstanceOf[H6], attrs, body)
def ol(attrs: AttributeMap[Ol] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Ol] =
  tag(constValue[Ol].asInstanceOf[Ol], attrs, body)

def dl(attrs: AttributeMap[Dl] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Dl] =
  tag(constValue[Dl].asInstanceOf[Dl], attrs, body)

def dt(attrs: AttributeMap[Dt] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Dt] =
  tag(constValue[Dt].asInstanceOf[Dt], attrs, body)

def dd(attrs: AttributeMap[Dd] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Dd] =
  tag(constValue[Dd].asInstanceOf[Dd], attrs, body)

def figure(attrs: AttributeMap[Figure] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Figure] =
  tag(constValue[Figure].asInstanceOf[Figure], attrs, body)

def figcaption(attrs: AttributeMap[Figcaption] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Figcaption] =
  tag(constValue[Figcaption].asInstanceOf[Figcaption], attrs, body)

def pre(attrs: AttributeMap[Pre] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Pre] =
  tag(constValue[Pre].asInstanceOf[Pre], attrs, body)

def blockquote(attrs: AttributeMap[Blockquote] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Blockquote] =
  tag(constValue[Blockquote].asInstanceOf[Blockquote], attrs, body)
// grouping content
def p(attrs: AttributeMap[P] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[P] =
  tag(constValue[P].asInstanceOf[P], attrs, body)

def hr(attrs: AttributeMap[Hr] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Hr] =
  tag(constValue[Hr].asInstanceOf[Hr], attrs)

def div(attrs: AttributeMap[Div] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Div] =
  tag(constValue[Div].asInstanceOf[Div], attrs, body)

def ul(attrs: AttributeMap[Ul] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Ul] =
  tag(constValue[Ul].asInstanceOf[Ul], attrs, body)

def li(attrs: AttributeMap[Li] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Li] =
  tag(constValue[Li].asInstanceOf[Li], attrs, body)

// Text level semantics
def a(attrs: AttributeMap[A] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[A] =
  tag(constValue[A].asInstanceOf[A], attrs, body)

def span(attrs: AttributeMap[Span] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Span] =
  tag(constValue[Span].asInstanceOf[Span], attrs, body)

def br(attrs: AttributeMap[Br] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Br] =
  tag(constValue[Br].asInstanceOf[Br], attrs)
// embedded
def img(attrs: AttributeMap[Img] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Img] =
  tag(constValue[Img].asInstanceOf[Img], attrs)

def iframe(attrs: AttributeMap[Iframe] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Iframe] =
  tag(constValue[Iframe].asInstanceOf[Iframe], attrs, body)

def video(attrs: AttributeMap[Video] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Video] =
  tag(constValue[Video].asInstanceOf[Video], attrs, body)

// forms
def form(attrs: AttributeMap[Form] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Form] =
  tag(constValue[Form].asInstanceOf[Form], attrs, body)

def label(attrs: AttributeMap[Label] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Label] =
  tag(constValue[Label].asInstanceOf[Label], attrs, body)

def input(attrs: AttributeMap[Input] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Input] =
  tag(constValue[Input].asInstanceOf[Input], attrs)

def button(attrs: AttributeMap[Button] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Button] =
  tag(constValue[Button].asInstanceOf[Button], attrs, body)

def textarea(attrs: AttributeMap[Textarea] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Textarea] =
  tag(constValue[Textarea].asInstanceOf[Textarea], attrs, body)
// Tables
def table(attrs: AttributeMap[Table] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Table] =
  tag(constValue[Table].asInstanceOf[Table], attrs, body)

def caption(attrs: AttributeMap[Caption] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Caption] =
  tag(constValue[Caption].asInstanceOf[Caption], attrs, body)

def thead(attrs: AttributeMap[Thead] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Thead] =
  tag(constValue[Thead].asInstanceOf[Thead], attrs, body)

def tbody(attrs: AttributeMap[Tbody] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Tbody] =
  tag(constValue[Tbody].asInstanceOf[Tbody], attrs, body)

def tfoot(attrs: AttributeMap[Tfoot] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Tfoot] =
  tag(constValue[Tfoot].asInstanceOf[Tfoot], attrs, body)

def tr(attrs: AttributeMap[Tr] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Tr] =
  tag(constValue[Tr].asInstanceOf[Tr], attrs, body)

def th(attrs: AttributeMap[Th] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Th] =
  tag(constValue[Th].asInstanceOf[Th], attrs, body)
// interactive
def details(attrs: AttributeMap[Details] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Details] =
  tag(constValue[Details].asInstanceOf[Details], attrs, body)

def summary(attrs: AttributeMap[Summary] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Summary] =
  tag(constValue[Summary].asInstanceOf[Summary], attrs, body)

def dialog(attrs: AttributeMap[Dialog] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Dialog] =
  tag(constValue[Dialog].asInstanceOf[Dialog], attrs, body)

def td(attrs: AttributeMap[Td] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Td] =
  tag(constValue[Td].asInstanceOf[Td], attrs, body)
// text level semantics
def strong(attrs: AttributeMap[Strong] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Strong] =
  tag(constValue[Strong].asInstanceOf[Strong], attrs, body)

def em(attrs: AttributeMap[Em] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Em] =
  tag(constValue[Em].asInstanceOf[Em], attrs, body)

def code(attrs: AttributeMap[Code] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Code] =
  tag(constValue[Code].asInstanceOf[Code], attrs, body)

def small(attrs: AttributeMap[Small] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Small] =
  tag(constValue[Small].asInstanceOf[Small], attrs, body)

def sub(attrs: AttributeMap[Sub] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Sub] =
  tag(constValue[Sub].asInstanceOf[Sub], attrs, body)

def sup(attrs: AttributeMap[Sup] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Sup] =
  tag(constValue[Sup].asInstanceOf[Sup], attrs, body)

def i(attrs: AttributeMap[I] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[I] =
  tag(constValue[I].asInstanceOf[I], attrs, body)

def b(attrs: AttributeMap[B] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[B] =
  tag(constValue[B].asInstanceOf[B], attrs, body)
// ------------
// attrs
// ------------

// custom
def attr[T <: TagType](name: String): Attr[T] = Attr(name, true)

// global
def id[T <: TagType]: Attr[T] = Attr("id", true)
def cls[T <: TagType]: Attr[T] = Attr("class", true)
def style[T <: TagType]: Attr[T] = Attr("style", true)
def title[T <: TagType]: Attr[T] = Attr("title", true)
def lang[T <: TagType]: Attr[T] = Attr("lang", true)
def hidden[T <: TagType]: Attr[T] = Attr("hidden", false)
def tabindex[T <: TagType]: Attr[T] = Attr("tabindex", true)
def draggable[T <: TagType]: Attr[T] = Attr("draggable", true)
def contenteditable[T <: TagType]: Attr[T] = Attr("contenteditable", true)
def spellcheck[T <: TagType]: Attr[T] = Attr("spellcheck", true)

// source
def src[T <: Script | Img | Audio | Video | Iframe | Embed | Source]: Attr[T] =
  Attr("src", true)

def href[T <: A | Link | Base]: Attr[T] =
  Attr("href", true)

def action[T <: Form]: Attr[T] = Attr("action", true)
def poster[T <: Video]: Attr[T] = Attr("poster", true)
def data[T <: Object]: Attr[T] = Attr("data", true)

// metadata
def charset[T <: Meta]: Attr[T] = Attr("charset", true)
def name[T <: Meta | Input | Button | Select | Textarea | Form]: Attr[T] =
  Attr("name", true)
def content[T <: Meta]: Attr[T] = Attr("content", true)
def rel[T <: Link | A]: Attr[T] = Attr("rel", true)
def media[T <: Style | Link | Source]: Attr[T] = Attr("media", true)

// forms
def select(attrs: AttributeMap[Select] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Select] =
  tag(constValue[Select].asInstanceOf[Select], attrs, body)

def option(attrs: AttributeMap[Option] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Option] =
  tag(constValue[Option].asInstanceOf[Option], attrs, body)

def optgroup(attrs: AttributeMap[Optgroup] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Optgroup] =
  tag(constValue[Optgroup].asInstanceOf[Optgroup], attrs, body)

def fieldset(attrs: AttributeMap[Fieldset] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Fieldset] =
  tag(constValue[Fieldset].asInstanceOf[Fieldset], attrs, body)

def legend(attrs: AttributeMap[Legend] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Legend] =
  tag(constValue[Legend].asInstanceOf[Legend], attrs, body)

def audio(attrs: AttributeMap[Audio] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Audio] =
  tag(constValue[Audio].asInstanceOf[Audio], attrs, body)

def source(attrs: AttributeMap[Source] = AttributeMap())(using
    HTMLBuilder
): VoidElement[Source] =
  tag(constValue[Source].asInstanceOf[Source], attrs)

def canvas(attrs: AttributeMap[Canvas] = AttributeMap())(
    body: HTMLBuilder ?=> Unit = { x ?=> }
)(using HTMLBuilder): FullElement[Canvas] =
  tag(constValue[Canvas].asInstanceOf[Canvas], attrs, body)
def value[T <: Input | Option | Button | Textarea]: Attr[T] =
  Attr("value", true)

def `type`[T <: Input | Button]: Attr[T] =
  Attr("type", true)

def placeholder[T <: Input | Textarea]: Attr[T] =
  Attr("placeholder", true)

def checked[T <: Input]: Attr[T] = Attr("checked", false)
def disabled[T <: Input | Button | Select | Option | Textarea | Fieldset]
    : Attr[T] = Attr("disabled", false)

def readonly[T <: Input | Textarea]: Attr[T] = Attr("readonly", false)
def required[T <: Input | Select | Textarea]: Attr[T] = Attr("required", false)
def multiple[T <: Input | Select]: Attr[T] = Attr("multiple", false)
def maxlength[T <: Input | Textarea]: Attr[T] = Attr("maxlength", true)
def minlength[T <: Input | Textarea]: Attr[T] = Attr("minlength", true)

def forId[T <: Label]: Attr[T] = Attr("for", true)

// Tables
def colspan[T <: Td | Th]: Attr[T] = Attr("colspan", true)
def rowspan[T <: Td | Th]: Attr[T] = Attr("rowspan", true)
def scope[T <: Th]: Attr[T] = Attr("scope", true)

// media
def autoplay[T <: Audio | Video]: Attr[T] = Attr("autoplay", false)
def controls[T <: Audio | Video]: Attr[T] = Attr("controls", false)
def loop[T <: Audio | Video]: Attr[T] = Attr("loop", false)
def muted[T <: Audio | Video]: Attr[T] = Attr("muted", false)

def preload[T <: Audio | Video]: Attr[T] = Attr("preload", true)

// image
def alt[T <: Img]: Attr[T] = Attr("alt", true)
def width[T <: Img | Video | Canvas | Iframe]: Attr[T] = Attr("width", true)
def height[T <: Img | Video | Canvas | Iframe]: Attr[T] = Attr("height", true)
def loading[T <: Img | Iframe]: Attr[T] = Attr("loading", true)

// data
def dataAttr[T <: TagType](name: String): Attr[T] =
  Attr(s"data-$name", true)

def aria[T <: TagType](name: String): Attr[T] =
  Attr(s"aria-$name", true)

def role[T <: TagType]: Attr[T] = Attr("role", true)
