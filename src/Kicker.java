/**
 * Created by wsgreen on 11/15/15.
 */

public class Kicker {
  private static final String spamTrain = "./spam_detection/train_email.txt";
  private static final String spamTest = "./spam_detection/test_email.txt";

  private static final String reviewTrain = "./sentiment/rt-train.txt";
  private static final String reviewTest = "./sentiment/rt-test.txt";

  private static final String eightTrain = "./8category/8category.training.txt";
  private static final String eightTest = "./8category/8category.testing.txt";


  public static void main(String[] args) {
  /*MultinomialNaiveBayes mnb = new MultinomialNaiveBayes(spamTrain);
    mnb.testModel(spamTest);
    mnb.displayConfusionMatrix();
    //mnb.printTopTwenties();

    MultinomialNaiveBayes mnb2 = new MultinomialNaiveBayes(reviewTrain);
    mnb2.testModel(reviewTest);
    mnb2.displayConfusionMatrix();
    //mnb2.printTopTwenties();*/

    /*BernolliNaiveBayes bnb = new BernolliNaiveBayes(spamTrain);
    bnb.testModel(spamTest);
    bnb.displayConfusionMatrix();
    bnb.printTopTwenties();

    BernolliNaiveBayes bnb2 = new BernolliNaiveBayes(reviewTrain);
    bnb2.testModel(reviewTest);
    bnb2.displayConfusionMatrix();
    bnb2.printTopTwenties();*/

    MultinomialNaiveBayes mnb3 = new MultinomialNaiveBayes(eightTrain);
    mnb3.testModel(eightTest);
    mnb3.displayConfusionMatrix();
    mnb3.printTopTwenties();

    BernolliNaiveBayes bnb3 = new BernolliNaiveBayes(eightTrain);
    bnb3.testModel(eightTest);
    bnb3.displayConfusionMatrix();
    bnb3.printTopTwenties();
  }
}
