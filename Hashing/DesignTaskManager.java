import java.util.*;

class TaskManager {

    private Map<Integer, int[]> taskMap;
    private PriorityQueue<Task> heap;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        heap = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) {
                return b.priority - a.priority; // max priority
            }
            return b.taskId - a.taskId; // max taskId
        });

        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            taskMap.put(taskId, new int[]{userId, priority});
            heap.offer(new Task(taskId, priority));
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[]{userId, priority});
        heap.offer(new Task(taskId, priority));
    }

    public void edit(int taskId, int newPriority) {
        if (taskMap.containsKey(taskId)) {
            int userId = taskMap.get(taskId)[0];
            taskMap.put(taskId, new int[]{userId, newPriority});
            heap.offer(new Task(taskId, newPriority));
        }
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }

    public int execTop() {
        while (!heap.isEmpty()) {
            Task top = heap.poll();
            if (taskMap.containsKey(top.taskId)) {
                int[] data = taskMap.get(top.taskId);
                int actualPriority = data[1];
                if (actualPriority == top.priority) {
                    taskMap.remove(top.taskId);
                    return data[0]; // return userId
                }
                // else: stale task, skip
            }
        }
        return -1;
    }

    private static class Task {
        int taskId;
        int priority;

        public Task(int taskId, int priority) {
            this.taskId = taskId;
            this.priority = priority;
        }
    }
}
