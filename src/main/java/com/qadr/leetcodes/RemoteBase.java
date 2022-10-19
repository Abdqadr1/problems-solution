package com.qadr.leetcodes;

import java.util.*;

class Chat {
    List<String> members;
    List<Integer> files;
    Map<Integer, String> message;

    public Chat() {
        members = new ArrayList<>();
        files = new ArrayList<>();
        message = new HashMap<>();
    }

    public void add(String[] names) {
        for (String name : names){
            members.add(name);
        }
    }

    public void add(int[] ids) {
        for (int i : ids) {
            files.add(Integer.valueOf(i));
        }
    }

    public void add(int id, String message) {
        this.message.put(id, message);
    }

    public void remove(String[] names) {
        for (String name : names) {
            if (!members.remove(name)) {
                System.out.printf("Member with name %s does not exist%n", name);
            }
        }
    }

    public void remove(int[] ids) {
        for (int id : ids) {
            if (!files.remove(Integer.valueOf(id))) {
                System.out.printf("File with id %d does not exist%n", id);
            }
        }
    }

    public void remove(int id) {
        if (message.containsKey(id)) message.remove(id);
        else System.out.printf("Message with id %s does not exist%n", id);
    }

    public void printConversation(){
        System.out.println("Total number of members in the conversation are " + members.size());

        StringBuilder names = new StringBuilder();
        for (String member : members) {
            names.append(member).append(" ");
        }
        System.out.println("The names of these members are " + names);

        System.out.println("Total number of files in the conversation are "+files.size());
        StringBuilder messages = new StringBuilder();
        for(Integer id : this.message.keySet()){
            messages.append("'").append(this.message.get(id)).append("' ");
        }
        System.out.println("The messages in the conversation are " + messages);
    }
}



public class RemoteBase {
    public static int solution(int N) {
        // write your code in Java SE 8
        char[] chars = Integer.toBinaryString(N).toCharArray();
        int one = -1, gap = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if(ch == '1') {
                if(one > -1) {
                    int g = i-one-1;
                    gap = g > -1 ? Math.max(gap, g) : gap;
                }
                one = i;
            }
        }
        return gap;
    }
    public static boolean conforms(int a, int b){
        char[] bitA = Integer.toBinaryString(a).toCharArray();
        char[] bitB = Integer.toBinaryString(b).toCharArray();
        for (int i = 0; i < bitB.length; i++) {
            char ch = bitB[i];
            if(ch != '1') continue;

            if(ch != bitA[i]) return false;
        }
        return true;
    }
    public static int solution(int A, int B, int C) {
        int max = 1073741823;
        int min = Math.min(Math.min(A, B), C);
        int count = 0;
        for (int i = min; i <= max; i++) {
            if(conforms(i, A) || conforms(i, B) || conforms(i, C)) count++;
        }
        return count;
    }
    public static int solution(int[] A) {
        // write your code in Java SE
        int index = 0, count = 0, next=A[index];
        for (int i = 0; i < A.length; i++) {
            index = A[index];
            count++;
            if(index == -1) break;
        }
        return count;
    }
    public static int solution(int K, int[] A) {
        // write your code in Java SE 8
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int max = A[i];
            int min = A[i];
            for (int j = i; j < A.length; j++) {
                int c = A[j];
                max = Math.max(c, max);
                min = Math.min(c, min);
                if((max-min) <= K) {
                    count++;
                    if(count == 1000000000) return 1000000000;
                }
            }
        }
        return count;
    }
    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }
    public int solution(Tree T) {
        // write your code in Java SE 8
        return Math.max(explore(T.l, 1), explore(T.r, 0));
    }
    public int explore(Tree node, int dir){
        if(node == null) return 0;
        int right = 0, left = 0;

        if(dir == 0 && node.l != null) left++;
        if(dir == 1 && node.r != null) right++;

        right += explore(node.r, 0);
        left += explore(node.l, 1);


        return Math.max(right, left);
    }
    public int solution(int[][] A) {
        // write your code in Java SE 8
        int count = 0;
        boolean[][] memo = new boolean[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                int color = A[i][j];
                if (!memo[i][j]) {
                    exploreNeighbours(A, i, j, color, memo);
                    count++;
                }
            }
        }
        return count;
    }
    public void exploreNeighbours(int[][] a, int i, int j, int color, boolean[][] memo) {
        if(i >= 0 && i < a.length && j >= 0 && j < a[i].length && a[i][j] == color){
            if(memo[i][j]) return;
            memo[i][j] = true;

            exploreNeighbours(a, i-1, j, color, memo);
            exploreNeighbours(a, i, j+1, color, memo);
            exploreNeighbours(a, i+1, j, color, memo);
            exploreNeighbours(a, i, j-1, color, memo);
        }
    }
    static class Connection{
        int time, node;
        public Connection(int node, int time){
            this.node = node;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "time=" + time +
                    ", node=" + node +
                    '}';
        }
    }
    public static Map<Integer, List<Connection>> buildGraphFromEdges(int[][] edges){
        Map<Integer, List<Connection>> graph = new HashMap<>();
        for (int[] edge : edges){
            int node1 = edge[0], node2 = edge[1], time = edge[2];
            if(!graph.containsKey(node1)){
                graph.put(node1, new ArrayList<>());
            }
            graph.get(node1).add(new Connection(node2, time));
        }
        return graph;
    }
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Connection>> graph = buildGraphFromEdges(times);
        Queue<Connection> queue = new PriorityQueue<>(Comparator.comparingInt(c -> c.time));
        queue.add(new Connection(k, 0));
        boolean[] visited = new boolean[n+1];
        return explore(graph, k, visited, n, queue);
    }
    public static int explore(Map<Integer, List<Connection>> graph, int k, boolean[] visited, int n, Queue<Connection> queue){
        int time = 0, nodeCount= 0;
        Connection connection;
        while (!queue.isEmpty()){
            connection = queue.poll();
            int node = connection.node;
            if(visited[node]) continue;
            visited[node] = true;
            nodeCount++;
            time = Math.max(time, connection.time);

            if(graph.get(node) != null && graph.get(node).size() > 0){
                for(Connection con : graph.get(node)){
                    if(!visited[con.node]){
                        con.time += connection.time;;
                        queue.add(con);
                    }
                }
            }
        }
        return nodeCount == n ? time : -1;
    }

