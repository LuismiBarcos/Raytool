package com.raytool.extensions

/**
 *@author Luis Miguel Barcos
 */

/**
 * Get words of a sentence. Assume the words are separated by spaces and a word could contain
 * any kind of character
 * @return List of words in a sentence
 */
fun String.getWordsBySpaces() : List<String> {
    return this.split("\\s+".toRegex())
}