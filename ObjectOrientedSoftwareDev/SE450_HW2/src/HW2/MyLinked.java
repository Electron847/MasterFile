package HW2;

public class MyLinked {

    static class Node {
        public Node() {}
        public double item;
        public Node next;
    }

    int N;
    Node first;

    public MyLinked(){
        first = null;
        N = 0;
        assert checkInvariance();
    }

    private boolean checkInvariance(){
        assert ((N != 0) || (first == null));
        Node x = first;
        for (int i = 0; i < N; i++){
            if(x == null){
                return false;
            }
            x = x.next;
        }
        assert (x == null);
        return true;
    }

    public boolean isEmpty() { return first == null; }

    public int size() { return N; }

    public void add(double item)
    {

        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
        N++;
    }

    // delete the kth element
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();

        Boolean deletedNode = false;

        if(k==0 && N==1) {
            assert (k == 0 && N == 1);
            first = null;
            deletedNode = true;
        }

        if(!deletedNode && k==1 && N==2) {
            first.next = null;
            deletedNode = true;
        }

        if(!deletedNode && k==0) {
            first = first.next;
            deletedNode = true;
        }

        if(!deletedNode && k==N-1) {
            Node x = first;
            for (int i = 0; i < N; i++) {
                if (i==N-2) {
                    x.next = null;
                    deletedNode = true;
                    break;
                }
                x = x.next;
            }
        }

        if(!deletedNode) {
            Node x = first;
            for(int i = 1; i < N; i++) {
                if(k==i) {
                    x.next = x.next.next;
                    deletedNode = true;
                    break;
                }
                x = x.next;
            }
        }

        assert deletedNode;
        N--;
        assert checkInvariance ();
    }

        public void reverse()
        {
            if (N <= 3)
            {
                assert (N <= 3);
                if (N == 2)
                {
                    assert (N == 2);
                    Node temp = first.next;
                    first.next = null;
                    temp.next = first;
                    first = temp;
                }
                if (N == 3)
                {
                    assert N ==3;
                    first.next.next.next = first.next.next;
                    first.next.next = first;
                    first.next = null;
                }
            }
            if (N > 3)
            {
                assert N > 3;
                Node one = first;
                Node two = first.next;
                Node three = two.next;
                Node temp = null;
                first.next = null;
                while(two.next != null || three.next != null || one.next != null)
                {
                    assert two.next != null || three.next != null || one.next != null;
                    two.next = one;
                    one = three.next;
                    three.next = temp;
                    if(one.next == null || three.next == null)
                    {
                       // break;
                    }
                    temp = one;
                    one = three;
                    two = temp;
                    three = two.next;
                }
                if(one.next == null)
                {
                    assert one.next == null;
                    one.next = three;
                    first = one;
                }
                if (two.next.next == null)
                {
                    assert two.next.next == null;
                    temp = two.next;
                    two.next = one;
                    temp.next = two;
                    first = temp;
                }
                if(three.next.next == null)
                {
                    assert three.next.next == null;
                    temp = three.next;
                    three.next = two;
                    temp.next = three;
                    first = temp;
                }
            }
            assert checkInvariance();
        }

        public void remove(double item)
        {
            if(first == null) {return;}
            while(first != null && first.item == item)
            {
                if(first.next == null)
                {
                    first = null;
                    N--;
                }
                else {
                    first = first.next;
                    N--;
                }
            }

            Node alpha = first;
            Node beta = first;
            while (alpha != null)
            {
                while(alpha != null && alpha.item != item)
                {
                    beta = alpha;
                    alpha = alpha.next;
                }
                if(alpha == null) {return;}
                beta.next = alpha.next;
                alpha = alpha.next;
                N--;
            }
            assert (checkInvariance());
        }
}
