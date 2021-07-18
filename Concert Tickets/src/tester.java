import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.text.DecimalFormat;

public class tester {
    static long mod = (long) 1e9 + 7;
    static long mod1 = 998244353;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter("output.txt", StandardCharsets.UTF_8);


        int t = 1;
        while (t-- > 0) {

            int n = in.nextInt();
            int m = in.nextInt();
            TreeMap<Long, Integer> tmap = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                long x = in.nextLong();
                if (tmap.containsKey(x))
                    tmap.put(x, tmap.get(x) + 1);
                else
                    tmap.put(x, 1);
            }
            for (int i = 0; i < m; i++) {
                long x = in.nextLong();
                if (tmap.containsKey(x)) {
                    out.println(x);
                    tmap.put(x, tmap.get(x) - 1);
                    if (tmap.get(x) == 0)
                        tmap.remove(x);
                } else {
                    if (tmap.lowerKey(x) == null)
                        out.println(-1);
                    else {
                        long z = tmap.lowerKey(x);
                        out.println(z);
                        tmap.put(z, tmap.get(z) - 1);
                        if (tmap.get(z) == 0)
                            tmap.remove(z);
                    }
                }
            }
        }
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

    static int upper_bound(int[] arr, int n, int x) {
        int mid;
        int low = 0;
        int high = n;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (x >= arr[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    static int lower_bound(int[] arr, int n, int x) {
        int mid;
        int low = 0;
        int high = n;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (x <= arr[mid])
                high = mid;
            else
                low = mid + 1;
        }
        return low;
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

        public InputReader(InputStream stream) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader("input.txt"));
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