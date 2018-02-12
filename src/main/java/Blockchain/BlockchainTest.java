package Blockchain;

/**
 * Created by Antonio on 2018-02-10.
 */
public class BlockchainTest {

    public static void main (String[] args){
        Blockchain bc = new Blockchain();
        System.out.println(bc.getLastBlock());
        bc.addNewCurrencyTransaction("Me", "You", 10, "asdf");
        bc.addNewCurrencyTransaction("Me", "You", 10, "asdf");
        bc.addNewBlock("sdfsd");
        System.out.println(bc);
    }

}
