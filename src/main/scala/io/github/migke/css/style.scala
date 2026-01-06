package github.io.migke.site.css

import scala.collection.mutable
import github.io.migke.site.value
import scala.collection.mutable.ArrayBuffer

class CSSRuleGroup(rules: CSSRuleGroup ?=> Unit) {
  val rulesList = ArrayBuffer[String]()
  def +=(rule: String) = rulesList.addOne(rule)
  def render = rulesList.mkString("")
}
class CSSStyle {
  private val source: mutable.Map[String, String] = mutable.Map.empty
  def render =
    source.map(pair => s"${pair._1}:${pair._2}").mkString("", ";", ";")

  def set(prop: String, value: String): Unit = source.update(prop, value)
}

// --- 1. BOX MODEL (Margins, Padding, Dimensions) ---

def rule(p: String, v: String)(using style: CSSStyle) = style.set(p, v)
def width(v: String)(using style: CSSStyle) = style.set("width", v)
def height(v: String)(using style: CSSStyle) = style.set("height", v)
def minWidth(v: String)(using style: CSSStyle) = style.set("min-width", v)
def maxWidth(v: String)(using style: CSSStyle) = style.set("max-width", v)
def minHeight(v: String)(using style: CSSStyle) = style.set("min-height", v)
def maxHeight(v: String)(using style: CSSStyle) = style.set("max-height", v)

def margin(v: String)(using style: CSSStyle) = style.set("margin", v)
def marginTop(v: String)(using style: CSSStyle) = style.set("margin-top", v)
def marginRight(v: String)(using style: CSSStyle) = style.set("margin-right", v)
def marginBottom(v: String)(using style: CSSStyle) =
  style.set("margin-bottom", v)
def marginLeft(v: String)(using style: CSSStyle) = style.set("margin-left", v)

def padding(v: String)(using style: CSSStyle) = style.set("padding", v)
def paddingTop(v: String)(using style: CSSStyle) = style.set("padding-top", v)
def paddingRight(v: String)(using style: CSSStyle) =
  style.set("padding-right", v)
def paddingBottom(v: String)(using style: CSSStyle) =
  style.set("padding-bottom", v)
def paddingLeft(v: String)(using style: CSSStyle) = style.set("padding-left", v)

def boxSizing(v: String)(using style: CSSStyle) =
  style.set("box-sizing", v) // e.g., "border-box"
def aspectRatio(v: String)(using style: CSSStyle) = style.set("aspect-ratio", v)

// --- 2. TYPOGRAPHY & TEXT ---
def color(v: String)(using style: CSSStyle) = style.set("color", v)
def fontFamily(v: String)(using style: CSSStyle) = style.set("font-family", v)
def fontSize(v: String)(using style: CSSStyle) = style.set("font-size", v)
def fontWeight(v: String)(using style: CSSStyle) = style.set("font-weight", v)
def fontStyle(v: String)(using style: CSSStyle) = style.set("font-style", v)
def lineHeight(v: String)(using style: CSSStyle) = style.set("line-height", v)
def textAlign(v: String)(using style: CSSStyle) = style.set("text-align", v)
def textDecoration(v: String)(using style: CSSStyle) =
  style.set("text-decoration", v)
def textTransform(v: String)(using style: CSSStyle) =
  style.set("text-transform", v)
def textOverflow(v: String)(using style: CSSStyle) =
  style.set("text-overflow", v)
def letterSpacing(v: String)(using style: CSSStyle) =
  style.set("letter-spacing", v)
def wordSpacing(v: String)(using style: CSSStyle) = style.set("word-spacing", v)
def whiteSpace(v: String)(using style: CSSStyle) = style.set("white-space", v)
def verticalAlign(v: String)(using style: CSSStyle) =
  style.set("vertical-align", v)
def wordBreak(v: String)(using style: CSSStyle) = style.set("word-break", v)
def textShadow(v: String)(using style: CSSStyle) = style.set("text-shadow", v)

// --- 3. LAYOUT (Display, Flex, Grid) ---
def display(v: String)(using style: CSSStyle) = style.set("display", v)
def flex(v: String)(using style: CSSStyle) = style.set("flex", v)
def flexDirection(v: String)(using style: CSSStyle) =
  style.set("flex-direction", v)
