package world.cepi.flower

import net.minestom.server.extensions.Extension;
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.node

class Flower : Extension() {

    override fun initialize(): LoadStatus {
        FlowerCommand.register()
        node.listenOnly(FlowerEvent::place)

        logger().info("[Flower] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        FlowerCommand.unregister()

        logger().info("[Flower] has been disabled!")
    }

}
