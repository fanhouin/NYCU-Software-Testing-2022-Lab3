import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class PriorityQueueTest {
    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("ArraystreamProvider")
    public void PriorityQueue_RunTest(int [] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int idx = 0;
        Integer s;
        int[] result = new int[random_array.length];
        for(int i = 0; i < random_array.length; i++){
            test.offer(random_array[i]);
        }
        for(int i = 0; i < result.length; i++){
            result[i] = test.poll();
        }

        assertArrayEquals(result, correct_array);
    }

    private static Stream<Arguments> ArraystreamProvider(){
       return Stream.of(
               Arguments.of(new int[]{4,7,3,1}, new int[]{1,3,4,7}),
               Arguments.of(new int[]{-1,7,1,3}, new int[]{-1,1,3,7}),
               Arguments.of(new int[]{100,-99,50}, new int[]{-99,50,100}),
               Arguments.of(new int[]{8,7,0,1}, new int[]{0,1,7,8}),
               Arguments.of(new int[]{-666,-87,444,1,5}, new int[]{-666,-87,1,5,444})
       );
        // return Stream.of(
        //         Arguments.of(new int[]{4,7,3,1}, new int[]{1,3,4,7}),
        //         Arguments.of(new int[]{-1,7,1,3}, new int[]{-1,1,3,7}),
        //         Arguments.of(new int[]{100,-99,50}, new int[]{-99,50,100}),
        //         Arguments.of(new int[]{8,7,0,1}, new int[]{0,1,7,8}),
        //         Arguments.of(new int[]{-666,-87,444,1,5}, new int[]{-777,-87,1,5,444})
        // );
    }

    @Test
    // if {@code initialCapacity} is less than 1
    public void a_IllegalArgumentExceptionTest(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            new PriorityQueue<>(0);
        });
    }

    @Test
    // if the specified collection or any of its elements are null
    public void b_NullPointerExceptionTest(){
        Exception exception = assertThrows(NullPointerException.class, ()->{
            new PriorityQueue<>().offer(null);
        });
    }

    @Test
    public void c_NoSuchElementException(){
        Exception exception = assertThrows(NoSuchElementException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            Iterator val = test.iterator();
            val.next();
        });
    }


}
