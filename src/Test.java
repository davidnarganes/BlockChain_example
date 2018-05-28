public class Test {
    public void Test(){
    }

    public static void main(String[] args){
        BlockChain test = new BlockChain();

        System.out.println("Mining block 1...");
        test.addBlock("30-05-18", "sender: Freddy, receiver: David, amount: 20");

        System.out.println("Mining block 2...");
        test.addBlock("01-06-18", "sender: Peter, receiver: David, amount: 30");

        System.out.println("Mining block 3...");
        test.addBlock("03-06-18", "sender: Juan, receiver: Pepe, amount: 10");

        System.out.println("Is BlockChain valid? " + test.isChainValid());
    }
}
