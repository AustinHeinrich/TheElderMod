package kobolds.the_elder.world.dimension.elder;
import java.util.List;
import java.util.Random;

import kobolds.the_elder.init.ModBiomes;
import kobolds.the_elder.init.ModBlocks;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class ChunkGeneratorElder implements IChunkGenerator {
	protected static final IBlockState STONE = ModBlocks.ELDER_STONE.getDefaultState();
	protected static final IBlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
	protected static final IBlockState WATER = Blocks.WATER.getDefaultState();
	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
	private final Random rand;
	private NoiseGeneratorOctaves octPri1;
	private NoiseGeneratorOctaves octPri2;
	private NoiseGeneratorOctaves octPri3;
	private NoiseGeneratorPerlin perl;
	public NoiseGeneratorOctaves octPub1;
	public NoiseGeneratorOctaves octPub2;
	public NoiseGeneratorOctaves octPub3;
	private final World world;
	private final WorldType terrainType;
	private final double[] heightMap;
	private final double[] underMap;
	private final float[] field_185999_r;
	private ChunkGenSettings settings;
	private double[] field_186002_u = new double[256];
	private Biome[] biomesForGeneration;
	double[] heightA, heightB, heightC, heightD;
	double[] underA, underB, underC, underD;
	private final NoiseGeneratorOctaves noiseGen4;
	private double stoneNoise[];
	private MapGenBase caveGenerator;
	// private MapGenBaseMeta bigCaveGenerator;

	public ChunkGeneratorElder (World worldIn, long seed) {

		this.world = worldIn;
		this.terrainType = worldIn.getWorldInfo().getTerrainType();
		this.rand = new Random(seed);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.stoneNoise = new double[256];
		this.octPri1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.octPri2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.octPri3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.perl = new NoiseGeneratorPerlin(this.rand, 4);
		this.octPub1 = new NoiseGeneratorOctaves(this.rand, 10);
		this.octPub2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.octPub3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.heightMap = new double[825];
		this.underMap = new double[825];
		this.field_185999_r = new float[25];
		caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(new MapGenCaves(),
				net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				this.field_185999_r[i + 2 + (j + 2) * 5] = f;
			}
		}
		this.settings = new ChunkGenSettings.Factory().build();
		net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx = new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(
				octPri1, octPri2, octPri3, perl, octPub1, octPub2, octPub3);

		ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
		this.octPri1 = ctx.getLPerlin1();
		this.octPri2 = ctx.getLPerlin2();
		this.octPri3 = ctx.getPerlin();
		this.perl = ctx.getHeight();
		this.octPub1 = ctx.getScale();
		this.octPub2 = ctx.getDepth();
		this.octPub3 = ctx.getForest();
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.setBlocksInChunk(x, z, chunkprimer);
		this.setUnderside(x, z, chunkprimer);

		// addIceForestTop(x, z, chunkprimer);
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16,
				16);
		this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);

		this.caveGenerator.generate(this.world, x, z, chunkprimer);

		Chunk chunk = new Chunk(this.world, chunkprimer, x, z);

		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		int i = x * 16;
		int j = z * 16;
		BlockPos blockpos = new BlockPos(i, 0, j);
		Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
		this.rand.setSeed(this.world.getSeed());
		long k = this.rand.nextLong() / 2L * 2L + 1L;
		long l = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long) x * k + (long) z * l ^ this.world.getSeed());
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
		if (biome != Biomes.DESERT && biome != Biomes.DESERT_HILLS && this.settings.useWaterLakes
				&& this.rand.nextInt(this.settings.waterLakeChance) == 0)
			if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
					net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE)) {

			}
		if (this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes)
			if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
					net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA)) {
			}
		biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
		if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
				net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
			WorldEntitySpawner.performWorldGenSpawning(this.world, biome, i + 8, j + 8, 16, 16, this.rand);
		blockpos = blockpos.add(8, 0, 8);
		if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false,
				net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE)) {
			for (int k2 = 0; k2 < 16; ++k2) {
				for (int j3 = 0; j3 < 16; ++j3) {
					BlockPos blockpos1 = this.world.getPrecipitationHeight(blockpos.add(k2, 0, j3));
					BlockPos blockpos2 = blockpos1.down();
					if (this.world.canBlockFreezeWater(blockpos2)) {
						this.world.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
					}
					if (this.world.canSnowAt(blockpos1, true)) {
						this.world.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
					}
				}
			}
		}
		net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		Biome biome = this.world.getBiome(pos);
		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	private void setBlocksInChunk(int x, int z, ChunkPrimer primer) {	
		this.biomesForGeneration = this.world.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration,
				x * 4 - 2, z * 4 - 2, 10, 10);
		this.generateHeightmap(x * 4, 0, z * 4);

		for (int chunkX = 0; chunkX < 4; ++chunkX) {
			int xval = chunkX*5;
			int xvalNext = xval + 5;
			for (int chunkZ = 0; chunkZ < 4; ++chunkZ) {
				int chunk1 = (xval + chunkZ) * 33;
				int chunk2 = chunk1 + 33;
				int chunk3 = (xvalNext + chunkZ) * 33;
				int chunk4 = chunk3 + 33;

				for (int chunkY = 0; chunkY < 32; ++chunkY) {
					double x0 = this.heightMap[chunk1 + chunkY];
					double z0 = this.heightMap[chunk2 + chunkY];
					double x1 = this.heightMap[chunk3 + chunkY];
					double z1 = this.heightMap[chunk4 + chunkY];
					double x0Up = (this.heightMap[chunk1 + chunkY + 1] - x0) * 0.125D;
					double z0Up = (this.heightMap[chunk2 + chunkY + 1] - z0) * 0.125D;
					double x1Up = (this.heightMap[chunk3 + chunkY + 1] - x1) * 0.125D;
					double z1Up = (this.heightMap[chunk4 + chunkY + 1] - z1) * 0.125D;

					for (int dY = 0; dY < 8; ++dY) {
						double xCur = x0;
						double zCur = z0;
						double xNext = (x1 - x0) * 0.25D;
						double zNext = (z1 - z0) * 0.25D;

						for (int dX = 0; dX < 4; ++dX) {
							double pointDiff = (zCur - xCur) * 0.25D;
							double check = xCur;

							for (int dZ = 0; dZ < 4; ++dZ) {
								if (check > 0.0D) {
									primer.setBlockState(chunkX*4 + dX, chunkY * 8 + dY, chunkZ*4 + dZ, STONE);
								} else if (chunkY * 8 + dY < this.settings.seaLevel) {
									//primer.setBlockState(chunkX*4 + dX, chunkY * 8 + dY, chunkZ*4 + dZ, WATER);
								}
								check += pointDiff;
							}

							xCur += xNext;
							zCur += zNext;
						}

						x0 += x0Up;
						z0 += z0Up;
						x1 += x1Up;
						z1 += z1Up;
					}
				}
			}
		}
	}

	private void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn) {
		if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world))
			return;
		double d0 = 0.03125D;
		this.field_186002_u = this.perl.getRegion(this.field_186002_u, (double) (x * 16), (double) (z * 16),
				16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
		for (int i = 0; i < 16; ++i) {
			for (int j = 0; j < 16; ++j) {
				Biome Biome = biomesIn[j + i * 16];
				generateBiomeTerrain(this.world, this.rand, primer, x * 16 + i, z * 16 + j,
						this.field_186002_u[j + i * 16], Biome);
			}
		}
	}


	private void generateBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z,
			double noiseVal, Biome biome) {
		int seaLevel = worldIn.getSeaLevel();
		IBlockState topBlock = biome.topBlock;
		IBlockState fillerBlock = biome.fillerBlock;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int dz = z & 15;
		int dx = x & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		for (int y = 255; y >= 0; --y) {
			IBlockState currentBlock = chunkPrimerIn.getBlockState(dx, y, dz);
			if (currentBlock.getMaterial() == Material.AIR) {
				j = -1;
			} else if (currentBlock.getBlock() == STONE.getBlock()) {
				if (j == -1) {
					if (k <= 0) {
						topBlock = AIR;
						fillerBlock = STONE;
					} else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
						topBlock = biome.topBlock;
						fillerBlock = biome.fillerBlock;
					}
					if ( y < seaLevel && (topBlock == null || topBlock.getMaterial() == Material.AIR)) {
						fillerBlock = WATER;
					}
					j = k;
					if (y >= seaLevel - 1) {
						chunkPrimerIn.setBlockState(dx, y, dz, topBlock);
					} else if (y < seaLevel - 7 - k) {
						topBlock = AIR;
						fillerBlock = STONE;
						//chunkPrimerIn.setBlockState(dx, y, dz, GRAVEL);
					} else {
						chunkPrimerIn.setBlockState(dx, y, dz, fillerBlock);
					}
				} else if (j > 0) {
					--j;
					chunkPrimerIn.setBlockState(dx, y, dz, fillerBlock);
					if (j == 0 && fillerBlock.getBlock() == Blocks.SAND) {
						j = rand.nextInt(4);
						fillerBlock = STONE;
					}
				}
			}
		}
	}

	private void generateHeightmap(int x, int y, int z) {
		this.heightA = this.octPub2.generateNoiseOctaves(this.heightA, x, z, 5,
				5, (double) this.settings.depthNoiseScaleX, (double) this.settings.depthNoiseScaleZ,
				(double) this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.heightB = this.octPri3.generateNoiseOctaves(this.heightB, x, y,
				z, 5, 33, 5, (double) (f / this.settings.mainNoiseScaleX),
				(double) (f1 / this.settings.mainNoiseScaleY), (double) (f / this.settings.mainNoiseScaleZ));
		this.heightC = this.octPri1.generateNoiseOctaves(this.heightC, x, y,
				z, 5, 33, 5, (double) f, (double) f1, (double) f);
		this.heightD = this.octPri2.generateNoiseOctaves(this.heightD, x, y,
				z, 5, 33, 5, (double) f, (double) f1, (double) f);
		int i = 0;
		int j = 0;
		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				int i1 = 2;
				Biome biome = this.biomesForGeneration[k + 2 + (l + 2) * 10];
				for (int j1 = -i1; j1 <= i1; ++j1) {
					for (int k1 = -i1; k1 <= i1; ++k1) {
						Biome biome1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
						float f5 = this.settings.biomeDepthOffSet
								+ biome1.getBaseHeight() * this.settings.biomeDepthWeight;
						//if (f5 < 0F && biome != biome1) {
						//	f5 = this.settings.biomeDepthOffSet;
						//}
						float f6 = this.settings.biomeScaleOffset
								+ biome1.getHeightVariation() * this.settings.biomeScaleWeight;
						if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
							f5 = 1.0F + f5 * 2.0F;
							f6 = 1.0F + f6 * 4.0F;
						}
						float f7 = this.field_185999_r[j1 + 2 + (k1 + 2) * 5] / (Math.abs(f5) + 2.0F);
						if (biome1.getBaseHeight() > biome.getBaseHeight()) {
							f7 /= 2.0F;
						}
						f2 += f6 * f7;
						f3 += f5 * f7;
						f4 += f7;
					}
				}
				f2 = f2 / f4;
				f3 = f3 / f4;
				f2 = f2 * 0.9F + 0.1F;
				f3 = (f3 * 4.0F - 1.0F) / 8.0F;
				double d7 = this.heightA[j] / 8000.0D;
				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}
				d7 = d7 * 3.0D - 2.0D;
				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;
					if (d7 < -1.0D) {
						d7 = -1.0D;
					}
					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}
					d7 = d7 / 8.0D;
				}
				++j;
				double d8 = (double) f3;
				double d9 = (double) f2;
				d8 = d8 + d7 * 0.2D;
				d8 = d8 * (double) this.settings.baseSize / 8.0D;
				double d0 = (double) this.settings.baseSize + d8 * 4.0D;
				for (int l1 = 0; l1 < 33; ++l1) {
					double d1 = ((double) l1 - d0) * this.settings.stretchY * 128.0D / 256.0D / d9;
					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}
					double d2 = this.heightC[i] / (double) this.settings.lowerLimitScale;
					double d3 = this.heightD[i] / (double) this.settings.upperLimitScale;
					double d4 = (this.heightB[i] / 10.0D + 1.0D) / 2.0D;
					double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;
					if (l1 > 29) {
						double d6 = (double) ((float) (l1 - 29) / 3.0F);
						d5 = d5 * (1.0D - d6) + -10.0D * d6;
					}
					
					this.heightMap[i] = d5;
					++i;
				}
			}
		}
	}
	private void setUnderside(int x, int z, ChunkPrimer primer) {
		this.generateUnderside(x * 4, 0, z * 4);

		for (int chunkX = 0; chunkX < 4; ++chunkX) {
			int xval = chunkX*5;
			int xvalNext = xval + 5;
			for (int chunkZ = 0; chunkZ < 4; ++chunkZ) {
				int chunk1 = (xval + chunkZ) * 33;
				int chunk2 = chunk1 + 33;
				int chunk3 = (xvalNext + chunkZ) * 33;
				int chunk4 = chunk3 + 33;

				for (int chunkY = 0; chunkY < 32; ++chunkY) {
					double x0 = this.underMap[chunk1 + chunkY];
					double z0 = this.underMap[chunk2 + chunkY];
					double x1 = this.underMap[chunk3 + chunkY];
					double z1 = this.underMap[chunk4 + chunkY];
					double x0Up = (this.underMap[chunk1 + chunkY + 1] - x0) * 0.5D;
					double z0Up = (this.underMap[chunk2 + chunkY + 1] - z0) * 0.5D;
					double x1Up = (this.underMap[chunk3 + chunkY + 1] - x1) * 0.5D;
					double z1Up = (this.underMap[chunk4 + chunkY + 1] - z1) * 0.5D;

					for (int dY = 0; dY < 2; ++dY) {
						double xCur = x0;
						double zCur = z0;
						double xNext = (x1 - x0) * 0.25D;
						double zNext = (z1 - z0) * 0.25D;

						for (int dX = 0; dX < 4; ++dX) {
							double pointDiff = (zCur - xCur) * 0.25D;
							double check = xCur;

							for (int dZ = 0; dZ < 4; ++dZ) {
								if (check > 0.0D) {
									primer.setBlockState(chunkX*4 + dX, chunkY * 2 + dY, chunkZ*4 + dZ, AIR);
								}
								check += pointDiff;
							}

							xCur += xNext;
							zCur += zNext;
						}

						x0 += x0Up;
						z0 += z0Up;
						x1 += x1Up;
						z1 += z1Up;
					}
				}
			}
		}
	}
	
	private void generateUnderside(int x, int y, int z) {
		this.underA = this.octPub2.generateNoiseOctaves(this.underA, x, z, 5, 5, 
				(double) this.settings.depthNoiseScaleX, 
				(double) this.settings.depthNoiseScaleZ,
				(double) this.settings.depthNoiseScaleExponent);
		float f = this.settings.coordinateScale;
		float f1 = this.settings.heightScale;
		this.underB = this.octPri3.generateNoiseOctaves(this.underB, x, y, z, 5, 33, 5, 
				(double) (f / this.settings.mainNoiseScaleX),
				(double) (f1 / this.settings.mainNoiseScaleY), 
				(double) (f / this.settings.mainNoiseScaleZ));
		this.underC = this.octPri1.generateNoiseOctaves(this.underC, x, y, z, 5, 33, 5, 
				(double) f, (double) f1, (double) f);
		this.underD = this.octPri2.generateNoiseOctaves(this.underD, x, y, z, 5, 33, 5, 
				(double) f, (double) f1, (double) f);
		
		int i = 0;
		int j = 0;
		float f2, f3, f4;
		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				f2 = f3 = f4 = 0.0F;
				float baseHeight = 0.25F * this.settings.biomeDepthWeight + this.settings.biomeDepthOffSet;
				float heightVariation = 0.1F * this.settings.biomeScaleWeight + this.settings.biomeScaleOffset;
				if (this.biomesForGeneration[k+2+(l+2)*10].getBaseHeight() < 0) {
					//heightVariation *= 10;
					baseHeight *= 10;
				}
				for (int m = -2; m <= 2; ++m) {
					for (int n = -2; n <= 2; ++n) {
						float f7 = this.field_185999_r[m+2 + (n+2)*5] / (baseHeight + 2.0F);
						f2 += heightVariation * f7;
						f3 += baseHeight * f7;
						f4 += f7;
					}
				}
				f2 = (f2 / f4) * 0.9F + 0.1F;
				f3 = ((f3 * 4.0F / f4) - 1.0F) / 8.0F;
				double d7 = this.underA[j] / 8000.0D;
				if (d7 < 0.0D) {
					d7 = -d7 * 0.3D;
				}
				d7 = d7 * 3.0D - 2.0D;
				if (d7 < 0.0D) {
					d7 = d7 / 2.0D;
					if (d7 < -1.0D) {
						d7 = -1.0D;
					}
					d7 = d7 / 1.4D;
					d7 = d7 / 2.0D;
				} else {
					if (d7 > 1.0D) {
						d7 = 1.0D;
					}
					d7 = d7 / 8.0D;
				}
				++j;
				
				f3 += (float) (d7 * 0.2F);
				f3 *= this.settings.baseSize / 2.0F;
				f3 += this.settings.baseSize;
				for (int dY = 0; dY < 33; ++dY) {
					double d1 = ((double) dY - f3) * 128.0D / (256.0D * f2);
					if (d1 < 0.0D) {
						d1 *= 4.0D;
					}
					double d2 = this.underC[i] / (double) this.settings.lowerLimitScale;
					double d3 = this.underD[i] / (double) this.settings.upperLimitScale;
					double d4 = this.underB[i] / 22.0D;
					this.underMap[i] = MathHelper.clampedLerp(d2, d3, d4) - d1;
					
					++i;
				}
			}
		}
	}
}