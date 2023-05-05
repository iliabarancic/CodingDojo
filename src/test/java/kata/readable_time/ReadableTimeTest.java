package kata.readable_time;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReadableTimeTest {
    private final TimeFormatter sut = new TimeFormatter();

    @Test
    public void testTimeFormat__0seconds() {
        String result = sut.format(0);

        assertThat(result).isEqualTo("00:00:00");
    }

    @Test
    public void testTimeFormat__1second() {
        String result = sut.format(1);

        assertThat(result).isEqualTo("00:00:01");
    }

    @Test
    public void testTimeFormat__2seconds() {
        String result = sut.format(2);

        assertThat(result).isEqualTo("00:00:02");
    }

    @Test
    public void testTimeFormat__60seconds() {
        String result = sut.format(60);

        assertThat(result).isEqualTo("00:01:00");
    }

    @Test
    public void testTimeFormat__61seconds() {
        String result = sut.format(61);

        assertThat(result).isEqualTo("00:01:01");
    }

    @Test
    public void testTimeFormat__3600seconds() {
        String result = sut.format(3600);

        assertThat(result).isEqualTo("01:00:00");
    }

    @Test
    public void testTimeFormat__3601seconds() {
        String result = sut.format(3601);

        assertThat(result).isEqualTo("01:00:01");
    }

    @Test
    public void testTimeFormat__3661seconds() {
        String result = sut.format(3661);

        assertThat(result).isEqualTo("01:01:01");
    }

    @Test
    public void testTimeFormat__359999seconds() {
        String result = sut.format(359999);

        assertThat(result).isEqualTo("99:59:59");
    }

    @Test
    public void testTimeFormat__exceeds359999seconds() {
        assertThatThrownBy(() -> sut.format(360000)).isInstanceOf(IllegalArgumentException.class).hasMessage("Input exceeds maximum value");
    }

    @Test
    public void testTimeFormat__lessThanZeroSeconds() {
        assertThatThrownBy(() -> sut.format(-1)).isInstanceOf(IllegalArgumentException.class).hasMessage("Input is lower than the minimum value");
    }
}
