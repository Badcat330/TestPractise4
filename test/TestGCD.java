import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestGCD {
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
        x = rand.nextInt() + 1;
        y = rand.nextInt() + 1;
        answer = gcd.gcd(x, y);
    }

    @BeforeAll
    static void InitOnce() {
        rand = new Random();
    }

    @Test
    void TrevialTest() {
        Assertions.assertEquals(6, gcd.gcd(18, 42));
    }

    @Test
    void DivideLeftTest() {
        Assertions.assertEquals(0, x % answer);
    }

    @Test
    void DivideRightTest() {
        Assertions.assertEquals(0, y % answer);
    }

    @Test
    void AnswerPositiveTest() {
        Assertions.assertTrue(answer > 0);
    }

    @Test
    void MaxAnswerTest() {
        int min = Math.min(x, y);
        int max = 1;
        for (int i = 2; i <= min; i++) {
            if ((x % i) == 0 && (y % i) == 0) {
                max = i;
            }
        }

        Assertions.assertEquals(max, answer);
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
    }

    @Test
    void EqualTest() {
        Assertions.assertEquals(2, gcd.gcd(2, 2));
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
