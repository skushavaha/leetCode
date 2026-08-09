class Solution {
    public int largestRectangleArea(int[] arr) {
         int n = arr.length;

        int[] next = nextSmallerEle(arr , n);
        int[] prev = prevSmallerElement(arr , n);
        int area = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int l = arr[i];

            if (next[i] == -1){
                next[i] = n;
            }
            int w = next[i] - prev[i] - 1 ;

            int newArea = l * w;
            area = Math.max(area , newArea);
        }
        return area;
    }

     int[] prevSmallerElement(int[] arr, int n) {

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int[] ans = new int[arr.length];

        for (int i = 0 ; i < arr.length ; i++) {
            int curr = arr[i];
            while (stack.peek() != -1 && arr[stack.peek()] >= curr){
                stack.pop();
            }
            ans[i] = stack.peek();
            stack.push(i);
        }
        return ans;

    }

     int[] nextSmallerEle(int[] arr, int n) {

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int[] ans = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            int curr = arr[i];
            while (stack.peek() != -1 && arr[stack.peek()] >= curr){
                stack.pop();
            }
            ans[i] = stack.peek();
            stack.push(i);
        }
        return ans;
    }
}