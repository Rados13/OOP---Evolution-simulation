//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.Arrays;
//
//
//public class AnimalTest {
//    String[] args = {"r", "b", "l",
//            "f", "b", "f",
//            "l", "b", "f",
//            "f", "b", "r",
//            "f", "b", "l",
//            "f", "l", "l",};
//
//    MoveDirection[] result={MoveDirection.RIGHT,MoveDirection.BACKWARD,MoveDirection.LEFT,
//            MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.FORWARD,
//            MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.FORWARD,
//            MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.RIGHT,
//            MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,
//            MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.LEFT,};
//    OptionsParser pars=new OptionsParser();
//
//    @Test
//    public void Orientation(){
//        MoveDirection[] tab=pars.parse(args);
//        Assert.assertTrue(Arrays.deepEquals(tab,result));
//        int i=0;
//        //Idzie w różne kierunki i cały czas sprawdza czy działa
//        anim.move(tab[i++]);
//        Assert.assertEquals(anim.toString(),"Wschod (2,2)");
//        anim.move(tab[i++]);
//        anim.move(tab[i++]);
//        anim.move(tab[i++]);
//        Assert.assertEquals(anim.toString(),"Wschod (4,2)");
//        anim.move(tab[i++]);
//        Assert.assertEquals(anim.toString(),"Poludnie (4,2)");
//        anim.move(tab[i++]);
//        anim.move(tab[i++]);
//        anim.move(tab[i++]);
//        Assert.assertEquals(anim.toString(),"Poludnie (4,4)");
//        anim.move(tab[i]);
//        Assert.assertEquals(anim.toString(),"Wschod (4,4)");
//    }
//}
