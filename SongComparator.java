package csmsc256;
import bridges.data_src_dependent.Song;

import java.util.Comparator;

public class SongComparator implements Comparator<Song> {

    @Override
    public int compare(Song o1, Song o2) {
        // Compares the song albums alphabetically, and if the album is the same then the song titles are sorted alphabetically.
        int A;
        A = o1.getAlbumTitle().compareTo(o2.getAlbumTitle());
        if (A == 0) {
            A = o1.getSongTitle().compareTo(o2.getSongTitle());
        }
        return A;
    }
}

