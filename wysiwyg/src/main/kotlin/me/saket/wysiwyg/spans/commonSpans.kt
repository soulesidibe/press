package me.saket.wysiwyg.spans

actual fun SpanPool.foregroundColor(color: Int): WysiwygSpan =
  get { ForegroundColorSpan(recycler) }.apply {
    this.color = color
  }

actual fun SpanPool.italics(): WysiwygSpan =
  get { StyleSpan(recycler) }.apply {
    style = StyleSpan.Style.ITALIC
  }

actual fun SpanPool.bold(): WysiwygSpan =
  get { StyleSpan(recycler) }.apply {
    style = StyleSpan.Style.BOLD
  }

actual fun SpanPool.strikethrough(): WysiwygSpan =
  get { StrikethroughSpan(recycler) }

actual fun SpanPool.inlineCode(): WysiwygSpan =
  get { InlineCodeSpan(theme, recycler) }

actual fun SpanPool.monospaceTypeface(): WysiwygSpan =
  get { MonospaceTypefaceSpan(recycler) }

actual fun SpanPool.indentedCodeBlock(): WysiwygSpan =
  get { IndentedCodeBlockSpan(theme, recycler) }

actual fun SpanPool.quote(): WysiwygSpan =
  get { BlockQuoteSpan(theme, recycler) }