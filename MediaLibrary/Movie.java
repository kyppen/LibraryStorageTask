import java.util.ArrayList;
import java.util.Scanner;

public class Movie extends Media{
    public static ArrayList<Movie> listOfMovies = new ArrayList<>();
    private String director;
    private String length;

    Movie(String title, String director, String length, String genre){
        this.setTitle(title);
        this.director = director;
        this.length = length;
        this.setGenre(genre);

    }
    public void setLength(String length){
        this.length = length;
    }
    public String getLength(){
        return length;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public String getDirector(){
        return director;
    }
    public String toString(){
        return getId() + " |||  Title: " + getTitle() + " ||| Director: " + getDirector() + " || Length in minutes: " + getLength() + " || Genre: " + getGenre();
    }

    public static void printAll(){
        //MovieDB.writeMoviesToDB();
        for (Movie i : listOfMovies){
            System.out.println(i);
        }
    }
    public static void addToList(String id, String title, String director, String length, String genre){
        listOfMovies.add(new Movie(title, director, length, genre));
        listOfMovies.get(listOfMovies.size() - 1).setId(id);
    }

    public static void removeMovie(){
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        int userinput;
        for (Movie i : listOfMovies) {
            System.out.println(i);
        }
        while(inputAccepted.equals(false)){
            try{
                int maxvalue = listOfMovies.size();
                userinput = sc.nextInt();
                if(maxvalue >= userinput){
                    userinput = userinput - 1;
                    System.out.println(listOfMovies.get(userinput));
                    //MovieDB.removeMovieDB(listOfMovies.get(userinput));
                    listOfMovies.remove(userinput);
                    System.out.println("This Movie has now been removed, type 1 to see all Movies");
                    inputAccepted = true;
                }else{
                    maxvalue = listOfMovies.size();
                    System.out.println("Input was out of bounds. Max value: " + maxvalue + " || your input: " + userinput);
                }
            }catch (Exception e){
                System.out.println("Seems like you inputted something other then an int");
                sc.nextLine();
            }
        }
    }
    public static void modifyMovie(){
        Boolean modifyingmoivecompleted = false;
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);

        int movieselector = 0;
        String moviemodificationselector = "";
        String modification = "";
        for (Movie i : listOfMovies) {
            System.out.println(i);
        }
        if(listOfMovies.size() == 0){
            System.out.println("There are no books in storage, and we cant modify something that doesnt exist can we? ;)");
        }else{
            while (inputAccepted.equals(false)){
                try {
                    movieselector = sc.nextInt() - 1;
                    if (listOfMovies.size() > movieselector){
                        inputAccepted = true;
                    }else{
                        int maxvalue = listOfMovies.size() - 1;
                        System.out.println("input was out of bounds. Max value: " + maxvalue + " ||| your input: " + movieselector);
                    }
                }catch (Exception e){
                    System.out.println("Seems like you inputted something other then an int");
                    sc.nextLine();
                }
            }
            System.out.println("You have selected to modify");
            System.out.println(listOfMovies.get(movieselector));
            while (modifyingmoivecompleted.equals(false)){
                System.out.println("Select which field to modify");
                printModifyOptions();
                while (moviemodificationselector.equals("")){
                    moviemodificationselector = sc.nextLine();
                }

            }
        }
    }
    public static void printModifyOptions(){
        System.out.println("0: Edit Title");
        System.out.println("1: Edit director");
        System.out.println("2: Edit length in minutes");
        System.out.println("3: Edit Genre");
        System.out.println("4: Exit modification");
    }
}
