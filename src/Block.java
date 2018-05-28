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


BLOCK CLASS
Blocks are the units of the BlockChain and will contain the info for
the transactions that occured on a defined interval of time
 */

public class Block {

    // Define the properties of the blocks

    private String timestamp;
    private String data;
    private String previousHash;
    private String hash;
    private Long nounce; // Nounce is used to slow down the creation of blocks in the Proof-of-Work

    /* BLOCK CONSTRUCTOR
    It will require:
    1. Time of the transaction
    2. Data containing info about the transactions
    3. Hash of the previous block: block chain
     */

    public Block(String timestamp, String data, String previousHash){
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHMAC();
        this.nounce = 0L;
    }

    // Define some getters and setters
    public String getTimestamp(){
        return this.timestamp;
    }
    public void setTimestamp(String value){
        this.timestamp = value;
    }
    public String getData(){
        return this.data;
    }
    public void setData(String value){
        this.data = value;
    }
    public String getPreviousHash(){
        return this.previousHash;
    }
    public void setPreviousHash(String value){
        this.previousHash = value;
    }
    public String getHash(){
        return this.hash;
    }
    public void setHash(){
        this.hash = calculateHMAC();
    }

    /* PROOF-OF-WORK: MINING
    The aim is to slow down the creation of new blocks by setting the difficulty
    The hash of all blocks has to have a difficult number of "A"s at the end
     */

    public void mineBlock(int difficulty){
        String condition = "";

        for(int i = 0; i < difficulty; i++){
            condition = condition + "A";
        }

        while(!this.getHash().substring(this.getHash().length()-difficulty-1,
                this.getHash().length()-1).endsWith(condition)){
            this.setHash();
            this.nounce++;
        }

        System.out.println("Block mined: " + this.getHash());
    }

    /* CALCULATE HASH-BASED MESSAGE AUTHENTICATION CODE
    The CryptLib library will create an unique hash based on the properties of each block
     */
    public String calculateHMAC(){
        String hmac = "";
        try {
            CryptLib cryptLib = new CryptLib();

            String plainTxt = "|hash|" +this.timestamp + this.nounce;

            hmac = cryptLib.encrypt(plainTxt,this.data,this.previousHash);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hmac;
    }
}
