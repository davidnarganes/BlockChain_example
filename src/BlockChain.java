import java.util.ArrayList;

/* BLOCKCHAIN CLASS
This class will allow the application to generate a chain of Blocks
and define some methods to check its contents
 */

public class BlockChain {

    // Define the properties of the BlockChain

    private int difficulty;
    private ArrayList<Block> chain = new ArrayList<Block>();

    /* BLOCKCHAIN CONSTRUCTOR
    Each time is called, it will create the genesis block
    The difficulty of creating a new block will be a property
     */
    public BlockChain(){
        this.chain.add(GenesisBlock());
        this.difficulty = 3;
    }

    public Block GenesisBlock(){
        return new Block("27-05-2018", "Genesis block", "0");
    }

    /* RETURN LATEST BLOCK
    Return the last element of the chain casted as Block
     */

    public Block getLatestBlock(){
        return (Block) this.chain.get(this.chain.size()-1);
    }

    /* ADD BLOCKS TO THE CHAIN
    Every new block will get the previous hash
    And will generate a new hash
    Before being added to the chain
     */
    public void addBlock(String timestamp, String data){
        Block newBlock = new Block(timestamp, data, getLatestBlock().getHash());
        newBlock.mineBlock(this.difficulty);
        this.chain.add(newBlock);
    }

    /* CHECK BLOCKCHAIN
    There has to be a way to check if the chain has been brocken
    Loop though the blocks in blockchain to corroborate that
    the sequence of hash is preserved
     */
    public boolean isChainValid(){
        for (int i = 1; i < this.chain.size(); i++){

            Block currentBlock = this.chain.get(i);
            Block previousBlock = this.chain.get(i-1);

            if (currentBlock.getPreviousHash() != previousBlock.getHash()){
                return false;
            }
        }
        return true;
    }
}
