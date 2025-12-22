package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.attachment.SoyHealthAttachment;
import baguchi.tofucraft.attachment.TofuLivingAttachment;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class TofuAttachments {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TofuCraftReload.MODID);

	public static final Supplier<AttachmentType<SoyHealthAttachment>> SOY_HEALTH = ATTACHMENT_TYPES.register(
			"soy_health", () -> AttachmentType.serializable(SoyHealthAttachment::new).copyOnDeath().build());
	public static final Supplier<AttachmentType<TofuLivingAttachment>> TOFU_LIVING = ATTACHMENT_TYPES.register(
			"tofu_living", () -> AttachmentType.serializable(TofuLivingAttachment::new).sync(new AttachmentSyncHandler<>() {
				@Override
				public void write(RegistryFriendlyByteBuf registryFriendlyByteBuf, TofuLivingAttachment tofuLivingAttachment, boolean b) {
					registryFriendlyByteBuf.writeFloat(tofuLivingAttachment.recoverHealth);
					registryFriendlyByteBuf.writeBoolean(tofuLivingAttachment.zundafied);
					registryFriendlyByteBuf.writeInt(tofuLivingAttachment.eatCooldown);
				}

				@Override
				public @Nullable TofuLivingAttachment read(IAttachmentHolder iAttachmentHolder, RegistryFriendlyByteBuf registryFriendlyByteBuf, @Nullable TofuLivingAttachment tofuLivingAttachment) {
					TofuLivingAttachment attachment = new TofuLivingAttachment();

					attachment.recoverHealth = registryFriendlyByteBuf.readFloat();
					attachment.zundafied = registryFriendlyByteBuf.readBoolean();
					attachment.eatCooldown = registryFriendlyByteBuf.readInt();

					return attachment;
				}
			}).build());
}