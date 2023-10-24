package q2.csvfilesource;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import p3.csvfilesource.MySum;

public class TestComputation_CsvFileSource {

   // TODO: Write your answer to complete this test case
   @ParameterizedTest
   @CsvFileSource(resources = "computation_dataset.csv")
   public void testGetDiscount(int id, int rate, int expected) throws Exception {
	  Computation com = new Computation();
      int result = com.getDiscount(id, rate);
      Assert.assertEquals(expected, result);

   }
}
