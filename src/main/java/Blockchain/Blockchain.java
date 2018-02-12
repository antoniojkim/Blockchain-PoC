package Blockchain;

import Blockchain.Transaction.CurrencyTransaction;
import Blockchain.Transaction.Transaction;
import Byte_Encryption.ByteArrayOperations;
import Byte_Encryption.ByteEncryption;
import Byte_Encryption.ByteKeystore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Antonio on 2018-02-10.
 */
public class Blockchain {

    private final AtomicLong counter = new AtomicLong();

    private ByteEncryption be = null;

    private List<Block> blockChain;
    private List<Transaction> currentTransactions = new ArrayList<>();

    public Blockchain (){
        byte[] firstHash = ByteKeystore.generateKey(64);
        ByteEncryption be = new ByteEncryption(firstHash);
        byte[] privateKey = ByteKeystore.generateKey(64);

        System.out.println("The blockchain's private key is:   "+ByteArrayOperations.bytesToUnicode(privateKey));
        System.out.println("Keep this private key safe.");

        blockChain = new ArrayList<Block>();
        blockChain.add(new Block(
                counter.incrementAndGet(), null,
                ByteArrayOperations.bytesToHex(be.hash(privateKey)),
                ByteArrayOperations.bytesToHex(firstHash)));

        this.be = new ByteEncryption(privateKey);
    }

    public Block addNewBlock(String proof){
        return addNewBlock(proof, null);
    }
    public Block addNewBlock(String proof, String previousHash){
        if (be != null && proof != null) {
            Block block = new Block(
                    counter.incrementAndGet(),
                    currentTransactions,
                    proof,
                    previousHash != null ? previousHash : generateHash(getLastBlock()));
            blockChain.add(block);
            currentTransactions = new ArrayList<Transaction>();
            return null;
        }
        return null;
    }

    public boolean addNewCurrencyTransaction(String sender, String recipient, double amount, String hash){
        if (sender != null && recipient != null && amount > 0 && hash != null){
            return addNewTransaction(new CurrencyTransaction(sender, recipient, amount, hash));
        }
        return false;
    }
    public boolean addNewTransaction(Transaction transaction){
        if (transaction != null){
            currentTransactions.add(transaction);
            return true;
        }
        return false;
    }
    public List<Transaction> getCurrentTransactions(){
        return currentTransactions;
    }

    public List<Block> getChain(){
        return blockChain;
    }
    public int getLength(){
        return blockChain.size();
    }
    public Block getLastBlock(){
        if (!blockChain.isEmpty()){
            return blockChain.get(blockChain.size()-1);
        }
        return null;
    }

    public String generateHash(Block block){ // = new ByteEncryption(privateKey);
        if (be != null && block != null) {
            return be.hashString(block.toString());
        }
        return null;
    }

    public String proofOfWork(String previousProof){
        if (be != null) {
            long proof = 0;
            while (!isValidProof(previousProof, Long.toHexString(proof))){
                ++proof;
            }
            return Long.toHexString(proof);
        }
        return null;
    }

    public boolean isValidProof(String previousProof, String proof){
        byte[] guess = be.hash(previousProof+proof);
        return guess[0] == Byte.MIN_VALUE;// && guess[1] == Byte.MAX_VALUE;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Block block : blockChain){
            sb.append("\n");
            sb.append(block.toString("\t"));
        }
        sb.append("\n}");
        return sb.toString();
    }

}
