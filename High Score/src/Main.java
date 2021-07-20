import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {

        // If a positive weight cycle exists then you can get infinitely large Score
        // in case if there exist a path from root to node "1" through the cycle
        // If not then simple Bellman Ford works

        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] e = new Edge[m];
        adj = new ArrayList[n+1];
        for(int i = 0;i<=n;i++) adj[i] = new ArrayList<>();

        for(int i = 0;i<m;i++){
            int u = in.nextInt();
            int v = in.nextInt();
            long x = in.nextLong();
            adj[u].add(v);
            e[i] = new Edge(u,v,x);
        }
        long[] dist = new long[n+1];
        Arrays.fill(dist,Long.MIN_VALUE);
        dist[1] = 0;

        boolean flag = true;
        for(int i = 1;i<n;i++){
            for(int j=0;j<m;j++){
                int u = e[j].u;
                int v = e[j].v;
                long w = e[j].x;
                if(dist[u]!=Long.MIN_VALUE)
                    dist[v] = Math.max(dist[v],dist[u]+w);
            }
        }
        ArrayList<Integer> nodesInCycle = new ArrayList<>();
        for(int i = 1;i<n;i++){
            for(int j=0;j<m;j++){
                int u = e[j].u;
                int v = e[j].v;
                long w = e[j].x;
                if(dist[u]!=Long.MIN_VALUE) {
                    long prev = dist[v];
                    dist[v] = Math.max(dist[v], dist[u] + w);
                    if (prev != dist[v]) {
                        nodesInCycle.add(v);
                        if(v==n){
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(!flag)
                break;
        }

        if(nodesInCycle.isEmpty())
            out.println(dist[n]);
        else{
            if(flag) {
                for (int i : nodesInCycle) {
                    if (isReachable(i, n)) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    out.println(dist[n]);
                else
                    out.println(-1);
            }else out.println(-1);
        }
        out.close();
    }

    static class Edge{
        int u,v;
        long x;
        Edge(int u,int v, long x){
            this.u = u;
            this.v = v;
            this.x = x;
        }
    }

    static boolean isReachable(int s,int d){
        boolean[] visit = new boolean[d+1];
        visit[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(!q.isEmpty()){
            int u = q.remove();
            for(int i:adj[u]){
                if(!visit[i]){
                    visit[i] = true;
                    if(i==d)
                        return true;
                    q.add(i);
                }
            }
        }
        return false;
    }

    static final Random random=new Random();

    static void ruffleSort(int[] a) {
        int n=a.length;//shuffle, then sort
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n), temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }
    static long gcd(long x, long y){
        if(x==0)
            return y;
        if(y==0)
            return x;
        long r=0, a, b;
        a = Math.max(x, y);
        b = Math.min(x, y);
        r = b;
        while(a % b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
    static long modulo(long a,long b,long c){
        long x=1,y=a%c;
        while(b > 0){
            if(b%2 == 1)
                x=(x*y)%c;
            y = (y*y)%c;
            b = b>>1;
        }
        return  x%c;
    }
    public static void debug(Object... o){
        System.err.println(Arrays.deepToString(o));
    }

    static int upper_bound(int[] arr,int n,int x){
        int mid;
        int low=0;
        int high=n;
        while(low<high){
            mid=low+(high-low)/2;
            if(x>=arr[mid])
                low=mid+1;
            else
                high=mid;
        }
        return low;
    }

    static int lower_bound(int[] arr,int n,int x){
        int mid;
        int low=0;
        int high=n;
        while(low<high){
            mid=low+(high-low)/2;
            if(x<=arr[mid])
                high=mid;
            else
                low=mid+1;
        }
        return low;
    }
    static String printPrecision(double d){
        DecimalFormat ft = new DecimalFormat("0.00000000000");
        return String.valueOf(ft.format(d));
    }
    static int countBit(long mask){
        int ans=0;
        while(mask!=0){
            mask&=(mask-1);
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