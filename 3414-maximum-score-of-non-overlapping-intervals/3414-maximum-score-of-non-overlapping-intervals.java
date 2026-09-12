import java.util.*;

class Solution {

    static class Interval {
        int start, end, score, index;

        Interval(int start, int end, int score, int index) {
            this.start = start;
            this.end = end;
            this.score = score;
            this.index = index;
        }
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.end != b.end) {
                return Integer.compare(a.end, b.end);
            }
            return Integer.compare(a.index, b.index);
        });

        // prev[i] = number of intervals before i that are compatible
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = i - 1;
            int pos = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (arr[mid].end < arr[i].start) {
                    pos = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            prev[i] = pos + 1;
        }

        Result[][] dp = new Result[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new Result(0, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {

            Interval current = arr[i - 1];

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                Result skip = dp[i - 1][k];

                // Option 2: Take current interval
                Result previous = dp[prev[i - 1]][k - 1];

                List<Integer> takenIndices =
                    new ArrayList<>(previous.indices);

                takenIndices.add(current.index);
                Collections.sort(takenIndices);

                Result take = new Result(
                    previous.score + current.score,
                    takenIndices
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[n][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private Result better(Result a, Result b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        int size = Math.min(a.indices.size(), b.indices.size());

        for (int i = 0; i < size; i++) {
            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i) ? a : b;
            }
        }

        return a.indices.size() <= b.indices.size() ? a : b;
    }
}