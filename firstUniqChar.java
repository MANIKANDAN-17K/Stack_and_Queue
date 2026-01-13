class Solution {
    public int firstUniqChar(String s) {
        int temp[] = new int[26];
        Queue<Character> val = new LinkedList<>();
        for(int i = 0;i<s.length();i++){
            char ch = s.charAt(i);
            val.add(ch);
            temp[ch - 'a']++;
            while(!val.isEmpty() && temp[val.peek() - 'a'] > 1){
                val.remove();
            }
        }
        return val.isEmpty() ? -1 : s.indexOf(val.peek());
    }
}
