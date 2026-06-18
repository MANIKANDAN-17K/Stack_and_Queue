class Solution {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n,-1));
        Stack<Integer> st = new Stack<>();

        for(int i = n-1;i>= 0;i--){
            while(!st.isEmpty() && st.peek() >= arr[i]){
                st.pop();
            }
            if (!st.isEmpty()){
                ans.set(i,st.peek());
            }
            st.push(arr[i]);
        }
        return ans;
    }
}
