package kata.readable_time;

public class TimeFormatter {
    public String format(final int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Input is lower than the minimum value");
        }
        else if (seconds>=360000) {
            throw new IllegalArgumentException("Input exceeds maximum value");
        }


        final int actualHours = seconds / 3600;
        final int actualMinutes = seconds / 60 - actualHours * 60;
        final int actualSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d",actualHours, actualMinutes, actualSeconds);
    }
}
