package world.cepi.flower

import net.minestom.server.instance.block.Block
import world.cepi.kstom.serializer.BlockSerializer
import kotlin.random.Random

@kotlinx.serialization.Serializable
class FlowerBlock(
    @kotlinx.serialization.Serializable(with = BlockSerializer::class)
    val block: Block,
    val chance: Int = 100
) {
    fun generate(): Block? = if (Random.Default.nextInt(100) >= 100 - chance) block else null
}