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
    // PUBLIC ACCESSORS
    //
    //---------------------------------------------------------------------------

    @Override
    public boolean isDouble() {
        return true;
    }
}
