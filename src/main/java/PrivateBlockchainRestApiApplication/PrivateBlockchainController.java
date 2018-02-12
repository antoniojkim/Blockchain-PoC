package PrivateBlockchainRestApiApplication;

import Blockchain.Transaction.CurrencyTransaction;
import Blockchain.Blockchain;
import Blockchain.Block;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Antonio on 2018-02-10.
 */
@RestController
public class PrivateBlockchainController {

    private final AtomicLong counter = new AtomicLong();
    private Blockchain bc = new Blockchain();

    //http://localhost:8080/transactions/new
    @RequestMapping(method = RequestMethod.POST, value = "/transactions/currency/new") // @RequestParam(value="index", defaultValue = "-1") String strIndex
    public ResponseEntity<CurrencyTransaction> addNewTransaction(@RequestBody CurrencyTransaction currencyTransaction){
        if (currencyTransaction == null || !currencyTransaction.hasRequiredFields())
            return new ResponseEntity<CurrencyTransaction>(currencyTransaction, HttpStatus.BAD_REQUEST);
        bc.addNewTransaction(currencyTransaction);
        return new ResponseEntity<CurrencyTransaction>(currencyTransaction, HttpStatus.OK);
    }

    //http://localhost:8080/mine
    @RequestMapping("/mine") // @RequestParam(value="index", defaultValue = "-1") String strIndex
    public ResponseEntity<Block> mine(){
        String previousProof = bc.getLastBlock().getProof();
        String proof = bc.proofOfWork(previousProof);

        bc.addNewCurrencyTransaction("blockchain", "you", 1, "asdf");

        String previousHash = bc.generateHash(bc.getLastBlock());

        Block newBlock = bc.addNewBlock(proof, previousHash);

        return new ResponseEntity<Block>(newBlock, HttpStatus.OK);
    }

    //http://localhost:8080/chain
    @RequestMapping(method = RequestMethod.GET, value = "/chain", produces = "application/json") // @RequestParam(value="index", defaultValue = "-1") String strIndex
    public ResponseEntity<Blockchain> chain(){
        return new ResponseEntity<Blockchain>(bc, HttpStatus.OK);
    }

}
