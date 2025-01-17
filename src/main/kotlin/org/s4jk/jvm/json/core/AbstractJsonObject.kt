package org.s4jk.jvm.json.core

import org.jetbrains.annotations.NotNull
import org.s4jk.jvm.json.JsonStringManager
import java.util.*
import java.util.function.Consumer

/**
 * Abstract base class for [IJO], providing default implementations for common operations.
 *
 * @property objectName The name associated with this JSON object.
 * @property map A mutable map holding the key-value pairs for the JSON object.
 */
abstract class AbstractJsonObject protected constructor(
    private val objectName: String,
    private val map: MutableMap<String, JsonValue>
): IJO {
    override val name get() = this.objectName
    override val entries get() = this.map.entries
    override val keys get() = this.map.keys
    override val values get() = this.map.values

    override fun get(key: String): JsonValue {
        return this.map[key] ?: JsonValue.Null
    }

    override fun getOrDefault(key: String, defaultValue: Any?): JsonValue {
        return this.map.getOrDefault(key, JsonValue.handle(defaultValue))
    }

    override fun set(key: String, value: Any?) {
        this.map[key] = JsonValue.handle(value)
    }

    override fun remove(key: String): JsonValue {
        return this.map.remove(key) ?: JsonValue.Null
    }

    override fun toString(): String {
        return JsonStringManager.jsonObjectToString(this, 0, 1)
    }

    override fun toString(@NotNull indent: Int): String {
        return JsonStringManager.jsonObjectToString(this, indent, 1)
    }

    override fun forEach(action: Consumer<in MutableMap.MutableEntry<String, JsonValue>>?) {
        return this.map.entries.forEach(action)
    }

    override fun spliterator(): Spliterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this.map.entries.spliterator()
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, JsonValue>> {
        return this.map.entries.iterator()
    }
}