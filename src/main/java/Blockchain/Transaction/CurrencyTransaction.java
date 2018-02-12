package Blockchain.Transaction;

import Blockchain.Blockchain;

/**
 * Created by Antonio on 2018-02-10.
 */
public class CurrencyTransaction extends Transaction {

    private String sender = null;
    private String recipient = null;

    private double amount = -1;

    public CurrencyTransaction() {
        super("Currency Transaction");
    }
    public CurrencyTransaction(String sender, String recipient, double amount, String hash) {
        super("Currency Transaction");
        setSender(sender);
        setRecipient(recipient);
        setAmount(amount);
        setHash(hash);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        if (this.sender == null) this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        if (this.recipient == null) this.recipient = recipient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (this.amount == -1 && amount > 0) this.amount = amount;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(sender);
        sb.append("->");
        sb.append(recipient);
        sb.append(":");
        sb.append(amount);
        sb.append("}");
        return sb.toString();
    }

    public boolean hasRequiredFields(){
        return sender != null && recipient != null && amount > 0 && getHash() != null;
    }
}
