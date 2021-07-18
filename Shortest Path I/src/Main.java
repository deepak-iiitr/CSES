import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static ArrayList<Integer>[] adj;
    static ArrayList<Long>[] cost;

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);


        int n = in.nextInt();
        int m = in.nextInt();
        adj = new ArrayList[n];
        cost = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            long c = in.nextLong();
            adj[a].add(b);
            cost[a].add(c);
        }
        long[] dist = new long[n];
        Arrays.fill(dist, (long) 1e15);
        dist[0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new sortByDistance());
        pq.add(new Pair(0, dist[0]));

        while (pq.size() > 0) {
            Pair p = pq.remove();
            int u = p.u;
            if (dist[u] == p.dist) {
                for (int i = 0; i < adj[u].size(); i++) {
                    int v = adj[u].get(i);
                    long w = cost[u].get(i);
                    if (dist[v] > dist[u] + w) {
//                        pq.remove(k);
                        dist[v] = dist[u] + w;
                        Pair k = new Pair(v, dist[v]);
                        pq.add(k);
                    }
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++)
            ans.append(dist[i]).append(" ");
        out.print(ans);
        out.flush();
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

class Pair {
    int u;
    long dist;

    Pair(int u, long dist) {
        this.u = u;
        this.dist = dist;
    }
}

class sortByDistance implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        return Long.compare(a.dist, b.dist);
    }
}