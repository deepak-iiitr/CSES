import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class countRooms {
    static long mod=(long)1e9+7;
    static long mod1=998244353;
    static boolean[][] visit;
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);


        int t= 1;
        while(t-->0) {

            int n=in.nextInt();
            int m=in.nextInt();
            int dots=0;
            char[][] arr=new char[n][m];
            for(int i=0;i<n;i++){
                String st=in.next();
                for(int j=0;j<m;j++) {
                    arr[i][j] = st.charAt(j);
                    if(arr[i][j]=='.')
                        dots++;
                }
            }
            int count=0;
            int dotsCounted=0;
            visit=new boolean[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    Stack<Pair> st=new Stack<Pair>();
                    if(arr[i][j]=='.' && !visit[i][j]) {
                        st.push(new Pair(i, j));
                        dotsCounted++;
                        count++;
//                        out.println(i+" "+j);
                    }
                    while(!st.isEmpty()){
                        Pair u=st.pop();
                        int i_=u.x;
                        int j_=u.y;
                        if(i_+1<n && arr[i_+1][j_]=='.' && !visit[i_+1][j_]){
                            visit[i_+1][j_]=true;
                            dotsCounted++;
                            st.push(new Pair(i_+1,j_));
                        }
                        if(i_-1>=0 && arr[i_-1][j_]=='.' && !visit[i_-1][j_]){
                            dotsCounted++;
                            visit[i_-1][j_]=true;
                            st.push(new Pair(i_-1,j_));
                        }
                        if(j_+1<m && arr[i_][j_+1]=='.' && !visit[i_][j_+1])
                        {
                            dotsCounted++;
                            visit[i_][j_+1]=true;
                            st.push(new Pair(i_,j_+1));
                        }
                        if(j_-1>=0 && arr[i_][j_-1]=='.' && !visit[i_][j_-1])
                        {
                            dotsCounted++;
                            visit[i_][j_-1]=true;
                            st.push(new Pair(i_,j_-1));
                        }
                    }

                }
            }
            out.println(count);

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
class Pair{
    int x;
    int y;
    Pair(int x,int y){
        this.x=x;
        this.y=y;
    }
}