import java.util.ArrayList;
import java.util.Scanner;

public class MovieMenu {
    static Boolean movieMenuRunning = false;
    
    static Scanner sc = new Scanner(System.in);

    public static void MovieMenu(){
        movieMenuRunning = true;
        printMovieMenu();
        while(movieMenuRunning){
            String input = sc.nextLine();
            switch(input){
                case "1":
                    System.out.println("Printing all Movies");
                    Movie.printAll();
                    break;
                case "2":
                    System.out.println("Add a movie");
                    addMovie();
                    break;
                case "3":
                    System.out.println("Modify a movie");
                    modifyMovie();
                    break;
                case "4":
                    System.out.println("Enter a Genre: ");
                    input = sc.nextLine();
                    System.out.println("Printing Movies that contains: " + input);
                    ArrayList<Movie> resultGenreMovies = Movie.searchByGenre(input);
                    for (Movie i : resultGenreMovies) {
                        System.out.println(i);
                    }
                    break;
                case "5":
                    System.out.println("Enter an Director: ");
                    input = sc.nextLine();
                    System.out.println("Printing Movies that contains: " + input);
                    ArrayList<Movie> resultDirectorMovies = Movie.searchByDirector(input);
                    for (Movie i : resultDirectorMovies) {
                        System.out.println(i);
                    }
                    break;
                case "6":
                    System.out.println("Enter an Title: ");
                    input = sc.nextLine();
                    System.out.println("Printing Movies that contains: " + input);
                    ArrayList<Movie> resultTitleMovies = Movie.searchByTitle(input);
                    for (Movie i : resultTitleMovies) {
                        System.out.println(i);
                    }
                    break;
                case "7":
                    System.out.println("Remove a Movie");
                    removeMovie();
                    break;
                case "9":
                    System.out.println("Exiting the Movie branch, you are now in the main Menu");
                    movieMenuRunning = false;
                    break;
                default:
                    System.out.println("invalid input");
            }

        }

    }

    public static void printMovieMenu(){
        System.out.println("Menu:");
        System.out.println("1: Overview over all Movies");
        System.out.println("2: Add a movie");
        System.out.println("3: Modify a movie");
        System.out.println("4: Find a movie based on genre");
        System.out.println("5: Find a movie based on director");
        System.out.println("6: Find a movie based on title");
        System.out.println("7: Remove a movie");
        System.out.println("8: Exit Movies");
    }


