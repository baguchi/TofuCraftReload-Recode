package baguchi.tofucraft.api;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public record TofunianProfession(Optional<TagKey<Block>> jobSite) {
	public boolean is(BlockState state) {
		return this.jobSite.isPresent() && state.getBlockHolder().is(this.jobSite.get());
	}

	public boolean isValidTarget(BlockState blockstate) {
		return jobSite().isPresent() && blockstate.getBlockHolder().is(jobSite.get());
	}
}