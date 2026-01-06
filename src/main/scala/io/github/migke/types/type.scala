package github.io.migke.site.types

import scala.compiletime.constValue

opaque type TagType = String

inline def tagValue[T <: TagType]: String =
  constValue[T]

// Root
type HTML = "html" & TagType

// Document metadata
type Head = "head" & TagType
type Title = "title" & TagType
type Base = "base" & TagType
type Link = "link" & TagType
type Meta = "meta" & TagType
type Style = "style" & TagType

// Sectioning
type Body = "body" & TagType
type Header = "header" & TagType
type Footer = "footer" & TagType
type Main = "main" & TagType
type Section = "section" & TagType
type Article = "article" & TagType
type Nav = "nav" & TagType
type Aside = "aside" & TagType
type H1 = "h1" & TagType
type H2 = "h2" & TagType
type H3 = "h3" & TagType
type H4 = "h4" & TagType
type H5 = "h5" & TagType
type H6 = "h6" & TagType

// Grouping content
type P = "p" & TagType
type Hr = "hr" & TagType
type Pre = "pre" & TagType
type Blockquote = "blockquote" & TagType
type Ol = "ol" & TagType
type Ul = "ul" & TagType
type Li = "li" & TagType
type Dl = "dl" & TagType
type Dt = "dt" & TagType
type Dd = "dd" & TagType
type Figure = "figure" & TagType
type Figcaption = "figcaption" & TagType
type Div = "div" & TagType

// Text-level semantics
type A = "a" & TagType
type Em = "em" & TagType
type Strong = "strong" & TagType
type Small = "small" & TagType
type S = "s" & TagType
type Cite = "cite" & TagType
type Q = "q" & TagType
type Dfn = "dfn" & TagType
type Abbr = "abbr" & TagType
type Data = "data" & TagType
type Time = "time" & TagType
type Code = "code" & TagType
type Var = "var" & TagType
type Samp = "samp" & TagType
type Kbd = "kbd" & TagType
type Sub = "sub" & TagType
type Sup = "sup" & TagType
type I = "i" & TagType
type B = "b" & TagType
type U = "u" & TagType
type Mark = "mark" & TagType
type Ruby = "ruby" & TagType
type Rt = "rt" & TagType
type Rp = "rp" & TagType
type Bdi = "bdi" & TagType
type Bdo = "bdo" & TagType
type Span = "span" & TagType
type Br = "br" & TagType
type Wbr = "wbr" & TagType

// Edits
type Ins = "ins" & TagType
type Del = "del" & TagType

// Embedded content
type Img = "img" & TagType
type Iframe = "iframe" & TagType
type Embed = "embed" & TagType
type Object = "object" & TagType
type Param = "param" & TagType
type Video = "video" & TagType
type Audio = "audio" & TagType
type Source = "source" & TagType
type Track = "track" & TagType
type Canvas = "canvas" & TagType
type Map = "map" & TagType
type Area = "area" & TagType
type SVG = "svg" & TagType
type Math = "math" & TagType

// Tabular data
type Table = "table" & TagType
type Caption = "caption" & TagType
type Colgroup = "colgroup" & TagType
type Col = "col" & TagType
type Tbody = "tbody" & TagType
type Thead = "thead" & TagType
type Tfoot = "tfoot" & TagType
type Tr = "tr" & TagType
type Th = "th" & TagType
type Td = "td" & TagType

// Forms
type Form = "form" & TagType
type Label = "label" & TagType
type Input = "input" & TagType
type Button = "button" & TagType
type Select = "select" & TagType
type Datalist = "datalist" & TagType
type Optgroup = "optgroup" & TagType
type Option = "option" & TagType
type Textarea = "textarea" & TagType
type Output = "output" & TagType
type Progress = "progress" & TagType
type Meter = "meter" & TagType
type Fieldset = "fieldset" & TagType
type Legend = "legend" & TagType

// Interactive elements
type Details = "details" & TagType
type Summary = "summary" & TagType
type Dialog = "dialog" & TagType

// Scripting
type Script = "script" & TagType
type Noscript = "noscript" & TagType
type Template = "template" & TagType
type Slot = "slot" & TagType
