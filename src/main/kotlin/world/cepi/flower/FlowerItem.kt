package world.cepi.flower

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.color
import world.cepi.kstom.adventure.component
import world.cepi.kstom.adventure.noItalic
import world.cepi.kstom.item.get
import world.cepi.kstom.item.item
import world.cepi.kstom.item.set
import world.cepi.kstom.util.pascalToTitle
import world.cepi.kstom.util.snakeToTitle

@kotlinx.serialization.Serializable
class FlowerItem(vararg val blocks: FlowerBlock) {

    fun add(block: FlowerBlock) = FlowerItem(*blocks, block)

    fun remove(index: Int) = FlowerItem(*blocks.filterIndexed { li, _ -> li != index }.toTypedArray())

    fun toItem() = item(Material.SUNFLOWER) {
        displayName(
            component(
                "Flower ".color(NamedTextColor.YELLOW),
                "(".color(NamedTextColor.DARK_GRAY),
                blocks.size.color(NamedTextColor.GRAY),
                ")".color(NamedTextColor.DARK_GRAY)
            )
        )

        this["flower"] = this@FlowerItem

        lore(
            Component.empty(),
            *blocks.map {
                component(
                    it.block.namespace().value().snakeToTitle().color(NamedTextColor.GRAY),
                    " (".color(NamedTextColor.DARK_GRAY),
                    it.chance.color(NamedTextColor.GRAY),
                    "%".color(NamedTextColor.GRAY),
                    ")".color(NamedTextColor.DARK_GRAY)
                )
            }.toTypedArray()
        )
    }

}

val ItemStack.flower: FlowerItem? get() = this.get("flower")