//     Definition for singly-linked list.
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
     public static ListNode oddEvenList(ListNode head) {
            if(head == null || head.next == null) return head;
            ListNode evenHead = null, oddHead = new ListNode(head.val);
            ListNode evenPointer = null, oddPointer = oddHead;
            while (head != null){
                head =  head.next;
                if(evenHead == null){
                    evenHead = new ListNode(head.val);
                    evenPointer = evenHead;
                }else {
                    evenPointer.next = head;
                    evenPointer = evenPointer.next;
                }


                if(head != null && head.next != null){
                    head = head.next;
                    oddPointer.next = head;
                    oddPointer = oddPointer.next;
                }
            }

         oddPointer.next = evenHead;
         return oddHead;
        }
        public static List<List<Integer>> threeSum(int[] nums) {
            HashSet<List<Integer>> result = new HashSet<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                int res = -nums[i];
                int j = i + 1, k = nums.length-1;
                while (j < k){
                    if(nums[j]+nums[k] == res){
                        List<Integer> r = new ArrayList<>();
                        r.add(nums[i]);
                        r.add(nums[j]);
                        r.add(nums[k]);
                        result.add(r);
                        j++;k--;
                    }else if(nums[j]+nums[k] > res){
                        k--;
                    }else{
                        j++;
                    }
                }
            }
            return new ArrayList<>(result);
    }
    public static int threeSumClosest(int[] nums, int target) {
        int sum = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            int j = i + 1, k = nums.length-1;
            while (j < k){
                int total = nums[j]+nums[k]+first;
                int diff = Math.abs(total - target);
                if(total == target){
                    return target;
                }else if(total > target){
                    k--;
                }else{
                    j++;
                }
                if(sum == Integer.MAX_VALUE || diff < Math.abs(target - sum)){
                    sum = total;
                }
            }
        }
        return sum;
    }

    public static Map<Character, List<Character>> getMap(){
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', List.of('a', 'b', 'c'));
        map.put('3', List.of('d', 'e', 'f'));
        map.put('4', List.of('g', 'h', 'i'));
        map.put('5', List.of('j', 'k', 'l'));
        map.put('6', List.of('m', 'n', 'o'));
        map.put('7', List.of('p', 'q', 'r', 's'));
        map.put('8', List.of('t', 'u', 'v'));
        map.put('9', List.of('w', 'x', 'y', 'z'));
        return map;
    }
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() == 0) return result;
        Map<Character, List<Character>> map = getMap();
        backTrack(result, map, "", 0, digits.toCharArray());
        return result;
    }
    public static void backTrack(List<String> result, Map<Character, List<Character>> map, String current, int index, char[] digits){
        if(index == digits.length){
            result.add(current);
        }else{
            char ch = digits[index];
            List<Character> characters = map.get(ch);
            for (Character c : characters) {
                backTrack(result,map, current+c, index+1, digits);
            }
        }
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int size =0;
        ListNode current = head;
        while(current != null){
            current = current.next;
            size++;
        }
        int idx = size - n;
        if(idx == 0 && size == 0) return null;
        else if(idx == 0){
            head = head.next;
        }else{
            current = head;
            for (int i = 0; i < size-n-1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        return head;
    }
    public static ListNode swapNodes(ListNode head, int k) {
        int size = 1;
        ListNode current = head;
        while (current.next != null){
            current = current.next;
            size++;
        }
        if(size == 1) return head;
        current = head;
        int index = 1;
        ListNode first = head;
        ListNode second = head;
        while (index <= size){
            if(index == k){
                first = current;
            }
            if(index == (size-(k-1))){
                second = current;
            }
            index++;
            current = current.next;
        }
        int temp = first.val;
        first.val  = second.val;
        second.val = temp;

        return head;
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head.next == null) return head;
        ListNode current = head, pointer = head;
        int count = 1;
        while (current != null){
            if(count == k){
                reverseNode(pointer, k);
                pointer = current.next;
                count=0;
            }
            count++;
            current = current.next;
        }
        return head;
    }
    private static void reverseNode(ListNode pointer, int k) {
        if(pointer == null) return;
        List<ListNode> list = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            list.add(i, pointer);
            pointer = pointer.next;
        }
        for (int i = 0; i < k/2; i++) {
            ListNode first = list.get(i);
            int temp = first.val;
            ListNode node = list.get(k - i - 1);
            first.val = node.val;
            node.val = temp;
        }
    }

    public static int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int first = 0, second = 0;
        for (int num : nums) {
            int before = second;
            second = Math.max(first + num, second);
            first = before;
        }
        return second;
    }
    public static int countPrimes(int n) {
        if(n == 0) return n;
        boolean[] notPrime = new boolean[n];
        int number = 0;
        for (int i = 2; i < n; i++) {
            if(notPrime[i]) continue;
            number++;
            for (int j = i*i; j < n; j+=i) {
                notPrime[j] = true;
            }
        }
        return number;
    }

    public static String strings(int A, int B){
        StringBuilder builder = new StringBuilder();
        int max = A, min = B;
        char charMax = 'a', charMin = 'b';
        if(B > A){
            max = B;
            min = A;
            charMax='b';
            charMin='a';
        }
        int maxCount=0;
        while (max != 0 || min != 0){
            if(max > min && maxCount < 2){
                builder.append(charMax);
                maxCount++;
                max--;
            }else{
                builder.append(charMin);
                min--;
                maxCount=0;
            }
        }
        return builder.toString();
    }

    public static int inversion(int[] A) {
        // write your code in Java SE 8
        int count = 0;
        for (int i = 0; i < A.length-1; i++) {
            int a = A[i];
            for (int j = i+1; j < A.length; j++) {
                int b = A[j];
                if(b<a) count++;
            }
        }
        return count > 1000000000 ? -1 : count;
    }

    public static String disappearingPairs(String input){
        StringBuilder builder = new StringBuilder();
        for (char ch : input.toCharArray()){
            int size = builder.length();
            if(size > 0 && builder.charAt(size-1) == ch){
                builder.deleteCharAt(size-1);
            }else{
                builder.append(ch);
            }
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(disappearingPairs("ABCBBCBA"));
//        System.out.println(inversion(new int[]{-1, 6, 3, 4, 7, 4}));


//        System.out.println(countPrimes(10));
//        System.out.println(rob(new int[]{2,7,9,3,1}));
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        node4.next = new ListNode(5);
//        node3.next = node4;
//        node2.next = node3;
//        node1.next = node2;
//        System.out.println(reverseKGroup(node1, 2));

//        System.out.println((int)3/2);
//        System.out.println(networkDelayTime(new int[][]{{1,2,1}, {1,3,2}, {2,3,2}}, 3 ,1));
//        System.out.println(threeSumClosest(new int[]{40,-53,36,89,-38,-51,80,11,-10,76,-30,46,-39,-15,4,72,83,-25,33,-69,-73,-100,-23,-37,-13,
//                -62,-26,-54,36,-84,-65,-51,11,98,-21,49,51,78,-58,-40,95,-81,41,-17,-70,83,-88,-14,-75,-10,-44,-21,6,68,-81,-1,41,-61,-82,-24,
//                45,19,6,-98,11,9,-66,50,-97,-2,58,17,51,-13,88,-16,-77,31,35,98,-2,0,-70,6,-34,-8,78,22,-1,-93,-39,-88,-77,-65,80,91,35,-15,7,
//                -37,-96,65,3,33,-22,60,1,76,-32,}, 292));

    }
}