def flexWrap(v: String)(using style: CSSStyle) = style.set("flex-wrap", v)
def flexGrow(v: String)(using style: CSSStyle) = style.set("flex-grow", v)
def flexShrink(v: String)(using style: CSSStyle) = style.set("flex-shrink", v)
def flexBasis(v: String)(using style: CSSStyle) = style.set("flex-basis", v)
def justifyContent(v: String)(using style: CSSStyle) =
  style.set("justify-content", v)
def alignItems(v: String)(using style: CSSStyle) = style.set("align-items", v)
def alignContent(v: String)(using style: CSSStyle) =
  style.set("align-content", v)
def alignSelf(v: String)(using style: CSSStyle) = style.set("align-self", v)
def order(v: String)(using style: CSSStyle) = style.set("order", v)

def gap(v: String)(using style: CSSStyle) = style.set("gap", v)
def rowGap(v: String)(using style: CSSStyle) = style.set("row-gap", v)
def columnGap(v: String)(using style: CSSStyle) = style.set("column-gap", v)

def gridTemplateColumns(v: String)(using style: CSSStyle) =
  style.set("grid-template-columns", v)
def gridTemplateRows(v: String)(using style: CSSStyle) =
  style.set("grid-template-rows", v)
def gridColumn(v: String)(using style: CSSStyle) = style.set("grid-column", v)
def gridRow(v: String)(using style: CSSStyle) = style.set("grid-row", v)
def gridArea(v: String)(using style: CSSStyle) = style.set("grid-area", v)

// --- 4. POSITIONING ---
def position(v: String)(using style: CSSStyle) = style.set("position", v)
def top(v: String)(using style: CSSStyle) = style.set("top", v)
def right(v: String)(using style: CSSStyle) = style.set("right", v)
def bottom(v: String)(using style: CSSStyle) = style.set("bottom", v)
def left(v: String)(using style: CSSStyle) = style.set("left", v)
def zIndex(v: String)(using style: CSSStyle) = style.set("z-index", v)
def float(v: String)(using style: CSSStyle) = style.set("float", v)
def clear(v: String)(using style: CSSStyle) = style.set("clear", v)
def overflow(v: String)(using style: CSSStyle) = style.set("overflow", v)
def overflowX(v: String)(using style: CSSStyle) = style.set("overflow-x", v)
def overflowY(v: String)(using style: CSSStyle) = style.set("overflow-y", v)
def visibility(v: String)(using style: CSSStyle) = style.set("visibility", v)

// --- 5. BACKGROUNDS & BORDERS ---
def backgroundColor(v: String)(using style: CSSStyle) =
  style.set("background-color", v)
def backgroundImage(v: String)(using style: CSSStyle) =
  style.set("background-image", v)
def backgroundSize(v: String)(using style: CSSStyle) =
  style.set("background-size", v)
def backgroundPosition(v: String)(using style: CSSStyle) =
  style.set("background-position", v)
def backgroundRepeat(v: String)(using style: CSSStyle) =
  style.set("background-repeat", v)
def backgroundAttachment(v: String)(using style: CSSStyle) =
  style.set("background-attachment", v)

def border(v: String)(using style: CSSStyle) = style.set("border", v)
def borderTop(v: String)(using style: CSSStyle) = style.set("border-top", v)
def borderRight(v: String)(using style: CSSStyle) = style.set("border-right", v)
def borderBottom(v: String)(using style: CSSStyle) =
  style.set("border-bottom", v)
def borderLeft(v: String)(using style: CSSStyle) = style.set("border-left", v)
def borderRadius(v: String)(using style: CSSStyle) =
  style.set("border-radius", v)
def borderColor(v: String)(using style: CSSStyle) = style.set("border-color", v)
def borderStyle(v: String)(using style: CSSStyle) = style.set("border-style", v)
def borderWidth(v: String)(using style: CSSStyle) = style.set("border-width", v)
def outline(v: String)(using style: CSSStyle) = style.set("outline", v)

// --- 6. EFFECTS & TRANSITIONS ---
def opacity(v: String)(using style: CSSStyle) = style.set("opacity", v)
def boxShadow(v: String)(using style: CSSStyle) = style.set("box-shadow", v)
def cursor(v: String)(using style: CSSStyle) = style.set("cursor", v)
def transition(v: String)(using style: CSSStyle) = style.set("transition", v)
def transitionDuration(v: String)(using style: CSSStyle) =
  style.set("transition-duration", v)
