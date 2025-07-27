import java.util.Map;

record ReshapeConfig(Map<String, Integer> subStatUpgradeCounts, int guaranteedRollLimit) {}