import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t= 1;
        while(t-->0) {

            int n=in.nextInt();
            long x=in.nextLong();
            long[] arr=in.readArray(n);
            HashMap<Long,ArrayList<Pair>> hmap=new HashMap<>();
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    long sum=arr[i]+arr[j];
                    ArrayList<Pair> ll;
                    if(hmap.containsKey(sum))
                        ll=hmap.get(sum);
                    else
                        ll=new ArrayList<>();
                    ll.add(new Pair(i+1,j+1));
                    hmap.put(sum,ll);

                }
            }
            boolean flag=false;
            for(Map.Entry<Long,ArrayList<Pair>> entry: hmap.entrySet()){
                long rem=x-entry.getKey();
                if(hmap.containsKey(rem)){
                    ArrayList<Pair> ll= entry.getValue();
                    ArrayList<Pair> mm= hmap.get(rem);
                    for(Pair a:ll){
                        for(Pair b:mm){
                            if(a.x!=b.x && a.y!=b.y && a.x!=b.y && a.y!=b.x) {
                                flag=true;
                                out.println(a.x + " " + a.y + " " + b.x + " " + b.y);
                                break;
                            }
                        }
                        if(flag)
                            break;
                    }
                    if(flag)
                        break;
                }
            }
            if(!flag)
                out.println("IMPOSSIBLE");
        }
        out.close();
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
        public long[] readArray(int n)
        {
            long[] arr=new long[n];
            for(int i=0;i<n;i++) arr[i]=nextLong();
            return arr;
        }
    }
}
class Pair{
    int x;int y;
    Pair(int x,int y){
        this.x=x;
        this.y=y;
    }
}