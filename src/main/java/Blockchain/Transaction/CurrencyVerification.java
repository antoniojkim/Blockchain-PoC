package Blockchain.Transaction;

/**
 * Created by Antonio on 2018-02-11.
 */
public class CurrencyVerification extends Transaction {

    public CurrencyVerification(){
        super("Currency Verification");
    }

    public boolean hasRequiredFields(){
        return false;
    }

}
