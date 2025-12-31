package migke.site.utils

import java.util.regex.Pattern

def escape(str: String): String = {
  val pattern = Pattern.compile("[\"&'<>]")
  val matcher = pattern.matcher(str)
  matcher.replaceAll((res) => {
    var matched = res.group()
    matched match {
      case "\"" => "&quot;"
      case "&"  => "&amp;"
      case "'"  => "&#x27;"
      case "<"  => "&lt;"
      case ">"  => "&gt;"
      case _    => matched
    }
  })
}
