package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class TofuCapability {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TofuCraftReload.MODID);

	public static final Supplier<AttachmentType<SoyHealthCapability>> SOY_HEALTH = ATTACHMENT_TYPES.register(
			"soy_health", () -> AttachmentType.serializable(SoyHealthCapability::new).build());
	public static final Supplier<AttachmentType<TofuLivingCapability>> TOFU_LIVING = ATTACHMENT_TYPES.register(
			"tofu_living", () -> AttachmentType.serializable(TofuLivingCapability::new).build());
}