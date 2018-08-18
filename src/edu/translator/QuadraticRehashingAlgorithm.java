package edu.translator;


public class QuadraticRehashingAlgorithm extends HashingAlgorithm {

    private int A_COEFFICIENT = 3;
    private int B_COEFFICIENT = 4;
    private int C_COEFFICIENT = 3;

    public QuadraticRehashingAlgorithm(){
        super();
    }

    @Override
    protected boolean tryToWrite(String word, int attempt){
        int hashCode;
        if(attempt != 0)
            hashCode = (toHash(word) + A_COEFFICIENT*attempt*attempt +
                    B_COEFFICIENT*attempt + C_COEFFICIENT) % TABLE_SIZE;
        else
            hashCode = toHash(word) % TABLE_SIZE;
        if(hashTable[Math.abs(hashCode)].equals("$")){
            hashTable[Math.abs(hashCode)] = word;
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryToWriteMock(String word, int attempt){
        int hashCode;
        if(attempt != 0)
            hashCode = (toHash(word) + A_COEFFICIENT*attempt*attempt +
                    B_COEFFICIENT*attempt + C_COEFFICIENT) % TABLE_SIZE;
        else
            hashCode = toHash(word) % TABLE_SIZE;
        if(hashTable[Math.abs(hashCode)].equals("$")){
            return true;
        }
        return false;
    }

    @Override
    protected void fillInPercentage(int percentage){
        while(measureTableFilling() > 100 - percentage) {

            int attempt = 0;
            String word = generateWord();
            while(!tryToWrite(word, attempt)) {
                attempt++;
            }

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
            while(!tryToWriteMock(word, attempt)) {
                attempt++;
            }
            tryCount += attempt + 1;

        }

        return tryCount*1.0/wordCount;
    }

    @Override
    public void test(){

        System.out.println("QUADRATIC REHASHING");

        super.test();
    }
}
