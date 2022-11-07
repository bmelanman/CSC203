import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestCases {
    private static final Song[] songs = new Song[]{
            new Song("Decemberists", "The Mariner's Revenge Song", 2005),
            new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
            new Song("Avett Brothers", "Talk on Indolence", 2006),
            new Song("City and Colour", "Sleeping Sickness", 2007),
            new Song("Foo Fighters", "Baker Street", 1997),
            new Song("Queen", "Bohemian Rhapsody", 1975)
    };

    final Song extraSong1 = new Song("Gerry Rafferty", "Baker Street", 1998);
    final Song extraSong2 = new Song("Gerry Rafferty", "Baker Street", 1978);

    List<Song> songList = new ArrayList<>(Arrays.asList(songs));

    @Test
    public void testArtistComparator() {

        List<Song> expectedList = Arrays.asList(
                new Song("Avett Brothers", "Talk on Indolence", 2006),
                new Song("City and Colour", "Sleeping Sickness", 2007),
                new Song("Decemberists", "The Mariner's Revenge Song", 2005),
                new Song("Foo Fighters", "Baker Street", 1997),
                new Song("Queen", "Bohemian Rhapsody", 1975),
                new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
        );

        songList.sort(new ArtistComparator());
        assertEquals(expectedList, songList);

        songList.sort(new ArtistComparator());
        assertEquals(expectedList, songList);
    }

    @Test
    public void testLambdaTitleComparator() {

        List<Song> expectedList = Arrays.asList(
                new Song("Foo Fighters", "Baker Street", 1997),
                new Song("Queen", "Bohemian Rhapsody", 1975),
                new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
                new Song("City and Colour", "Sleeping Sickness", 2007),
                new Song("Avett Brothers", "Talk on Indolence", 2006),
                new Song("Decemberists", "The Mariner's Revenge Song", 2005)
        );

        Comparator<Song> TitleComparator = (Song s1, Song s2) -> s1.title().compareTo(s2.title());

        songList.sort(TitleComparator);
        assertEquals(expectedList, songList);
    }

    @Test
    public void testYearExtractorComparator() {

        List<Song> expectedList = Arrays.asList(
                new Song("Queen", "Bohemian Rhapsody", 1975),
                new Song("Foo Fighters", "Baker Street", 1997),
                new Song("Decemberists", "The Mariner's Revenge Song", 2005),
                new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
                new Song("Avett Brothers", "Talk on Indolence", 2006),
                new Song("City and Colour", "Sleeping Sickness", 2007)
        );

        Comparator<Song> TitleComparator = Comparator.comparing(Song::year);

        songList.sort(TitleComparator);
        assertEquals(expectedList, songList);
    }


    @Test
    public void testThenComparing() {

        songList.add(extraSong1);
        songList.add(extraSong2);

        List<Song> expectedList = Arrays.asList(
                new Song("Foo Fighters", "Baker Street", 1997),
                new Song("Gerry Rafferty", "Baker Street", 1998),
                new Song("Gerry Rafferty", "Baker Street", 1978),
                new Song("Queen", "Bohemian Rhapsody", 1975),
                new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
                new Song("City and Colour", "Sleeping Sickness", 2007),
                new Song("Avett Brothers", "Talk on Indolence", 2006),
                new Song("Decemberists", "The Mariner's Revenge Song", 2005)
        );

        Comparator<Song> TitleArtistComparator = Comparator.comparing(Song::title);
        TitleArtistComparator.thenComparing(new ArtistComparator());

        songList.sort(TitleArtistComparator);

        assertEquals(expectedList, songList);
    }

    @Test
    public void runSort() {

        List<Song> songList = new ArrayList<>(Arrays.asList(songs));

        songList.add(extraSong1);
        songList.add(extraSong2);

        List<Song> expectedList = Arrays.asList(
                new Song("Avett Brothers", "Talk on Indolence", 2006),
                new Song("City and Colour", "Sleeping Sickness", 2007),
                new Song("Decemberists", "The Mariner's Revenge Song", 2005),
                new Song("Foo Fighters", "Baker Street", 1997),
                new Song("Gerry Rafferty", "Baker Street", 1998),
                new Song("Gerry Rafferty", "Baker Street", 1978),
                new Song("Queen", "Bohemian Rhapsody", 1975),
                new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
        );

        // Artist, then title, and then year
        Comparator<Song> ArtistTitleYearComparator = Comparator.comparing(Song::artist);
        ArtistTitleYearComparator.thenComparing(Song::title);
        ArtistTitleYearComparator.thenComparing(Song::year);

        songList.sort(
                ArtistTitleYearComparator
        );

        assertEquals(songList, expectedList);
    }
}
