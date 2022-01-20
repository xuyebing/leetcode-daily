class Solution {
    // public boolean stoneGameIX(int[] stones) {
    //     // split stones to 3 sets: (A) %3 == 0; (B) %3 == 1; (C) %3 == 2
    //     // in the Kth round, based on sum(k-1)%3, to decide how to pick the Kth value.
    //     // if (sum(k-1)%3 == 0) -> if k==0, pick up the Kth value from SetB or SetC, else k-1 round person lose.
    //     // if (sum(k-1)%3 == 1) -> pick up the Kth value from SetA or SetB
    //     // if (sum(k-1)%3 == 2) -> pick up the Kth value from SetA or SetC

    //     // Split stones to 3 sets
    //     List<Integer> listA = new ArrayList<>();
    //     List<Integer> listB = new ArrayList<>();
    //     List<Integer> listC = new ArrayList<>();

    //     int l = stones.length;
    //     for (int i = 0; i < l; i++) {
    //         int key = stones[i] % 3;
    //         if (key == 0) {
    //             listA.add(i);
    //         } else if (key == 1) {
    //             listB.add(i);
    //         } else {
    //             listC.add(i);
    //         }
    //     }

    //     int k = 0; // k is the round number, k%2 == 0 means this round is Alice, otherwise it's Bob.
    //     int sum = 0;
    //     while (k < l) {
    //         int sumMod = sum % 3;
    //         if (sumMod == 0) {
    //             // if (sum(k-1)%3 == 0) -> if k==0, pick up the Kth value from SetB or SetC, else k-1 round person lose.
    //             if (k == 0) {
    //                 if (!listB.isEmpty()) {
    //                     int index = listB.remove(0);
    //                     sum += stones[index];
    //                     k++;
    //                     continue;
    //                 }
    //                 if (!listC.isEmpty()) {
    //                     int index = listC.remove(0);
    //                     sum += stones[index];
    //                     k++;
    //                     continue;
    //                 }
    //                 // reach here, means listB and listC are all empty, can only choose listA
    //                 int index = listA.remove(0);
    //                 sum+= stones[index];
    //                 k++;
    //                 continue;
    //             } else {
    //                 // means k-1 step's player has lost the game.
    //                 int player = (k-1)%2;
    //                 if (player == 0) {
    //                     return false; // Alice loses.
    //                 } else {
    //                     return true; // Alice wins.
    //                 }
    //             }
    //         } else if (sumMod == 1) {
    //             // if (sum(k-1)%3 == 1) -> pick up the Kth value from SetA or SetB
    //             if (!listA.isEmpty()) {
    //                 int index = listA.remove(0);
    //                 sum += stones[index];
    //                 k++;
    //                 continue;
    //             }
    //             if (!listB.isEmpty()) {
    //                 int index = listB.remove(0);
    //                 sum += stones[index];
    //                 k++;
    //                 continue;
    //             }
    //             // reach here, means ListA and ListB are all empty, can only choose listC
    //             int index = listC.remove(0);
    //             sum += stones[index];
    //             k++;
    //             continue;
    //         } else {
    //             // if (sum(k-1)%3 == 2) -> pick up the Kth value from SetA or SetC
    //             if (!listA.isEmpty()) {
    //                 int index = listA.remove(0);
    //                 sum += stones[index];
    //                 k++;
    //                 continue;
    //             }
    //             if (!listC.isEmpty()) {
    //                 int index = listC.remove(0);
    //                 sum += stones[index];
    //                 k++;
    //                 continue;
    //             }
    //             // reach here, means ListA and ListC are all empty, can only choose listB
    //             int index = listB.remove(0);
    //             sum += stones[index];
    //             k++;
    //             continue;
    //         }
    //     }
    //     // if reaches here, means k == l, i.e. all the stones have been removed.
    //     int sumMod = sum % 3;
    //     if (sumMod == 0) {
    //         // means the last person loses.
    //         int player = (k-1)%2;
    //         if (player == 0) {
    //             return false; // Alice loses
    //         } else {
    //             return true; // Bob loses
    //         }
    //     } else {
    //         // means all the stones have been removed, but the total sum%3 != 0, Bob wins
    //         return false;
    //     }
    // }

    /* 2nd Version */
    // public boolean stoneGameIX(int[] stones) {
    //     // split stones to 3 sets: (A) %3 == 0; (B) %3 == 1; (C) %3 == 2
    //     // in the Kth round, based on sum(k-1)%3, to decide how to pick the Kth value.
    //     // if (sum(k-1)%3 == 0) -> if k==0, pick up the Kth value from SetB or SetC, else k-1 round person lose.
    //     // if (sum(k-1)%3 == 1) -> pick up the Kth value from SetA or SetB
    //     // if (sum(k-1)%3 == 2) -> pick up the Kth value from SetA or SetC

    //     // Split stones to 3 sets
    //     List<Integer> listA = new ArrayList<>();
    //     List<Integer> listB = new ArrayList<>();
    //     List<Integer> listC = new ArrayList<>();

    //     int l = stones.length;
    //     for (int i = 0; i < l; i++) {
    //         int key = stones[i] % 3;
    //         if (key == 0) {
    //             listA.add(i);
    //         } else if (key == 1) {
    //             listB.add(i);
    //         } else {
    //             listC.add(i);
    //         }
    //     }

    //     int k = 0; // k is the round number, k%2 == 0 means this round is Alice, otherwise it's Bob.
    //     int sum = 0;
    //     while (k < l) {
    //         int sumMod = sum % 3;
    //         if (sumMod == 0) {
    //             // if (sum(k-1)%3 == 0) -> if k==0, pick up the Kth value from SetB or SetC, else k-1 round person lose.
    //             if (k == 0) {
    //                 List<Integer> res = null;
    //                 if (listB.size() > listC.size()) {
    //                     res = listB;
    //                 } else {
    //                     if (listC.size() == 0) {
    //                         res = listA;
    //                     } else {
    //                         res = listC;
    //                     }
    //                 }
    //                 // reach here, means listB and listC are all empty, can only choose listA
    //                 int index = res.remove(0);
    //                 sum+= stones[index];
    //                 k++;
    //                 continue;
    //             } else {
    //                 // means k-1 step's player has lost the game.
    //                 int player = (k-1)%2;
    //                 if (player == 0) {
    //                     return false; // Alice loses.
    //                 } else {
    //                     return true; // Alice wins.
    //                 }
    //             }
    //         } else if (sumMod == 1) {
    //             // if (sum(k-1)%3 == 1) -> pick up the Kth value from SetA or SetB
    //             List<Integer> res = null;
    //             if (listA.size() > listB.size()) {
    //                 res = listA;
    //             } else {
    //                 if (listB.size() == 0) {
    //                     res = listC;
    //                 } else {
    //                     res = listB;
    //                 }
    //             }
    //             // reach here, means listA and listB are all empty, can only choose listC
    //             int index = res.remove(0);
    //             sum+= stones[index];
    //             k++;
    //             continue;
    //         } else {
    //             // if (sum(k-1)%3 == 2) -> pick up the Kth value from SetA or SetC
    //             List<Integer> res = null;
    //             if (listA.size() > listC.size()) {
    //                 res = listA;
    //             } else {
    //                 if (listC.size() == 0) {
    //                     res = listB;
    //                 } else {
    //                     res = listC;
    //                 }
    //             }
    //             // reach here, means listA and listC are all empty, can only choose listB
    //             int index = res.remove(0);
    //             sum+= stones[index];
    //             k++;
    //             continue;
    //         }
    //     }
    //     // if reaches here, means k == l, i.e. all the stones have been removed.
    //     int sumMod = sum % 3;
    //     if (sumMod == 0) {
    //         // means the last person loses.
    //         int player = (k-1)%2;
    //         if (player == 0) {
    //             return false; // Alice loses
    //         } else {
    //             return true; // Bob loses
    //         }
    //     } else {
    //         // means all the stones have been removed, but the total sum%3 != 0, Bob wins
    //         return false;
    //     }
    // }
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3]; // cnt[0] = sum%3==0, cnt[1] = sum%3==1, cnt[2] = sum%3==2;
        for (int i : stones) {
            cnt[i%3]++;
        }
        if (cnt[0]%2 == 0) {
            return cnt[1]>=1 && cnt[2] >=1;
        } else {
            return Math.abs(cnt[1] - cnt[2]) > 2;
        }

    }
}
