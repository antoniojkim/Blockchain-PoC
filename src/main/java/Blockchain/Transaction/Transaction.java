package Blockchain.Transaction;

/**
 * Created by Antonio on 2018-02-11.
 */
public abstract class Transaction {

    private final String transactionType;
    private String hash = null;

    public Transaction(String transactionType){
        this.transactionType = transactionType;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        if (this.hash == null) this.hash = hash;
    }

    public String getTransactionType(){
        return transactionType;
    }

    public abstract boolean hasRequiredFields();

}
