package baguchi.tofucraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class TileScanner {
	private final Level world;

	private final BlockPos centrePos;

	public TileScanner(Level world, BlockPos pos) {
		this.world = world;
		this.centrePos = pos;
	}

	public <T> T scan(int size, Impl<T> impl) {
		return scan(size, Method.partial, impl);
	}

	public <T> T scan(int size, Method method, Impl<T> impl) {
		impl.pos = this.centrePos;
		for (int x = -size; x <= size; x++) {
			for (int y = -size; y <= size; y++) {
				for (int z = -size; z <= size; z++) {
					if (x != 0 || y != 0 || z != 0) {
						BlockPos absTargPos = this.centrePos.offset(x, y, z);
						method.impl.apply(this.world, new BlockPos(x, y, z), absTargPos, size, impl);
					}
				}
			}
		}
		return impl.getReturn();
	}

	public enum Method {
		partial(new IScanMethod() {
			public void apply(Level world, BlockPos relPos, BlockPos absTargPos, int size, Impl<?> impl) {
				int dist = Math.abs(relPos.getX()) + Math.abs(relPos.getY()) + Math.abs(relPos.getZ());
				if (dist == size)
					impl.apply(world, absTargPos);
			}
		}),
		full(new IScanMethod() {
			public void apply(Level world, BlockPos relPos, BlockPos absTargPos, int size, Impl<?> impl) {
				int dist = Math.abs(relPos.getX()) + Math.abs(relPos.getY()) + Math.abs(relPos.getZ());
				if (dist <= size)
					impl.apply(world, absTargPos);
			}
		}),
		fullSimply(new IScanMethod() {
			public void apply(Level world, BlockPos relPos, BlockPos absTagPos, int size, Impl<?> impl) {
				impl.apply(world, absTagPos);
			}
		});

		public IScanMethod impl;

		Method(IScanMethod impl) {
			this.impl = impl;
		}
	}

	private interface IScanMethod {
		void apply(Level param1Level, BlockPos param1BlockPos1, BlockPos param1BlockPos2, int param1Int, Impl<?> param1Impl);
	}

	public static abstract class Impl<T> {
		public BlockPos pos;

		public abstract void apply(Level param1Level, BlockPos param1BlockPos);

		public T getReturn() {
			return null;
		}
	}
}
