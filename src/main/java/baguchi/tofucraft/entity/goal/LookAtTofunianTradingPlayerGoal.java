package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.AbstractTofunian;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;

public class LookAtTofunianTradingPlayerGoal extends LookAtPlayerGoal {
    private final AbstractTofunian villager;

    public LookAtTofunianTradingPlayerGoal(AbstractTofunian p_25538_) {
        super(p_25538_, Player.class, 8.0F);
        this.villager = p_25538_;
    }

    public boolean canUse() {
        if (this.villager.isTrading()) {
            this.lookAt = this.villager.getTradingPlayer();
            return true;
        } else {
            return false;
        }
    }
}