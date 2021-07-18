import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static int[] pred;
    static int[] dist;
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t= 1;
        while(t-->0) {

            int n=in.nextInt();
            int m=in.nextInt();
            ArrayList<Integer>[] adj=new ArrayList[n+1];
            for(int i=0;i<=n;i++)
                adj[i]=new ArrayList<>();
            for(int i=0;i<m;i++){
                int x=in.nextInt();
                int y=in.nextInt();
                adj[x].add(y);
                adj[y].add(x);
            }
            BFS(adj,1,n);
            if(dist[n]==Integer.MAX_VALUE)
                out.println("IMPOSSIBLE");
            else{
                out.println(dist[n]);
                ArrayList<Integer> sol=new ArrayList<>();
                int i=n;
                while(pred[i]!=-1){
                    sol.add(i);
                    i=pred[i];
                }
                sol.add(i);
                for(int j=dist[n]-1;j>=0;j--)
                    out.print(sol.get(j)+" ");
            }
        }
        out.close();
    }

    static void BFS(ArrayList<Integer>[] adj,int s,int d){
        int n=adj.length;
        boolean[] visit=new boolean[n+1];
        LinkedList<Integer> queue=new LinkedList<>();
        dist=new int[n+1];
        pred=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(pred,-1);
        dist[s]=1;
        queue.add(s);
        visit[1]=true;
        while(!queue.isEmpty()){
            int u=queue.remove();
            for(int i=0;i<adj[u].size();i++){
                if(!visit[adj[u].get(i)]){
                    dist[adj[u].get(i)]=dist[u]+1;
                    pred[adj[u].get(i)]=u;
                    visit[adj[u].get(i)]=true;
                    if(adj[u].get(i)==d)
                        return;
                    queue.add(adj[u].get(i));
                }
            }
        }
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