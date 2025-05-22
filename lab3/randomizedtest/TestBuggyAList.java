package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> bl = new BuggyAList<>();
        AListNoResizing<Integer> al = new AListNoResizing<>();

       bl.addLast(4);
       bl.addLast(5);
       bl.addLast(6);
       bl.removeLast();
       al.addLast(4);
       al.addLast(5);
       al.addLast(6);
       al.removeLast();

       assertEquals(al,bl);

    }
}
