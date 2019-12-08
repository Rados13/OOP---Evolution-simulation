//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Arrays;
//
//import static org.junit.Assert.*;
//
//public class Vector2dTest {
//
//    @Test
//    public void checkEquals() {
//        Assert.assertEquals(new Vector2d(-1,-1),new Vector2d(-1,-1));
//        Assert.assertEquals(new Vector2d(-4,2),new Vector2d(-4,2));
//        Assert.assertEquals(new Vector2d(2,-4),new Vector2d(2,-4));
//        Assert.assertEquals(new Vector2d(3,3),new Vector2d(3,3));
//    }
//
//    @Test
//    public void checkToString() {
//        Assert.assertEquals(new Vector2d(-1,-1).toString(),"(-1,-1)");
//        Assert.assertEquals(new Vector2d(-4,2).toString(),"(-4,2)");
//        Assert.assertEquals(new Vector2d(2,-4).toString(),"(2,-4)");
//        Assert.assertEquals(new Vector2d(3,3).toString(),"(3,3)");
//    }
//
//    @Test
//    public void precedes() {
//        Assert.assertTrue(new Vector2d(-1,-1).precedes(new Vector2d(1,1)));
//        Assert.assertTrue(new Vector2d(-4,2).precedes(new Vector2d(-4,4)));
//        Assert.assertTrue(new Vector2d(2,-4).precedes(new Vector2d(4,-4)));
//        Assert.assertTrue(new Vector2d(3,3).precedes(new Vector2d(3,3)));
//    }
//
//    @Test
//    public void follows() {
//        Assert.assertTrue(new Vector2d(-1,-1).follows(new Vector2d(-2,-2)));
//        Assert.assertTrue(new Vector2d(-4,2).follows(new Vector2d(-4,1)));
//        Assert.assertTrue(new Vector2d(2,-4).follows(new Vector2d(2,-5)));
//        Assert.assertTrue(new Vector2d(3,3).follows(new Vector2d(3,3)));
//    }
//
//    @Test
//    public void upperRight() {
//        Assert.assertEquals(new Vector2d(-1,-1).upperRight(new Vector2d(1,1)),new Vector2d(1,1));
//        Assert.assertEquals(new Vector2d(-4,2).upperRight(new Vector2d(2,-4)),new Vector2d(2,2));
//        Assert.assertEquals(new Vector2d(2,-4).upperRight(new Vector2d(-2,4)),new Vector2d(2,4));
//        Assert.assertEquals(new Vector2d(4,4).upperRight(new Vector2d(4,4)),new Vector2d(4,4));
//    }
//
//    @Test
//    public void lowerLeft() {
//        Assert.assertEquals(new Vector2d(-1,-2).lowerLeft(new Vector2d(-2,-1)),new Vector2d(-2,-2));
//        Assert.assertEquals(new Vector2d(-4,2).lowerLeft(new Vector2d(-4,-4)),new Vector2d(-4,-4));
//        Assert.assertEquals(new Vector2d(2,-4).lowerLeft(new Vector2d(-2,-4)),new Vector2d(-2,-4));
//        Assert.assertEquals(new Vector2d(2,4).lowerLeft(new Vector2d(4,2)),new Vector2d(2,2));
//    }
//
//    @Test
//    public void add() {
//        Assert.assertEquals(new Vector2d(-1,-1).add(new Vector2d(1,1)),new Vector2d(0,0));
//        Assert.assertEquals(new Vector2d(-4,2).add(new Vector2d(2,-4)),new Vector2d(-2,-2));
//        Assert.assertEquals(new Vector2d(2,-4).add(new Vector2d(-2,4)),new Vector2d(0,0));
//        Assert.assertEquals(new Vector2d(2,4).add(new Vector2d(4,2)),new Vector2d(6,6));
//    }
//
//    @Test
//    public void substract() {
//        Assert.assertEquals(new Vector2d(-1,-1).subtract(new Vector2d(1,1)),new Vector2d(-2,-2));
//        Assert.assertEquals(new Vector2d(-4,2).subtract(new Vector2d(2,-4)),new Vector2d(-6,6));
//        Assert.assertEquals(new Vector2d(2,-4).subtract(new Vector2d(-2,4)),new Vector2d(4,-8));
//        Assert.assertEquals(new Vector2d(2,4).subtract(new Vector2d(4,2)),new Vector2d(-2,2));
//    }
//
//    @Test
//    public void equalsOpposite() {
//        Assert.assertEquals(new Vector2d(-1,-1).opposite(),new Vector2d(1,1));
//        Assert.assertEquals(new Vector2d(-4,2).opposite(),new Vector2d(4,-2));
//        Assert.assertEquals(new Vector2d(2,-4).opposite(),new Vector2d(-2,4));
//        Assert.assertEquals(new Vector2d(3,3).opposite(),new Vector2d(-3,-3));
//    }
//}