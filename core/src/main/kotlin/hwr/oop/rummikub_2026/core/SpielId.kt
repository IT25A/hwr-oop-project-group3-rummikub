package hwr.oop.rummikub_2026.core
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
@JvmInline
value class SpielId(val wert: String) {
    companion object {
        fun random(): SpielId = SpielId(UUID.randomUUID().toString())
        fun from(uuid: UUID): SpielId = SpielId(uuid.toString())
    }
    fun uuid(): UUID = UUID.fromString(wert)
}