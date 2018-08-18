package edu.translator.strategy;


public class RandomRehashingAlgorithm extends HashingAlgorithm {

    private int R_COEFFICIENT = 1;

    public RandomRehashingAlgorithm(){
        super();
    }

    @Override
    public void test(){

        System.out.println(propertiesResourceBundle.getString("random.rehashing.method.name"));

        super.test();
    }

    @Override
    protected void fillInPercentage(int percentage){
        while(measureTableFilling() > 100 - percentage) {

            R_COEFFICIENT = 1;
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

            R_COEFFICIENT = 1;
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
    protected boolean tryToWrite(String word, int attempt){
        int hashCode;
        if(attempt == 0)
            hashCode = toHash(word) % TABLE_SIZE;
        else {
            R_COEFFICIENT = R_COEFFICIENT * 5 % (4 * TABLE_SIZE);
            hashCode = (toHash(word) + R_COEFFICIENT / 4) % TABLE_SIZE;
        }
        if(hashTable[hashCode].equals(TABLE_FILLER)){
            hashTable[hashCode] = word;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected boolean tryToWriteMock(String word, int attempt){
        int hashCode;
        if(attempt == 0)
            hashCode = toHash(word) % TABLE_SIZE;
        else {
            R_COEFFICIENT = R_COEFFICIENT * 5 % (4 * TABLE_SIZE);
            hashCode = (toHash(word) + R_COEFFICIENT / 4) % TABLE_SIZE;
        }
        return hashTable[hashCode].equals(TABLE_FILLER);
    }

}
