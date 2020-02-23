package csmsc256;
//
// Charles Rooney - Project 3 - CMSC 256, Spring 2020, Debra Duke
// This programs accesses a data set of songs from the Bridges API. It searches the data for songs by a certain artist
// provided in the command line or user. It returns a formatted list of songs by the artist in alphabetical order, grouped by album.
//
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SongList {
    public static void main(String[] args){
        // Instance variables
        String artist = "";
        Scanner in = new Scanner(System.in);
        String songsByArtist = "";

        // Assigns the artist search value to the argument in the command line. If there are spaces in the name, the artist name is concatenated into a string at each arg[] index.
        if(args.length != 0){
            if(args.length > 1){
                for(int i = 0; i < args.length; i++){
                    if(i == args.length - 1){
                        artist += args[i];
                    }
                    else{
                        artist += args[i] + " ";
                    }
                }
            }
            else{
                artist = args[0];
            }
        }
        // If no argument, or an invalid argument is entered, prompts the user to assign one manually.
        else{
            System.out.println("Please enter an artist to search for.");
            artist = in.nextLine();
            System.out.println("Searching for " + artist + "...");
            System.out.println(" ");
        }

        songsByArtist = getSongsByArtist(artist);
        System.out.println(songsByArtist);

    }
    public static String getSongsByArtist(String artist){
        // Establish connection to Bridges API, creat lists.
        Bridges bridges = new Bridges(1, "crooney36","700571318135" );
        DataSource songData = bridges.getDataSource();
        List<Song> songsRaw = new ArrayList<Song>();
        List<Song> songsByArtist = new ArrayList<Song>();
        String A = "";
        try{
            songsRaw = new ArrayList<Song>(songData.getSongData());
            System.out.println();
        }
        catch (Exception e){
            System.out.println("Bridges could not be reached.");
        }
        // Iterate over the list of songs... if the artist is equivalent to the target artist, the Song is
        // added to the list to be sorted later in the comparator method.
        for(int i = 0; i < songsRaw.size(); i++){
            if(songsRaw.get(i).getArtist().equals(artist)){
                songsByArtist.add(songsRaw.get(i));
            }
        }
        // List of songs containing the proper artist is sorted alphabetically.
        Collections.sort(songsByArtist, new SongComparator());

        // If there are no songs for the given artist, a proper message is displayed.
        if(songsByArtist.isEmpty()){
            System.out.println("No songs found for " + artist);
        }
        // The list of Songs is displayed in the proper format containing title, artist and album name by using a for each loop.
        else{
            for(Song B : songsByArtist){
                A += "\n" + "Title: " + B.getSongTitle() + " Artist: " + B.getArtist() + " Album: " + B.getAlbumTitle();
            }
        }
        return A;
    }
}