package world.cepi.flower

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.noItalic
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set

@kotlinx.serialization.Serializable
class FlowerItem(vararg val blocks: FlowerBlock) {

    fun add(block: FlowerBlock) = FlowerItem(*blocks, block)

    fun remove(index: Int) = FlowerItem(*blocks.filterIndexed { li, _ -> li != index }.toTypedArray())

    fun toItem() = item(Material.SUNFLOWER) {
        displayName(Component.text("Flower", NamedTextColor.YELLOW).noItalic())

        this["flower"] = this@FlowerItem

        lore(Component.empty())
    }

}

val ItemStack.flower: FlowerItem? get() = this.get("flower")