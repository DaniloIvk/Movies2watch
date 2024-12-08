package ivk.danilo.movies2watch.models;

import org.jetbrains.annotations.NotNull;

public final class Movie {
    public final String title;
    public final String genre;
    public final int year;
    public final double rating;

    public Movie(String title, String genre, int year, double rating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public Movie(String title, String genre, int year) {
        this(title, genre, year, -1);
    }

    public Movie() {
        this("Austin Powers in Goldmember", "Action · Adventure · Comedy · Crime", 2002, 6.2);
    }

    /**
     * Format year using specified format.
     */
    @NotNull
    public String formatYear(String format) throws Exception {
        this.validateFormat(format);

        return String.format(format, this.year);
    }

    /**
     * Format year using predefined format (<i>%d.</i>).<br>
     * <b>Example:</b> <i>2002.</i>
     */
    public String formatYear() {
        try {
            return this.formatYear("%d.");
        } catch (Exception exception) {
            return exception.toString();
        }
    }

    /**
     * Format rating using specified format.
     */
    @NotNull
    public String formatRating(String format) throws Exception {
        this.validateFormat(format);

        return String.format(format, this.rating);
    }

    /**
     * Format rating using predefined format (<i>%.1f / 5</i>).<br>
     * <b>Example:</b> <i>6.2 / 10</i>
     */
    @NotNull
    public String formatRating() {
        try {
            return this.formatRating("%.1f / 10");
        } catch (Exception exception) {
            return exception.toString();
        }
    }

    /**
     * Validate string format.
     *
     * @param format String containing format character(s) (<i>example: "%d"</i>)
     * @throws Exception When validation fails
     */
    private void validateFormat(String format) throws Exception {
        if (!format.matches("^((%[0-9 .,#(0+-]*[a-zA-Z]){1}[^%]*)$")) {
            throw new Exception("Format string must contain exactly one '%f' character.");
        }
    }
}
