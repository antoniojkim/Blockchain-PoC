package Blockchain;

import Blockchain.Transaction.CurrencyTransaction;
import Blockchain.Transaction.Transaction;

import java.util.List;

/**
 * Created by Antonio on 2018-02-10.
 */
public class Block {

    private final long index;
    private final long timeStamp; // in Nanoseconds

    private final List<Transaction> transactionList;

    private final String proof;
    private final String previousHash;

    public Block(long index, List<Transaction> transactionList, String proof, String previousHash){
        this.index = index;
        this.timeStamp = System.nanoTime();
        this.transactionList = transactionList;
        this.proof = proof;
        this.previousHash = previousHash;
    }

    public long getIndex(){
        return index;
    }

    public long getTimeStamp(){
        return timeStamp;
    }

    public List<Transaction> getTransactionList(){
        return transactionList;
    }

    public String getProof() {
        return proof;
    }

    public String getPreviousHash(){
        return previousHash;
    }


    public String toString(){
        return toString("");
    }
    public String toString(String prefix){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append("{\n");
        sb.append(prefix);
        sb.append("\t{index:");
        sb.append(getIndex());
        sb.append("},\n");
        sb.append(prefix);
        sb.append("\t{timestamp:");
        sb.append(getTimeStamp());
        if (transactionList != null){
            sb.append("},\n");
            sb.append(prefix);
            sb.append("\t{transactions:{");
            for (Transaction transaction : getTransactionList()){
                sb.append("\n");
                sb.append(prefix);
                sb.append("\t\t");
                sb.append(transaction.toString());
            }
            sb.append("}");
        }
        sb.append("},\n");
        sb.append(prefix);
        sb.append("\t{proof:");
        sb.append(getProof());
        sb.append("},\n");
        sb.append(prefix);
        sb.append("\t{previousHash:");
        sb.append(getPreviousHash());
        sb.append("}\n");
        sb.append(prefix);
        sb.append("}");
        return sb.toString();
    }

}
