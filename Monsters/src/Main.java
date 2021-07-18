import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static long mod = (long) 1e9 + 7;
    static long mod1 = 998244353;
    static OutputStream outputStream = System.out;
    static PrintWriter out = new PrintWriter(outputStream);
    static InputStream inputStream = System.in;
    static InputReader in = new InputReader(inputStream);

    public static void main(String[] args) throws IOException {

        int n = in.nextInt();
        int m = in.nextInt();
        char[][] arr = new char[n][m];
        int[] path = new int[n * m];
        int[][] dig = new int[n][m];

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = in.next().toCharArray();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Pair a_pos = new Pair(-1, -1);
        Pair end = new Pair(0, 0);
        int Z = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++, Z++) {
                path[Z] = Z;
                dig[i][j] = Z;
                if (arr[i][j] == 'A')
                    a_pos = new Pair(i, j);
                if (arr[i][j] == 'M') {
                    boolean[][] visit = new boolean[n][m];
                    visit[i][j] = true;
                    dist[i][j] = 0;
                    Queue<Pair> q = new LinkedList<>();
                    q.add(new Pair(i, j));
                    while (!q.isEmpty()) {
                        Pair u = q.remove();

                        for (int k = 0; k < 4; k++) {
                            int x = u.x + dx[k];
                            int y = u.y + dy[k];

                            if (x < n && x >= 0 && y < m && y >= 0) {
                                if (arr[x][y] != '#') {
                                    if (!visit[x][y]) {
                                        visit[x][y] = true;
                                        q.add(new Pair(x, y));
                                    }
                                    dist[x][y] = Math.min(dist[x][y], dist[u.x][u.y] + 1);
                                }
                            }
                        }
                    }
                }
            }
        }

        boolean flag = false;
        int u = a_pos.x;
        int v = a_pos.y;
        if (u == 0 || u == n - 1 || v == 0 || v == m - 1)
            flag = true;
        if (u != -1 && v != -1 && !flag) {
            Queue<Pair> q = new LinkedList<>();
            q.add(a_pos);
            dist[u][v] = 0;
            boolean[][] visit = new boolean[n][m];
            visit[u][v] = true;
            while (!q.isEmpty() && !flag) {
                Pair p = q.remove();
                for (int i = 0; i < 4; i++) {
                    int x = p.x + dx[i];
                    int y = p.y + dy[i];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        if (arr[x][y] != '#') {
                            if (!visit[x][y]) {
                                int prev = dist[x][y];
                                dist[x][y] = Math.min(dist[x][y], dist[p.x][p.y] + 1);
                                visit[x][y] = true;
                                if (dist[x][y]<prev) {
                                    path[dig[x][y]] = dig[p.x][p.y];
                                    q.add(new Pair(x, y));
                                    if (x == n - 1 || y == m - 1 || x == 0 || y == 0) {
                                        end = new Pair(x, y);
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (flag) {
            out.println("YES");
            int k = dig[end.x][end.y];
            StringBuilder ans = new StringBuilder();
            while (path[k] != k) {
                int x = path[k];
                if (x == k - 1)
                    ans.append('R');
                else if (x == k + 1)
                    ans.append('L');
                else if (x < k)
                    ans.append('D');
                else
                    ans.append('U');
                k = x;
            }
            out.println(ans.length());
            out.println(ans.reverse());
        } else
            out.println("NO");
        out.close();
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
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