    public static void removeMovie(){
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        int userinput;
        int counter = 0;
        for (Movie i : Movie.listOfMovies) {
            System.out.println(counter + " ||| " + i);
            counter = counter + 1;
        }
        while(inputAccepted.equals(false)){
            try{
                int maxvalue = Movie.listOfMovies.size();
                userinput = sc.nextInt();
                if(maxvalue >= userinput){
                    userinput = userinput - 1;
                    System.out.println(Movie.listOfMovies.get(userinput));
                    MovieDB.removeMovieDB(Movie.listOfMovies.get(userinput));
                    Movie.listOfMovies.remove(userinput);
                    System.out.println("This Movie has now been removed, type 1 to see all Movies");
                    inputAccepted = true;
                }else{
                    maxvalue = Movie.listOfMovies.size();
                    System.out.println("Input was out of bounds. Max value: " + maxvalue + " || your input: " + userinput);
                }
            }catch (Exception e){
                System.out.println("Seems like you inputted something other then an int");
                sc.nextLine();
            }
        }
    }
    public static void addMovie(){
        boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter title:");
        String title = sc.nextLine();
        System.out.println("Enter director:");
        String director = sc.nextLine();
        System.out.println("Enter length:");
        String length = "12";
        while (!inputAccepted){
            length = sc.nextLine();
            try{
                int pagecountint = Integer.parseInt(length);
                inputAccepted = true;
            }catch(Exception e){
                System.out.println("Length input is invalid, try entering a number");
            }
        }

        System.out.println("Enter genre:");
        String genre = sc.nextLine();
        System.out.println("Movie has been added");
        MovieDB.addMovieToDB(title, director, length, genre);

    }
    public static void modifyMovie(){
        Boolean modifyingmoivecompleted = false;
        Boolean inputAccepted = false;
        Scanner sc = new Scanner(System.in);

        int movieselector = 0;
        String moviemodificationselector = "";
        String modfication = "";
        int counter = 0;
        for (Movie i : Movie.listOfMovies) {
            System.out.println(counter + " ||| " + i);
        }
        if(Movie.listOfMovies.size() == 0){
            System.out.println("There are no Movies in storage, and we cant modify something that doesnt exist can we? ;)");
        }else{
            while (inputAccepted.equals(false)){
                try {
                    movieselector = sc.nextInt();
                    if (Movie.listOfMovies.size() > movieselector){
                        inputAccepted = true;
                    }else{
                        int maxvalue = Movie.listOfMovies.size();
                        System.out.println("input was out of bounds. Max value: " + maxvalue + " ||| your input: " + movieselector);
                    }
                }catch (Exception e){
                    System.out.println("Seems like you inputted something other then an int");
                    sc.nextLine();
                }
            }
            System.out.println("You have selected to modify");
            System.out.println(Movie.listOfMovies.get(movieselector));
            while (modifyingmoivecompleted.equals(false)){
                System.out.println("Select which field to modify");
                printModifyOptions();
                while (moviemodificationselector.equals("")){
                    moviemodificationselector = sc.nextLine();
                }
                switch (moviemodificationselector){
                    case "0":{
                        System.out.println("Current Title: " + Movie.listOfMovies.get(movieselector).getTitle());
                        System.out.println("Enter a new title");
                        modfication = sc.nextLine();
                        Movie.listOfMovies.get(movieselector).setTitle(modfication);
                        MovieDB.writeMovieChangesToDB(Movie.listOfMovies.get(movieselector), "title");
                        moviemodificationselector = "";
                        break;
                    }
                    case "1": {
                        System.out.println("Current Director: " + Movie.listOfMovies.get(movieselector).getDirector());
                        System.out.println("Enter a new Director");
                        modfication = sc.nextLine();
                        Movie.listOfMovies.get(movieselector).setDirector(modfication);
                        MovieDB.writeMovieChangesToDB(Movie.listOfMovies.get(movieselector), "director");
                        moviemodificationselector = "";
                        break;
                    }
                    case "2": {
                        System.out.println("Current Length: " + Movie.listOfMovies.get(movieselector).getLength());
                        System.out.println("Enter a new length");
                        modfication = sc.nextLine();
                        Movie.listOfMovies.get(movieselector).setLength(modfication);
                        MovieDB.writeMovieChangesToDB(Movie.listOfMovies.get(movieselector), "length");
                        moviemodificationselector = "";
                        break;
                    }
                    case "3": {
                        System.out.println("Current Genre: " + Movie.listOfMovies.get(movieselector).getGenre());
                        System.out.println("Enter a new genre");
                        modfication = sc.nextLine();
                        Movie.listOfMovies.get(movieselector).setGenre(modfication.toUpperCase());
                        MovieDB.writeMovieChangesToDB(Movie.listOfMovies.get(movieselector), "genre");
                        moviemodificationselector = "";
                        break;
                    }
                    case "9": {
                        System.out.println("Exiting modification menu");
                        modifyingmoivecompleted = true;
                    }
                    default: {
                        moviemodificationselector = "";
                        break;
                    }
                }

            }
        }
    }
    public static void printModifyOptions(){
        System.out.println("0: Edit Title");
        System.out.println("1: Edit director");
        System.out.println("2: Edit length in minutes");
        System.out.println("3: Edit Genre");
        System.out.println("9: Exit modification");
    }
}
