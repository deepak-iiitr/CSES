import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int i = 0; i < t; i++)
            Solution.solve(in, out);
        out.close();
    }

    static final Random random = new Random();

    static void ruffleSort(int[] a) {
        int n = a.length;//shuffle, then sort
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    static long gcd(long x, long y) {
        if (x == 0)
            return y;
        if (y == 0)
            return x;
        long r = 0, a, b;
        a = Math.max(x, y);
        b = Math.min(x, y);
        r = b;
        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }

    static long modulo(long a, long b, long c) {
        long x = 1, y = a % c;
        while (b > 0) {
            if (b % 2 == 1)
                x = (x * y) % c;
            y = (y * y) % c;
            b = b >> 1;
        }
        return x % c;
    }

    public static void debug(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }

    static String printPrecision(double d) {
        DecimalFormat ft = new DecimalFormat("0.00000000000");
        return String.valueOf(ft.format(d));
    }

    static int countBit(long mask) {
        int ans = 0;
        while (mask != 0) {
            mask &= (mask - 1);
            ans++;
        }
        return ans;
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public int[] readArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }
    }
}

class Solution {
    static long mod = (long) 1e9 + 7;
    static long mod1 = 998244353;

    public static void solve(Main.InputReader in, PrintWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();
        if ((a + b) % 3 == 0) {
           long max=Math.max(a,b);
           long min=Math.min(a,b);
           long diff=max-min;
           max-=2*diff;
           min-=diff;
           if(min>=0 && max>=0) {
               if (min == 0 && max != 0)
                   out.println("NO");
               else {
                   if ((max + min) % 3 == 0)
                       out.println("YES");
                   else
                       out.println("NO");
               }
           }else
               out.println("NO");
        } else
            out.println("NO");
    }
}