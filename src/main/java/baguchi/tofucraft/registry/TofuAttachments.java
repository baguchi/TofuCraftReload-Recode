package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.attachment.SoyHealthAttachment;
import baguchi.tofucraft.attachment.TofuLivingAttachment;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class TofuAttachments {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TofuCraftReload.MODID);

	public static final Supplier<AttachmentType<SoyHealthAttachment>> SOY_HEALTH = ATTACHMENT_TYPES.register(
			"soy_health", () -> AttachmentType.serializable(SoyHealthAttachment::new).build());
	public static final Supplier<AttachmentType<TofuLivingAttachment>> TOFU_LIVING = ATTACHMENT_TYPES.register(
			"tofu_living", () -> AttachmentType.serializable(TofuLivingAttachment::new).build());
}