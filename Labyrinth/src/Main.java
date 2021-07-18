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


        int t=1;
        while(t-->0) {

            int n=in.nextInt();
            int m=in.nextInt();
            char[][] arr=new char[n][m];
            int start_x=-1;
            int start_y=-1;
            int end_x=-1;
            int end_y=-1;
            for(int i=0;i<n;i++) {
                arr[i] = in.next().toCharArray();
                for(int j=0;j<m;j++){
                    if(arr[i][j]=='A'){
                        start_x=i;
                        start_y=j;
                    }
                    if(arr[i][j]=='B'){
                        end_x=i;
                        end_y=j;
                    }
                }
            }

            int[][] dig=new int[n][m];
            int[]path=new int[n*m];
            boolean[][] visit=new boolean[n][m];
            int z=0;
            for(int i=0;i<n;i++) {
                for (int j = 0; j < m; j++, z++) {
                    dig[i][j] = z;
                    path[z]=z;
                }
            }

            int[] p={1,0,-1,0};
            int[] q={0,1,0,-1};
            Queue<Integer> st=new LinkedList<>();
            st.add(start_y);
            st.add(start_x);
            visit[start_x][start_y]=true;
            boolean flag=false;
            while(!st.isEmpty()){
                int y=st.remove();
                int x=st.remove();
                for(int i=0;i<4;i++){
                    int x_=x+p[i];
                    int y_=y+q[i];

                    if(x_<n && x_>=0 && y_>=0 && y_<m && !visit[x_][y_] && arr[x_][y_]!='#'){
                        visit[x_][y_]=true;
                        st.add(y_);
                        st.add(x_);
                        path[dig[x_][y_]]=dig[x][y];
                        if(x_==end_x && y_==end_y) {
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag)
                    break;
            }

            if(visit[end_x][end_y]){
                out.println("YES");
//                out.println(Arrays.toString(path));
                int k= dig[end_x][end_y];
//                out.println(k+" "+path[k]);
                StringBuilder ans=new StringBuilder();
                while(path[k]!=k){
                    int x=path[k];
                    if(x==k-1)
                        ans.append('R');
                    else if(x==k+1)
                        ans.append('L');
                    else if(x<k)
                        ans.append('D');
                    else
                        ans.append('U');
                    k=x;
                }
                out.println(ans.length());
                out.println(ans.reverse());
            }else{
                out.println("NO");
            }

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
        public int[] readArray(int n)
        {
            int[] arr=new int[n];
            for(int i=0;i<n;i++) arr[i]=nextInt();
            return arr;
        }
    }
}