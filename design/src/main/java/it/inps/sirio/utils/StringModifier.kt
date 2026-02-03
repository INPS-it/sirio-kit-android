package it.inps.sirio.utils


internal fun String?.takeTwoWords(): String {
    return this.takeWords(2)
}

internal fun String?.takeWords(numberOfWords: Int? = null): String { //SE SI PASSA NULL LE PRENDE TUTTE
    val wordsArray = this?.split(" ")
    if (wordsArray.isNullOrEmpty()) {
        return ""
    } else {
        var stringaSupporto = ""
        val wordsToTake =
            if (numberOfWords == null) wordsArray.size else if (numberOfWords < wordsArray.size) numberOfWords else wordsArray.size
        for (i: Int in 0 until wordsToTake) {
            stringaSupporto += wordsArray[i].lowercase().replaceFirstChar { it.uppercase() }
        }
        return stringaSupporto
    }
}