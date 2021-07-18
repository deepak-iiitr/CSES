import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    static long mod=(long)1e9+7;
    static long mod1=998244353;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);


        int n=in.nextInt();
        int k=in.nextInt();
        TreeMap<Long,Integer> tmap=new TreeMap<>();
        long[] arr=in.readArray(n);
        for(int i=0;i<k;i++)
        {
            if(tmap.containsKey(arr[i]))
                tmap.put(arr[i],tmap.get(arr[i])+1);
            else
                tmap.put(arr[i],1);
        }
        int pos;
        if(k%2==1)
            pos=(k-1)/2+1;
        else
            pos=k/2;
        int c=0;
        for(Map.Entry<Long,Integer> entry:tmap.entrySet()){
            c+=entry.getValue();
            if(c>=pos){
                out.print(entry.getKey()+" ");
                break;
            }
        }
        for(int i=1;i<=n-k;i++){
            int count=tmap.get(arr[i-1]);
            if(count-1==0)
                tmap.remove(arr[i-1]);
            else
                tmap.put(arr[i-1],count-1);
            if(tmap.containsKey(arr[i+k-1]))
                tmap.put(arr[i+k-1],tmap.get(arr[i+k-1])+1);
            else
                tmap.put(arr[i+k-1],1);
            c=0;
            for(Map.Entry<Long,Integer> entry:tmap.entrySet()){
                c+=entry.getValue();
                if(c>=pos){
                    out.print(entry.getKey()+" ");
                    break;
                }
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

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long[] readArray(int n) throws IOException{
            long[] arr = new long[n];
            for(int i = 0;i<n;i++)
                arr[i] = nextInt();

            return arr;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}