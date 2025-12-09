package baguchi.tofucraft.network;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.TofuLearning;
import baguchi.tofucraft.attachment.TofuPlayerAttachment;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

import java.util.Optional;

public class AddLearningPacket implements CustomPacketPayload, IPayloadHandler<AddLearningPacket> {

	public static final StreamCodec<FriendlyByteBuf, AddLearningPacket> STREAM_CODEC = CustomPacketPayload.codec(
			AddLearningPacket::write, AddLearningPacket::new
	);
	public static final CustomPacketPayload.Type<AddLearningPacket> TYPE = new Type<>(TofuCraftReload.prefix("add_learn"));


	private int entityId;
	private Identifier learning;
	private boolean makeToast;


	public AddLearningPacket(int id, Identifier learning, boolean toast) {
		this.entityId = id;
		this.learning = learning;
		this.makeToast = toast;
	}

	public AddLearningPacket(Entity entity, Identifier learning, boolean toast) {
		this.entityId = entity.getId();
		this.learning = learning;
		this.makeToast = toast;
	}

	public void write(FriendlyByteBuf buffer) {
		buffer.writeInt(this.entityId);
		buffer.writeIdentifier(this.learning);
		buffer.writeBoolean(this.makeToast);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public AddLearningPacket(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readIdentifier(), buffer.readBoolean());
	}

	@Override
	public void handle(AddLearningPacket message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Entity entity = Minecraft.getInstance().player.level().getEntity(message.entityId);
			if (entity != null && entity instanceof Player player) {
				TofuPlayerAttachment attachment = player.getData(TofuAttachments.TOFU_PLAYER);
				Optional<Holder.Reference<TofuLearning>> optional = Minecraft.getInstance().player.level().registryAccess().lookupOrThrow(TofuLearning.REGISTRY_KEY).get(learning);
				optional.ifPresent(tofuLearningHolder -> attachment.addLearning(tofuLearningHolder, player));
				if (player == Minecraft.getInstance().player && makeToast) {
					ClientUtils.openToast();
				}
			}
		});
	}
}