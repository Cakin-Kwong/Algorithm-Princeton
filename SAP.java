public class SAP {
   private final Digraph digraph;

   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G) {
      if (G == null) 
         throw new java.lang.NullPointerException("NullPointerException");
      digraph = G;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w) {
      if (v == w)
         return 0;
      int minLength = -1;
      BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(digraph, v);
      BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(digraph, w);
      for (int i = 0; i < digraph.V(); i++) {
         if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
            if (minLength == -1) {
               minLength = vBFS.distTo(i) + wBFS.distTo(i);
            }
            else {
               int length = vBFS.distTo(i) + wBFS.distTo(i);
               if (minLength > length) {
                  minLength = length;
               }
            }   
         }
      }
      return minLength;
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w) {
      if (v == w)
         return v;
      int minLength = -1;
      int minAncestor = -1;
      BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(digraph, v);
      BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(digraph, w);
      for (int i = 0; i < digraph.V(); i++) {
         if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
            if (minLength == -1) {
               minLength = vBFS.distTo(i) + wBFS.distTo(i);
               minAncestor = i;
            }
            else {
               int length = vBFS.distTo(i) + wBFS.distTo(i);
               if (minLength > length) {
                  minLength = length;
                  minAncestor = i;
               }
            }   
         }
      }
      return minAncestor;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w) {
      if (v == null || w == null)
         throw new java.lang.NullPointerException("v, w NullPointerException");
      int sapLength = -1;

      BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(digraph, v);
      BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(digraph, w);
      for (int i = 0; i < digraph.V(); i++) {
         if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
            if (sapLength == -1) {
               sapLength = vBFS.distTo(i) + wBFS.distTo(i);
            }
            else {
               int length = vBFS.distTo(i) + wBFS.distTo(i);
               if (sapLength > length) {
                  sapLength = length;
               }
            }   
         }
      }
      return sapLength;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
      if (v == null || w == null)
         throw new java.lang.NullPointerException("v, w NullPointerException");
      int sapLength = -1;
      int sapAncestor = -1;

      BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(digraph, v);
      BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(digraph, w);
      for (int i = 0; i < digraph.V(); i++) {
         if (vBFS.hasPathTo(i) && wBFS.hasPathTo(i)) {
            if (sapLength == -1) {
               sapLength = vBFS.distTo(i) + wBFS.distTo(i);
               sapAncestor = i;
            }
            else {
               int length = vBFS.distTo(i) + wBFS.distTo(i);
               if (sapLength > length) {
                  sapLength = length;
                  sapAncestor = i;
               }
            }   
         }
      }
      return sapAncestor;
   }

   // do unit testing of this class
   public static void main(String[] args) {
      In in = new In(args[0]);
      Digraph G = new Digraph(in);
      SAP sap = new SAP(G);
      while (!StdIn.isEmpty()) {
         int v = StdIn.readInt();
         int w = StdIn.readInt();
         int length   = sap.length(v, w);
         int ancestor = sap.ancestor(v, w);
         StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
      }
   }
}
