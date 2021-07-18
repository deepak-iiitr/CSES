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
            HashMap<Pair,Stack<Integer>> pos = new HashMap<>();
            for (int i = 0; i < n; i++) {
                Pair x = new Pair(in.nextInt(), in.nextInt());
                Stack<Integer> st = new Stack<>();
                if(pos.containsKey(x))
                    st = pos.get(x);
                st.push(i);
                pos.put(x,st);
                ll.add(x);
            }
            ll.sort(new SortByFirst());
            ArrayList<valPair> vp = new ArrayList<>();
            int[] ans = new int[n];
            int room = 0;
            int start = 0;
            for (int i = 0; i < n; i++) {
                Pair a = ll.get(i);
                int prev = start;
                while(start<vp.size()) {
                    valPair b = vp.get(start);
                    if (a.x > b.p.y) {
                        int val = b.val;
                        ans[pos.get(a).pop()] = val;
                        vp.add(new valPair(a, val));
                        break;
                    }else
                        start++;
                }

                if (prev == start || start == vp.size()) {
                    room++;
                    ans[pos.get(a).pop()] = room;
                    vp.add(new valPair(a,room));
                }

            }
            out.println(room);
            for(int i:ans)
                out.print(i+" ");

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
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class SortByFirst implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        if (a.y == b.y)
            return a.x - b.x;
        else return a.y - b.y;
    }
}
class valPair {
    Pair p;
    int val;
    valPair(Pair p, int val){
        this.p = p;
        this.val = val;
    }
}