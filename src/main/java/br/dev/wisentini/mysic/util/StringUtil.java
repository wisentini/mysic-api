package br.dev.wisentini.mysic.util;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class StringUtil {
    public static String durationMsLongToString(long durationMs) {
        Duration duration = Duration.ofMillis(durationMs);

        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static Optional<Long> durationMsStringToLong(String durationMs) {
        try {
            return Optional.of( LocalTime.parse(durationMs).toSecondOfDay() * 1000L);
        } catch (DateTimeParseException ignored) {
        }

        return Optional.empty();
    }

    public static Optional<Long> toLong(String string) {
        try {
            return Optional.of(Long.parseLong(string));
        } catch (NumberFormatException ignored) {
        }

        return Optional.empty();
    }

    public static String capitalize(String string) {
        if (string == null || string.isBlank() || string.isEmpty()) {
            return string;
        }

        String trimmedString = string.trim();

        return trimmedString.substring(0, 1).toUpperCase() + trimmedString.substring(1).toLowerCase();
    }
}
