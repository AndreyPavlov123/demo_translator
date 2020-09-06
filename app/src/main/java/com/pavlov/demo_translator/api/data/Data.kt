package com.pavlov.demo_translator.api.data

class Translation {
    var text: String? = null
    var note: String? = null
}

class MeaningShort {
    var id: Int? = null
    var partOfSpeechCode: String? = null
    var translation: Translation? = null
    var previewUrl: String? = null
    var imageUrl: String? = null
    var transcription: String? = null
    var soundUrl: String? = null
}

class MeaningShortRoot {
    var id: Int? = null
    var text: String? = null
    var meanings: List<MeaningShort>? = null
}

class Image {
    var url: String? = null
}

class Definition {
    var text: String? = null
    var soundUrl: String? = null
}

class MeaningsWithSimilarTranslation {
    var meaningId = 0
    var frequencyPercent: String? = null
    var partOfSpeechAbbreviation: String? = null
    var translation: Translation? = null
}

class AlternativeTranslation {
    var text: String? = null
    var translation: Translation? = null
}

class MeaningFull {
    var id: String? = null
    var wordId = 0
    var difficultyLevel = 0
    var partOfSpeechCode: String? = null
    var prefix: String? = null
    var text: String? = null
    var soundUrl: String? = null
    var transcription: String? = null
    var properties: Map<String, Any?>? = null
    var updatedAt: String? = null
    var mnemonics: String? = null
    var translation: Translation? = null
    var images: List<Image>? = null
    var definition: Definition? = null
    var examples: List<Definition>? = null
    var meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation>? = null
    var alternativeTranslations: List<AlternativeTranslation>? = null
}