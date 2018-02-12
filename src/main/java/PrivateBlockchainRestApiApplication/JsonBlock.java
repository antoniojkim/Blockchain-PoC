package PrivateBlockchainRestApiApplication;

import Blockchain.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 2018-02-10.
 */
public class JsonBlock {

    private final long id;
    private List<Block> blocks = new ArrayList<>();

    public JsonBlock (long id){
        this.id = id;
    }

    public List<Block> getBlocks(){
        return blocks;
    }

    public void addBlock(Block block){
        blocks.add(block);
    }

}
