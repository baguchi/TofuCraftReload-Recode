package baguchi.tofucraft.data.provider;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.model.Material;
import net.minecraft.world.level.block.Block;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class TofuTextureMapping {
	public static final TextureSlot GLOW_ALL = TextureSlot.create("glow_all");
	public static final TextureSlot GLOW_END = TextureSlot.create("glow_end");
	public static final TextureSlot GLOW_SIDE = TextureSlot.create("glow_side");

	public static final TextureSlot OVERLAY = TextureSlot.create("overlay");

	public static TextureMapping cubeTop(Block block) {
		return (new TextureMapping()).put(TextureSlot.ALL, getBlockTexture(block, "_top"));
	}



	public static TextureMapping doorTop(Block block) {
		return new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).copySlot(TextureSlot.TOP, TextureSlot.PARTICLE);
	}

	public static TextureMapping doorBottom(Block block) {
		return new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block)).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom")).copySlot(TextureSlot.BOTTOM, TextureSlot.PARTICLE);
	}

	public static TextureMapping logGlowColumn(Block p_388105_) {
		return new TextureMapping()
				.put(TextureSlot.SIDE, getBlockTexture(p_388105_))
				.put(TextureSlot.END, getBlockTexture(p_388105_, "_top"))
				.put(GLOW_SIDE, getBlockTexture(p_388105_, "_glow"))
				.put(GLOW_END, getBlockTexture(p_388105_, "_top_glow"))
				.put(TextureSlot.PARTICLE, getBlockTexture(p_388105_));
	}

	public static TextureMapping candleCake(Block candle, Block cake, boolean p_387959_) {
		return new TextureMapping()
				.put(TextureSlot.PARTICLE, getBlockTexture(cake, "_side"))
				.put(TextureSlot.BOTTOM, getBlockTexture(cake, "_bottom"))
				.put(TextureSlot.TOP, getBlockTexture(cake, "_top"))
				.put(TextureSlot.SIDE, getBlockTexture(cake, "_side"))
				.put(TextureSlot.CANDLE, getBlockTexture(candle, p_387959_ ? "_lit" : ""));
	}

	public static TextureMapping ladder(Block ladder) {
		return new TextureMapping()
				.put(TextureSlot.ALL, getBlockTexture(ladder));
	}


	public static TextureMapping grassBlock(Block block, Block dirt) {
		Material resourcelocation = getBlockTexture(block, "_top");
		Material resourcelocation2 = getBlockTexture(block, "_side");
		Material resourcelocation3 = getBlockTexture(block, "_side_overlay");
		Material resourcelocation4 = getBlockTexture(dirt);
		return grassBlock(resourcelocation, resourcelocation2, resourcelocation3, resourcelocation4);
	}

	public static TextureMapping grassBlock(Material p_386993_, Material side, Material overlay, Material dirt) {
		return new TextureMapping().put(TextureSlot.PARTICLE, dirt).put(TextureSlot.TOP, p_386993_).put(TextureSlot.SIDE, side).put(TextureSlot.BOTTOM, dirt).put(OVERLAY, overlay);
	}

	public static TextureMapping glowCube(Block p_387253_) {
		Material resourcelocation = getBlockTexture(p_387253_);
		Material resourcelocation2 = getBlockTexture(p_387253_, "_emissive");
		return glowCube(resourcelocation, resourcelocation2);
	}

	public static TextureMapping glowCube(Material p_386993_, Material glow) {
		return (new TextureMapping()).put(TextureSlot.ALL, p_386993_).put(GLOW_ALL, glow);
	}
}