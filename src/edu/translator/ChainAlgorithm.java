package edu.translator;


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
    protected boolean tryToWrite(String word, int attempt){
        HASH_MISS_COUNT = 0;
        int hashCode = toHash(word) % TABLE_SIZE;
        if(hashTable[hashCode].equals("$")){
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

    @Override
    protected boolean tryToWriteMock(String word, int attempt){
        HASH_MISS_COUNT = 0;
        int hashCode = toHash(word) % TABLE_SIZE;
        if(hashTable[hashCode].equals("$")){
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


    @Override
    protected void fillInPercentage(int percentage){
        while(measureTableFilling() > 100 - percentage) {

            int attempt = 0;
            String word = generateWord();
            tryToWrite(word, attempt);
            attempt += HASH_MISS_COUNT;

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
    public void test(){

        System.out.println("CHAIN METHOD");

        super.test();
    }
}
