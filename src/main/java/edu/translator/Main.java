package edu.translator;

import edu.translator.strategy.*;

public class Main {

    public static void main(String[] args) {

        HashingAlgorithm hashAlgorithm = new LinearRehashingAlgorithm();
        hashAlgorithm.test();

        hashAlgorithm = new QuadraticRehashingAlgorithm();
        hashAlgorithm.test();

        hashAlgorithm = new RandomRehashingAlgorithm();
        hashAlgorithm.test();

        hashAlgorithm = new ChainAlgorithm();
        hashAlgorithm.test();

    }
}
