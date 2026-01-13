class Solution {
    public boolean backspaceCompare(String s, String t) {
        return helper(t).equals(helper(s));
    }
    private String helper(String s){
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '#'){
                if(!st.isEmpty()){
                st.pop();
                }
            }else{
                st.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char c : st){
            sb.append(c);
        }
        return sb.toString();
    }
}
