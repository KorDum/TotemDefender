package ru.kordum.totemDefender.common.blocks;

public class BlockDoubleSlab extends BlockSlab {

	//---------------------------------------------------------------------------
	//
	// CONSTRUCTOR
	//
	//---------------------------------------------------------------------------

	public BlockDoubleSlab() {
		super("totemTreeDoubleSlab");
	}

	//---------------------------------------------------------------------------
	//
	// ACCESSORS
	//
	//---------------------------------------------------------------------------

	@Override
	public boolean isDouble() {
		return true;
	}
}
