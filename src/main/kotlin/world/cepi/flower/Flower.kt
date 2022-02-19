package world.cepi.flower

import net.minestom.server.extensions.Extension;

class Flower : Extension() {

    override fun initialize(): LoadStatus {
        logger().info("[Flower] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {
        logger().info("[Flower] has been disabled!")
    }

}
