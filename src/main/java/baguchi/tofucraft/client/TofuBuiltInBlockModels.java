package baguchi.tofucraft.client;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.TofunianStatueBlock;
import baguchi.tofucraft.block.utils.TofuChestBlock;
import baguchi.tofucraft.client.render.blockentity.TofuChestRenderer;
import baguchi.tofucraft.client.render.blockentity.TofunianStatueRender;
import baguchi.tofucraft.client.render.special.TofunianStatueSpecialRenderer;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.client.renderer.block.BuiltInBlockModels;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.client.renderer.special.BedSpecialRenderer;
import net.minecraft.client.renderer.special.ChestSpecialRenderer;
import net.minecraft.world.level.block.BedBlock;
import net.neoforged.neoforge.client.event.RegisterBlockModelsEvent;

import static net.minecraft.client.renderer.block.BuiltInBlockModels.specialModelWithPropertyDispatch;

//used to register custom special block models. which is allow render when enderman hold block like chest...
public class TofuBuiltInBlockModels {

	public static void registerBuiltinModel(RegisterBlockModelsEvent event) {
		event.register(createTofunianStatue(), TofuBlocks.TOFUNIAN_STATUE.get());
		event.register(createTofuBed(), TofuBlocks.TOFUBED.get());
		event.register(createTofuChest(), TofuBlocks.TOFUCHEST.get());
	}

	public static BuiltInBlockModels.SpecialModelFactory createTofunianStatue() {
		return specialModelWithPropertyDispatch(
				TofunianStatueBlock.FACING, (facing) -> BuiltInBlockModels.special(new TofunianStatueSpecialRenderer.Unbaked(), TofunianStatueRender.modelTransformation(facing))
		);
	}


	public static BuiltInBlockModels.SpecialModelFactory createTofuBed() {
		return specialModelWithPropertyDispatch(
				BedBlock.FACING, BedBlock.PART, (facing, part) -> BuiltInBlockModels.special(new BedSpecialRenderer.Unbaked(TofuCraftReload.prefix("tofubed"), part), BedRenderer.modelTransform(facing))
		);
	}

	public static BuiltInBlockModels.SpecialModelFactory createTofuChest() {
		return specialModelWithPropertyDispatch(
				TofuChestBlock.FACING, (facing) -> BuiltInBlockModels.special(new ChestSpecialRenderer.Unbaked(TofuCraftReload.prefix("tofuchest")), TofuChestRenderer.modelTransformation(facing))
		);
	}
}
