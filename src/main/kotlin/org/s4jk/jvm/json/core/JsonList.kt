package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.JsonStringManager

/**
 * Creates a [JsonList] from the provided elements.
 *
 * This function enables the creation of a [JsonList] by specifying a variable number of elements. The elements
 * are converted into a list and then used to construct a [JsonList].
 *
 * @param elements The elements to include in the JSON array.
 * @return An [IJL] instance representing the created [JsonList].
 */
@NotNull
@JvmSynthetic
fun jsonListOf(vararg elements: Any?): IJL {
    return JsonList.from(elements.toList())
}

/**
 * Converts an array into a [JsonList].
 *
 * This extension function creates a [JsonList] from the elements of the array. The array elements are converted
 * into a list and used to construct the [JsonList].
 *
 * @return An [IJL] instance representing the created [JsonList].
 */
@NotNull
@JvmSynthetic
fun Array<*>.toJsonList(): IJL {
    return JsonList.from(this@toJsonList)
}

/**
 * Converts a list into a [JsonList].
 *
 * This extension function creates a [JsonList] from the elements of the list. The list elements are used directly
 * to construct the [JsonList].
 *
 * @return An [IJL] instance representing the created [JsonList].
 */
@NotNull
@JvmSynthetic
fun List<*>.toJsonList(): IJL {
    return JsonList.from(this@toJsonList)
}

/**
 * Converts a JSON string into a [JsonList].
 *
 * This extension function parses a JSON-formatted string into a [JsonList]. The resulting [JsonList] contains
 * the data represented by the JSON string.
 *
 * @return An [IJL] instance representing the parsed JSON data.
 */
@NotNull
@JvmSynthetic
fun String.toJsonList(): IJL {
    return JsonList.from(this@toJsonList)
}

/**
 * Concrete implementation of [IJL], extending [AbstractJsonList].
 * This class provides methods for creating and manipulating JSON arrays from various data sources
 * such as arrays, lists, and JSON strings.
 */
class JsonList private constructor(list: MutableList<JsonValue>): AbstractJsonList(list) {

    companion object Static {

        /**
         * Creates an empty [JsonList].
         *
         * @return A new [JsonList] instance with an empty list.
         */
        @JvmStatic
        fun create(): IJL {
            return JsonList(mutableListOf())
        }

        /**
         * Creates a [JsonList] from an existing array.
         *
         * @param source The source array to convert into a [JsonList].
         * @return A new [JsonList] instance containing the elements from the source array.
         */
        @JvmStatic
        fun from(source: Array<*>): IJL {
            return JsonList(source.map { JsonValue.handle(it) }.toMutableList())
        }

        /**
         * Creates a [JsonList] from an existing list.
         *
         * @param source The source list to convert into a [JsonList].
         * @return A new [JsonList] instance containing the elements from the source list.
         */
        @JvmStatic
        fun from(source: List<*>): IJL {
            return JsonList(source.map { JsonValue.handle(it) }.toMutableList())
        }

        /**
         * Creates a [JsonList] from another [IJL] instance.
         *
         * @param source The source [IJL] instance to convert.
         * @return A new [JsonList] instance containing the elements from the source [IJL].
         */
        @JvmStatic
        fun from(source: IJL): IJL {
            val array = this.create()

            source.forEachIndexed { index, element ->
                array.add(index, element)
            }

            return array
        }

        /**
         * Parses a JSON string into a [JsonList].
         *
         * @param source The JSON string to parse into a [JsonList].
         * @return A new [JsonList] instance representing the parsed JSON data.
         */
        @JvmStatic
        fun from(source: String): IJL {
            return JsonStringManager.stringToJsonList(source)
        }
    }
}