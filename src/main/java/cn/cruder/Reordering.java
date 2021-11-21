package cn.cruder;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * @Author: cruder
 * @Date: 2021/11/20/13:48
 */
public class Reordering {
    /**
     * 测试用例1
     * id = "1, 0"  逗号后面有空格
     */
    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "1, 0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "INTERESTING")
    @State
    public static class Case1 {
        int x;
        int y;

        /**
         * 对于每一个 @Actor 修饰的方法 都会开一个线程去执行
         */
        @Actor
        public void actor1() {
            x = 1;
            y = 1;
        }

        @Actor
        public void actor2(II_Result result) {
            result.r1 = y;
            result.r2 = x;
        }
    }

    /**
     * 测试用例2
     */
    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "1, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case2 {
        int x;
        volatile int y;

        /**
         * 对于每一个 @Actor 修饰的方法 都会开一个线程去执行
         */
        @Actor
        public void actor1() {
            x = 1;
            y = 1;
        }

        @Actor
        public void actor2(II_Result result) {
            result.r1 = y;
            result.r2 = x;
        }


    }


    /**
     * 测试用例2
     */
    @JCStressTest
    @Outcome(id = {"0, 0", "1, 1", "0, 1"}, expect = Expect.ACCEPTABLE, desc = "ACCEPTABLE")
    @Outcome(id = "1, 0", expect = Expect.FORBIDDEN, desc = "FORBIDDEN")
    @State
    public static class Case3 {
        volatile int x;
        int y;

        /**
         * 对于每一个 @Actor 修饰的方法 都会开一个线程去执行
         */
        @Actor
        public void actor1() {
            x = 1;
            y = 1;
        }

        @Actor
        public void actor2(II_Result result) {
            result.r1 = y;
            result.r2 = x;
        }
    }
}
