package com.pavlov.demo_translator.common

typealias NumericId = Long

fun String.correctUrl() = "https:$this"

fun String.decodePartOfSpeech() = when (this) {
    "n" -> "noun"
    "v" -> "verb"
    "j" -> "adjective"
    "r" -> "adverb"
    "prp" -> "preposition"
    "prn" -> "pronoun"
    "crd" -> "cardinal number"
    "cjc" -> "conjunction"
    "exc" -> "interjection"
    "det" -> "article"
    "abb" -> "abbreviation"
    "x" -> "particle"
    "ord" -> "ordinal number"
    "md" -> "modal verb"
    "ph" -> "phrase"
    "phi" -> "idiom"
    else -> this
}