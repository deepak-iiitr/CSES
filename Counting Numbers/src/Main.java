import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static int[][] dp;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        long num=in.nextLong()-1;
        char[] l=String.valueOf(num).toCharArray();
        num=in.nextLong();
        char[] r=String.valueOf(num).toCharArray();
        dp=new int[19][2];

        for(int i=0;i<19;i++)
            for(int j=0;j<2;j++)
                dp[i][j]=-1;

        long up = findAns(r,r.length,0,-1,0);
        dp=new int[19][2];
        for(int i=0;i<19;i++)
            for(int j=0;j<2;j++)
                dp[i][j]=-1;
        long down = findAns(l,l.length,0,-1,0);

        out.println(up+" "+down);
        out.println(up-down);
        out.close();
    }


    static int findAns(char[] l,int n,int current,int prev,int flag){
        if(current == n) {
            return 1;
        }

        if(dp[current][flag]!=-1)
            return dp[current][flag];
        int upLimit=9;
        if(flag==0) upLimit=l[current]-'0';
        int s=0;
        System.out.println(current+" "+upLimit);
        for(int i=0;i<=upLimit;i++){
            if(flag==1 || i<upLimit){
                if(prev!=i)
                    s+=findAns(l,n,current+1,i,1);
            }else{
                if(prev!=i)
                    s+=findAns(l,n,current+1,i,0);
            }
        }

        return dp[current][flag]=s;
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