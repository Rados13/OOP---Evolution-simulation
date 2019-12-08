//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Arrays;
//
//public class ParserTest {
//    MoveDirection[] result = {MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
//            MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
//            MoveDirection.LEFT};
//    OptionsParser pars = new OptionsParser();
//    String[] args = {"r", "f", "forward", "f", "r", "b", "b", "backward", "left"};
//
//    @Test
//    public void Orientation() {
//        MoveDirection[] tab = pars.parse(args);
//        Assert.assertTrue(Arrays.deepEquals(tab, result));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void whenExceptionThrown_thenExpectationSatisfied() {
//        String[] arg = {" v"};
//        MoveDirection[] tab= pars.parse(arg);
//    }
//}