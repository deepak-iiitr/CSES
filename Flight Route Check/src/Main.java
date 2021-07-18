import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t= 1;
        while(t-->0) {

            int n = in.nextInt();
            int e = in.nextInt();
            adj = new ArrayList[n+1];
            int[] deg = new int[n+1];
            for(int i = 0; i <= n; i++){
                adj[i] = new ArrayList<>();
            }

            for(int i = 0;i<e;i++){
                int a = in.nextInt();
                int b = in.nextInt();
                deg[a]++;
                adj[a].add(b);
            }
            TreeMap<Integer,ArrayList<Integer>> tmap = new TreeMap<>();
            for(int i = 1;i<=n;i++){
                ArrayList<Integer> ll = new ArrayList<>();
                if(tmap.containsKey(deg[i]))
                    ll = tmap.get(deg[i]);
                ll.add(i);
                tmap.put(deg[i],ll);
            }

            boolean flag = true;
            boolean hasAns = false;
            for(Map.Entry<Integer,ArrayList<Integer>> entry:tmap.entrySet()){
                ArrayList<Integer> ll = entry.getValue();
                for(int i:ll){
                    int count = 1;
                    boolean[] visit = new boolean[n+1];
                    dfs(visit,i,count);
                    if(count!=n){
                        flag = false;
                        for(int j = 1;j<=n;j++){
                            if(!visit[j]){
                                hasAns = true;
                                out.println("NO");
                                out.println(i+" "+j);
                                break;
                            }
                        }
                        break;
                    }
                }
                if(!flag)
                    break;
            }
            if(!hasAns)
                out.println("YES");

        }
        out.close();
    }

    static void dfs(boolean[] visit,int src,int count){
        visit[src] = true;
        for(int i:adj[src]){
            if(!visit[i]){
                visit[i] = true;
                count+=1;
                dfs(visit,i,count);
            }
        }
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
        public int[] readArray(int n)
        {
            int[] arr=new int[n];
            for(int i=0;i<n;i++) arr[i]=nextInt();
            return arr;
        }
    }
}