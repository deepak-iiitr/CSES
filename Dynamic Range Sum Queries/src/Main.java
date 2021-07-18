import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    static long mod = (long) 1e9 + 7;
    static long mod1 = 998244353;
    static int[] segTree;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t = 1;
        while (t-- > 0) {

            int n = in.nextInt();
            int q = in.nextInt();
            segTree st = new segTree(n);
            int[] arr=in.readArray(n);
            st.build(arr);
            while (q-- > 0) {
                int ch = in.nextInt();
                if (ch == 1) {
                    int i = in.nextInt();
                    int v = in.nextInt();
                    st.set(i, v);
                } else {
                    int l, r;
                    l = in.nextInt();
                    r = in.nextInt();
                    int val=st.getMin(l, r);
                    out.println(val+" "+st.countMin(l,r,val));
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

class segTree {
    int[] val;
    int size;

    segTree(int n) {
        size = 1;
        while (size < n) size *= 2;
        val = new int[2 * size];
    }
    void build(int[] arr,int x,int lx,int rx){
        if(rx-lx==1){
            if(lx<arr.length) {
                val[x] = arr[lx];
            }
            return;
        }
        int m=(lx+rx)/2;
        build(arr,2*x+1,lx,m);
        build(arr,2*x+2,m,rx);
        val[x]=Math.min(val[2*x+1],val[2*x+2]);
    }
    void build(int[] arr){
        build(arr,0,0, size);
    }


    void set(int i, int v, int x, int lx, int rx) {
        if (rx - lx == 1) {
            val[x] = v;
            return;
        }
        int m = (lx + rx) / 2;
        if (i < m)
            set(i, v, 2 * x + 1, lx, m);
        else
            set(i, v, 2 * x + 2, m, rx);
        val[x] = Math.min(val[2 * x + 1] , val[2 * x + 2]);
    }

    void set(int i, int v) {
        set(i, v, 0, 0, size);
    }

    int getMin(int l, int r, int x, int lx, int rx) {
        if (lx >= r || l >= rx) return Integer.MAX_VALUE;
        if (lx >= l && rx <= r) return val[x];

        int m = (lx + rx) / 2;
        int s1 = getMin(l, r, 2 * x + 1, lx, m);
        int s2 = getMin(l, r, 2 * x + 2, m, rx);
        return Math.min(s1, s2);
    }

    int getMin(int l, int r) {
        return getMin(l, r, 0, 0, size);
    }
    int countMin(int l,int r,int v,int x,int lx,int rx){
        if(rx-lx==1){
            if(val[lx]==v)
                return 1;
            else
                return 0;
        }
        int m=(lx+rx)/2;
        return countMin(l,r,v,2*x+1,lx,m)+countMin(l,r,v,2*x+2,m,rx);
    }
    int countMin(int l,int r,int v){
        return countMin(l,r,v,0,0,size);
    }
}