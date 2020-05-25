package com.raytool.extensions

/**
 *@author Luis Miguel Barcos
 */

/**
 * Get words of a sentence. Assume the words are separated by spaces and a word could contain
 * any kind of character
 * @param useAmpersandAsWhitespace check if use symbol & as white space to get two or more words as one element in the List.
 * The & will be replace by whitespace
 * @return List of words in a sentence
 */
fun String.getWordsBySpaces(useAmpersandAsWhitespace: Boolean = false) : List<String> =
    when(useAmpersandAsWhitespace){
        true -> this.split("\\s+".toRegex()).map { it.replace('&', ' ') }
        false -> this.split("\\s+".toRegex())
}