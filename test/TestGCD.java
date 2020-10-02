import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestGCD {
    public static final int TEST_COUNT = 100;

    GCD gcd;
    int x, y, answer;
    static Random rand;

    int FibGen(int n) {
        int a = 1;
        int b = 1;
        for (int i = 0; i < n; ++i) {
            int newOne = a + b;
            a = b;
            b = newOne;
        }

        return b;
    }

    @BeforeEach
    void Init() {
        gcd = new GCD();
        x = rand.nextInt(100) + 1;
        y = rand.nextInt(100) + 1;
        answer = gcd.gcd(x, y);
    }

    @BeforeAll
    static void InitOnce() {
        rand = new Random();
    }

    @Test
    void TrevialTest() {
        Assertions.assertEquals(6, gcd.gcd(18, 42));
        Assertions.assertEquals(6, gcd.gcd(42, 18));
    }

    @Test
    void DivideLeftTest() {
        for (int i = 0; i < TEST_COUNT; i++) {
            Assertions.assertEquals(0, x % answer, String.format("GCD(%s;%s)", x, y));
            Init();
        }
    }

    @Test
    void DivideRightTest() {
        for (int i = 0; i < TEST_COUNT; i++) {
            Assertions.assertEquals(0, y % answer, String.format("GCD(%s;%s)", x, y));
            Init();
        }
    }

    @Test
    void AnswerPositiveTest() {
        for (int i = 0; i < TEST_COUNT; i++) {
            Assertions.assertTrue(answer > 0, String.format("GCD(%s;%s)", x, y));
            Init();
        }
    }

    @Test
    void MaxAnswerTest() {
        for (int j = 0; j < TEST_COUNT; j++) {

            if(x < 0) x = -x;
            if(y < 0) y = -y;

            int min = Math.min(x, y);
            int max = 1;
            for (int i = 2; i <= min; i++) {
                if ((x % i) == 0 && (y % i) == 0) {
                    max = i;
                }
            }

            Assertions.assertEquals(max, answer, String.format("GCD(%s;%s)", x, y));
            Init();
        }
    }

    @Test
    void NegativeFirstTest() {
        Assertions.assertEquals(6, gcd.gcd(-18, 42));
    }

    @Test
    void NegativeSecondTest() {
        Assertions.assertEquals(6, gcd.gcd(18, -42));
    }

    @Test
    void NegativeBothTest() {
        Assertions.assertEquals(6, gcd.gcd(-18, -42));
    }

    @Test
    void ZeroFirstTest() {
        Assertions.assertEquals(42, gcd.gcd(0, 42));
    }

    @Test
    void ZeroSecondTest() {
        Assertions.assertEquals(18, gcd.gcd(18, 0));
    }

    @Test
    void ZeroBothTest() {
        Assertions.assertEquals(0, gcd.gcd(0, 0));
    }

    @Test
    void SimpleBothTest() {
        Assertions.assertEquals(1, gcd.gcd(8, 7));
        Assertions.assertEquals(1, gcd.gcd(5, 7));
    }

    @Test
    void EqualTest() {
        for (int i = 0; i < TEST_COUNT; i++) {
            Assertions.assertEquals(x, gcd.gcd(x, x), String.format("GCD(%s;%s)", x, x));
            Init();
        }
    }

    @Test
    void DivideOtherFirstTest() {
        Assertions.assertEquals(2, gcd.gcd(2, 8));
    }

    @Test
    void DivideOtherSecondTest() {
        Assertions.assertEquals(2, gcd.gcd(8, 2));
    }

    @Test
    void MaxArgsTest() {
        x = -(int) Math.pow(2, 31);
        y = (int) Math.pow(2, 31) - 1;
        Assertions.assertEquals(1, gcd.gcd(x, y));
    }

    @Test
    void FibTest() {
        x = FibGen(rand.nextInt(100 - 80) + 80);
        y = FibGen(rand.nextInt(100 - 80) + 80);
        answer = gcd.gcd(x, y);
    }
}
