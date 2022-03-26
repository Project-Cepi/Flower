package world.cepi.flower

import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kepi.Kepi
import world.cepi.kepi.item.AddCreationalItem
import world.cepi.kepi.item.CreationalItemResult
import world.cepi.kstom.command.arguments.defaultValue
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object FlowerCommand : Kommand({

    val create by literal

    val add by literal

    val block = ArgumentType.BlockState("block")
    val chance = ArgumentType.Integer("chance").min(0).max(100).defaultValue(100)

    val loop = ArgumentType.Loop("blocks", ArgumentType.Group(
        "blockGroup",
        block,
        chance
    ))

    val remove by literal
    val index = ArgumentType.Integer("index").min(0)

    syntax(create) {
        AddCreationalItem.put(player, FlowerItem().toItem())

        player.playSound(Kepi.newItemSound)
    }

    syntax(create, loop) {

        AddCreationalItem.put(player, (!loop).fold(FlowerItem()) { flower, blockContext ->
            flower.add(FlowerBlock(blockContext.get(block), blockContext.get(chance)))
        }.toItem())

        player.playSound(Kepi.newItemSound)
    }

    syntax(add, loop) {

        if (player.itemInMainHand.flower == null) return@syntax

        val initialFlower = player.itemInMainHand.flower ?: return@syntax

        player.itemInMainHand = (!loop).fold(initialFlower) { flower, blockContext ->
            flower.add(FlowerBlock(blockContext.get(block), blockContext.get(chance)))
        }.toItem()

        player.playSound(Kepi.editItemSound)
    }

    syntax(add, block) {

        if (player.itemInMainHand.flower == null) return@syntax

        val flower = player.itemInMainHand.flower ?: return@syntax

        player.itemInMainHand = flower.add(FlowerBlock(!block)).toItem()

        player.playSound(Kepi.editItemSound)
    }

    syntax(remove, index) {

        if (player.itemInMainHand.flower == null) return@syntax

        val flower = player.itemInMainHand.flower ?: return@syntax

        player.itemInMainHand = flower.remove(!index).toItem()

        player.playSound(Kepi.editItemSound)
    }

}, "flower")