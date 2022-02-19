package world.cepi.flower

import net.minestom.server.event.player.PlayerBlockPlaceEvent

object FlowerEvent {

    fun place(event: PlayerBlockPlaceEvent) = with(event) {

        val flower = player.itemInMainHand.flower ?: return@with

        var blockIndex = 0
        flower.blocks.forEach { flowerBlock ->
            val block = flowerBlock.generate() ?: return@forEach

            instance.setBlock(blockPosition.add(0.0, blockIndex.toDouble(), 0.0), block)

            blockIndex++
        }

        isCancelled = true

    }

}