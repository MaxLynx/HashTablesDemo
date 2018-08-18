package edu.translator.strategy;


import edu.translator.entities.Symbol;

import java.util.ArrayList;
import java.util.List;

public class ChainAlgorithm extends HashingAlgorithm {

    private int POINT_FREE = 1;

    private int HASH_MISS_COUNT = 0;

    private List<Symbol> symbols;

    public ChainAlgorithm(){
        super();
        symbols = new ArrayList<>();
    }

    @Override
    public void test(){

        System.out.println(propertiesResourceBundle.getString("chain.method.name"));

        super.test();
    }

    @Override
    protected void fillInPercentage(int percentage){
        while(measureTableFilling() > 100 - percentage) {

            String word = generateWord();
            tryToWrite(word);

        }
    }

    @Override
    protected double fillInMock(){
        int wordCount = 0;
        int tryCount = 0;

        while(wordCount < 100) {

            wordCount++;
            int attempt = 0;
            String word = generateWord();
            tryToWriteMock(word, attempt);
            attempt += HASH_MISS_COUNT;
            tryCount += attempt + 1;

        }

        return tryCount*1.0/wordCount;
    }

    @Override
    protected boolean tryToWrite(String word, int attempt){
        HASH_MISS_COUNT = 0;
        int hashCode = toHash(word) % TABLE_SIZE;
        if(hashTable[hashCode].equals(TABLE_FILLER)){
            hashTable[hashCode] = "" + POINT_FREE;
            symbols.add(new Symbol(word, 0));
            POINT_FREE++;
            return true;
        }
        else{
            int reference = Integer.parseInt(hashTable[hashCode]);
            while(symbols.get(reference - 1).getReference() != 0) {
                HASH_MISS_COUNT++;
                reference = symbols.get(reference - 1).getReference();
            }
            symbols.get(reference - 1).setReference(POINT_FREE);
            symbols.add(new Symbol(word, 0));
            POINT_FREE++;
            return false;
        }
    }

    private void tryToWrite(String word){
        tryToWrite(word, 0);
    }

    @Override
    protected boolean tryToWriteMock(String word, int attempt){
        HASH_MISS_COUNT = 0;
        int hashCode = toHash(word) % TABLE_SIZE;
        if(hashTable[hashCode].equals(TABLE_FILLER)){
            return true;
        }
        else{
            int reference = Integer.parseInt(hashTable[hashCode]);
            while(symbols.get(reference - 1).getReference() != 0) {
                HASH_MISS_COUNT++;
                reference = symbols.get(reference - 1).getReference();
            }
            return false;
        }
    }

}
