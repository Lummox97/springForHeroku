package springForHeroku.domains;

import java.util.HashMap;
import java.util.Map;

public enum TaskPrior {
  LOW(4),
  MEDIUM(3),
  HIGH(2),
  IMPORTANT(1);

  TaskPrior(int value) {
    this.typeInt = value;
  }

  private int typeInt;
  private static Map<Integer, TaskPrior> priorityMap = new HashMap<>();

  static {
    for (TaskPrior type : TaskPrior.values()
    ) {
      priorityMap.put(type.typeInt, type);
    }
  }

  public static TaskPrior valueOf(int typeInt) {
    return (TaskPrior) priorityMap.get(typeInt);
  }

  public int getValue() {
    return typeInt;
  }
}
