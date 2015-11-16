/**
 * Created by wsgreen on 11/15/15.
 */

public class Kicker {
  private static final String spamTrain = "./spam_detection/train_email.txt";
  private static final String spamTest = "./spam_detection/test_email.txt";

  private static final String reviewTrain = "./sentiment/rt-train.txt";
  private static final String reviewTest = "./sentiment/rt-test.txt";

  public static void main(String[] args) {
    /*MultinomialNaiveBayes mnb = new MultinomialNaiveBayes(spamTrain);
    mnb.testModel(spamTest);
    mnb.displayConfusionMatrix();

    MultinomialNaiveBayes mnb2 = new MultinomialNaiveBayes(reviewTrain);
    mnb2.testModel(reviewTest);
    mnb2.displayConfusionMatrix();*/

    BernolliNaiveBayes bnb = new BernolliNaiveBayes(spamTrain);
    bnb.testModel(spamTest);
    bnb.displayConfusionMatrix();

    BernolliNaiveBayes bnb2 = new BernolliNaiveBayes(reviewTrain);
    bnb2.testModel(reviewTest);
    bnb2.displayConfusionMatrix();
  }
}
