import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static long mod = (long) 1e9 + 7;
    static long mod1 = 998244353;
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static ArrayList<Integer>[] adj;
    static ArrayList<Long>[] cost;

    public static void main(String[] args) throws IOException {

        int n = in.nextInt();
        int m = in.nextInt();
        adj = new ArrayList[n + 1];
        cost = new ArrayList[n + 1];
        int[] path = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            cost[i] = new ArrayList<>();
            adj[i] = new ArrayList<>();
            path[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            long c = in.nextLong();
            adj[a].add(b);
            cost[a].add(c);
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new SortByDistance());
        pq.add(new Pair(1, dist[1]));
        long ans = Long.MAX_VALUE;
        long[] storeMax = new long[n+1];
        while (!pq.isEmpty()) {
            Pair p = pq.remove();
            int u = p.u;
            if (dist[u] == p.dist) {
                for (int i = 0; i < adj[u].size(); i++) {
                    int v = adj[u].get(i);
                    long w = cost[u].get(i);
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        path[v] = u;
                        storeMax[v] = Math.max(storeMax[u],w);
                        pq.add(new Pair(v, dist[v]));
                    }

                    if(v == n){
                        long val = Math.min(dist[u]+ w/2,
                                dist[u] + w - storeMax[u] + storeMax[u]/2);
                        ans = Math.min(ans,val);
                    }
                }
            }
        }

        out.println(ans);

        out.close();
    }

    static class Pair {
        int u;
        long dist;

        Pair(int u, long dist) {
            this.u = u;
            this.dist = dist;
        }
    }

    static class SortByDistance implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return Long.compare(a.dist, b.dist);
        }
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