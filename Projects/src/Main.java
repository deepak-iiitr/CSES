import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static long mod = (long) 1e9 + 7;
    static long mod1 = 998244353;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t = 1;
        while (t-- > 0) {

            int n = in.nextInt();
            ArrayList<Pair> ll = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int cost = in.nextInt();
                ll.add(new Pair(x, y, cost));
            }

            long[] dp = new long[n + 1];
            ll.sort(new sortByX());
            TreeMap<Integer, Long> tmap = new TreeMap<>();
            long ans = 0;
            for (int i = n - 1; i >= 0; i--) {
                long max = 0;
                int key = ll.get(i).y;
                if (tmap.higherKey(key) != null)
                    max = tmap.get(tmap.higherKey(key));
                dp[i] = max + ll.get(i).cost;
                ans = Math.max(dp[i], ans);

                tmap.put(ll.get(i).x, ans);
            }
            out.println(ans);
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

class Pair {
    int x, y, cost;

    Pair(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

class sortByX implements Comparator<Pair> {
    public int compare(Pair x, Pair y) {
        if (x.x == y.x)
            return x.y - y.y;
        else
            return x.x - y.x;
    }
}