def transitionProperty(v: String)(using style: CSSStyle) =
  style.set("transition-property", v)
def transform(v: String)(using style: CSSStyle) = style.set("transform", v)
def transformOrigin(v: String)(using style: CSSStyle) =
  style.set("transform-origin", v)
def animation(v: String)(using style: CSSStyle) = style.set("animation", v)
def filter(v: String)(using style: CSSStyle) = style.set("filter", v)
def backdropFilter(v: String)(using style: CSSStyle) =
  style.set("backdrop-filter", v)

// --- 7. MISC & UI ---
def userSelect(v: String)(using style: CSSStyle) = style.set("user-select", v)
def pointerEvents(v: String)(using style: CSSStyle) =
  style.set("pointer-events", v)
def listStyle(v: String)(using style: CSSStyle) = style.set("list-style", v)
def appearance(v: String)(using style: CSSStyle) = style.set("appearance", v)
def objectFit(v: String)(using style: CSSStyle) = style.set("object-fit", v)
def objectPosition(v: String)(using style: CSSStyle) =
  style.set("object-position", v)
def content(v: String)(using style: CSSStyle) = style.set("content", v)
def resize(v: String)(using style: CSSStyle) = style.set("resize", v)
def scrollBehavior(v: String)(using style: CSSStyle) =
  style.set("scroll-behavior", v)

extension [T <: Int | Double](t: T) {
  // --- 1. ABSOLUTE LENGTHS ---
  def px: String = s"${t}px" // Pixels
  def cm: String = s"${t}cm" // Centimeters
  def mm: String = s"${t}mm" // Millimeters
  def in: String = s"${t}in" // Inches
  def pt: String = s"${t}pt" // Points
  def pc: String = s"${t}pc" // Picas

  // --- 2. RELATIVE LENGTHS (FONT) ---
  def em: String = s"${t}em" // Relative to parent font size
  def rem: String = s"${t}rem" // Relative to root font size
  def ex: String = s"${t}ex" // x-height of the font
  def ch: String = s"${t}ch" // Width of the "0" character
  def cap: String = s"${t}cap" // Cap height of the font
  def lh: String = s"${t}lh" // Line height
  def rlh: String = s"${t}rlh" // Root line height

  // --- 3. RELATIVE LENGTHS (VIEWPORT) ---
  def vw: String = s"${t}vw" // 1% of viewport width
  def vh: String = s"${t}vh" // 1% of viewport height
  def vmin: String = s"${t}vmin" // 1% of viewport's smaller dimension
  def vmax: String = s"${t}vmax" // 1% of viewport's larger dimension

  // Modern Viewport Units (Small, Large, Dynamic)
  def svw: String = s"${t}svw"
  def svh: String = s"${t}svh"
  def lvw: String = s"${t}lvw"
  def lvh: String = s"${t}lvh"
  def dvw: String = s"${t}dvw"
  def dvh: String = s"${t}dvh"

  // --- 4. PERCENTAGES & GRID ---
  def pct: String = s"${t}%" // Percentage
  def fr: String = s"${t}fr" // Fraction (for CSS Grid)

  // --- 5. TIME ---
  def s: String = s"${t}s" // Seconds
  def ms: String = s"${t}ms" // Milliseconds

  // --- 6. ANGLES ---
  def deg: String = s"${t}deg" // Degrees
  def rad: String = s"${t}rad" // Radians
  def grad: String = s"${t}grad" // Gradians
  def turn: String = s"${t}turn" // Turns

  // --- 7. RESOLUTION ---
  def dpi: String = s"${t}dpi" // Dots per inch
  def dpcm: String = s"${t}dpcm" // Dots per centimeter
  def dppx: String = s"${t}dppx" // Dots per pixel
}
extension (s: String) {
  def apply(body: CSSStyle ?=> Unit)(using group: CSSRuleGroup) =
    group += s"${s}{${css(body).render}}"
}
def css(body: CSSStyle ?=> Unit) = {
  given style: CSSStyle = CSSStyle()
  body
  style
}
