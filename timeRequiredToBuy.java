class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i<tickets.length;i++){
            q.add(i);
        }
        int time = 0;
        while(!q.isEmpty()){
            time++;
            int f = q.poll();
            tickets[f]--;
            if(k == f && tickets[f] == 0){
                return time;
            }
            if(tickets[f] != 0){
                q.add(f);
            }
        }
        return time;
    }
}
