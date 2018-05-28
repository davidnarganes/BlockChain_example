/*
 * MIT License
 *
 * Copyright (c) 2018 David Narganes

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

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
