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
    static int[] path;
    static int endPoint;

    public static void main(String[] args) throws IOException {

       int n = in.nextInt();
       int m = in.nextInt();
       adj = new ArrayList[n+1];
       for(int i = 0;i<=n;i++)
           adj[i] = new ArrayList<>();
       for(int i = 0;i<m;i++){
           int a = in.nextInt();
           int b = in.nextInt();
           adj[a].add(b);
           adj[b].add(a);
       }
       boolean flag = false;
        int start = -1;
        if(m>=n) {
           for (int i = 1; i <= n; i++) {
               path = new int[n + 1];
               if (dfs(i,n)) {
                   start = i;
                   out.println(start+" "+endPoint);
                   flag = true;
                   break;
               }
           }
       }

       if(flag){
           ArrayList<Integer> ll = new ArrayList<>();
           ll.add(start);
           while(path[endPoint]!=endPoint){
               ll.add(endPoint);
               endPoint = path[endPoint];
               out.println("Hello");
           }
           ll.add(endPoint);
           out.println(ll.size());
           for(int i=ll.size()-1;i>=0;i--)
               out.print(ll.get(i)+" ");
       }else{
           out.println("IMPOSSIBLE");
       }

        out.close();
    }

    static boolean dfs(int s,int n){
        boolean[] visit = new boolean[n+1];
        visit[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        int[] dist = new int[n+1];
        dist[s] = 0;
        path[s] = s;
        while(!q.isEmpty()){
            int u = q.remove();
            out.println(u+" "+dist[u]);
            for(int i:adj[u]){
                if(!visit[i]){
                    visit[i] = true;
                    q.add(i);
                    dist[i] = dist[u]+1;
                    path[i] = u;
                }else{
                    if(i==s && dist[u]>1){
                        endPoint = u;
                        return true;
                    }else{
                        if(i!=s) {
                            dist[i] = Math.max(dist[i], dist[u] + 1);
                            if (dist[i] == dist[u] + 1) {
                                path[i] = u;
                                q.add(i);
                            }
                        }
                    }
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