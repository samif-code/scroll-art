import java.util.Random;

public class ScrollArt {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int  BIGFISH_WIDTH = 20;
    static final int BIGFISH_HEIGHT = 8;
    static final int FISH_WIDTH = 12;
    static final int FISH_HEIGHT = 10;
    static final int BONES_WIDTH = 21;
    static final int BONES_HEIGHT = 12;
    static final int FISHV_WIDTH = 10;
    static final int FISHV_HEIGHT = 10;
    static final int TURTLE_WIDTH = 11;
    static final int TURTLE_HEIGHT = 9;
    static final int PENGUIN_WIDTH = 45;
    static final int PENGUIN_HEIGHT = 40;
    static final Random rand = new Random();

              
    public static void main(String[] args) throws InterruptedException {

        
        char[][] nextRows = new char[45][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
            
        }
        char[] prevRow = nextRows[0];
        while (true) {
            // At each column on the top row, 1% chance to add a new image
            for (int x = 0; x < WIDTH - 45; x += 46) {
                if (isBlank(prevRow, x) && rand.nextDouble() < 0.08) {
                    loadNextRowsWithImage(nextRows, x);
                }
            }

            // THIS CODE IS MINE
            for (int x = 0; x < WIDTH; x += BIGFISH_WIDTH + 1) {
                if (rand.nextDouble() < 0.07) {
                    char[][] img = randomImage();
                    if (fits(img, x) && isClear(nextRows, prevRow, img, x)) {
                        loadNextRowsWithImage(nextRows, img, x);
                    }
                }
            }

            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(200); // Delay in ms

            //System.err.println("average time per frame: " + (time / iterations) + " ms");
            //this was distracting me from the art, so I commented it out.
        }
    }

    static boolean isBlank(char[] prevRow, int x) {
        for (int i = x; i < x + 45; i++) {
            if (prevRow[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    // pick an image first, then build only that one
    static char[][] randomImage() {
        int choice = rand.nextInt(5);
        if (choice == 4) {
            return getBigFish();
        }
        if (choice == 1) {
            return getFish();
    
        }
        if (choice == 2) {
            return getFishv();
        }
        if (choice == 6) {
            return getPenguin();
        }
        if (choice == 0) {
            return getTurtle();
    
        }
        return getBones();

        

        
    
    }

    // my code
    static boolean fits(char[][] img, int x) {
        return x + img[0].length <= WIDTH;
    }

    // #2: checking the row just printed and every upcoming row the image will
    // cover are blank across the image's full width
    //my code
    static boolean isClear(char[][] nextRows, char[] prevRow, char[][] img, int x) {
        int w = img[0].length;
        for (int i = x; i < x + w; i++) {
            if (prevRow[i] != ' ') {
                return false;
            }
        }
        for (int iy = 0; iy < img.length; iy++) {
            for (int i = x; i < x + w; i++) {
                if (nextRows[iy][i] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }
//THIS IS ELLA'S CODE FOR A PENGUIN ASCII ART
    static char[][] getPenguin() {
        char[][]img=new char [40][45];
        for(int y=0; y<40; y++ ){
            for(int x=0; x<45; x++){
                img[y][x]=' ';
            }
        }
        // outer body
        
        for(int i = 14; i <= 24; i++){
            img[1][i] = '_';
        }

        

        img[2][13] = '/';
        img[3][12] = '/';
        img[3][11] = '_';
        img[4][10] = '/';
        img[5][9] = '/';
        img[6][8] = '/';
        img[7][7] = '/';
        img[8][6] = '/';
        img[9][6] = '|';
        img[10][6] = '|';
        img[11][6] = '|';
        img[12][7] = '\\';
        img[13][8] = '\\';
        img[14][8] = '/';
        img[15][7] = '/';
        img[16][6] = '/';
        img[17][5] = '/';
        img[18][4] = '/';
        img[19][3] = '/';

        for(int i = 20; i <= 26; i++){
            img[i][2] = '|';
        }

        img[27][2] = '\\';
        img[28][3] = '\\';
        img[28][4] = '/';
        img[27][5] = '/';
        img[26][5] = '|';
        img[25][6] = '/';
        img[24][6] = '|';
        img[23][6] = '|';
        img[22][7] = '/';

        for(int i = 23; i <= 30; i++){
            img[i][7] = '|';
        }

        img[31][8] = '\\';
        img[32][9] = '\\';
        img[33][10] = '\\';
        img[34][11] = '\\';

        img[2][25] = '\\';
        img[3][26] = '\\';
        img[3][27] = '_';
        img[4][28] = '\\';
        img[5][29] = '\\';
        img[5][30] = '_';
        img[6][31] = '\\';

        for(int i = 32; i <= 36; i++){
            img[6][i] = '_';
        }

        img[7][37] = '\\';
        img[7][38] = '_';
        img[7][39] = '_';
        img[7][40] = '_';
        img[7][41] = '_';
        img[8][41] = '/';
        img[9][40] = '/';
    
        for(int i = 39; i >= 34; i--){
            img[9][i] = '_';
        }

        img[10][33] = '/';
        img[11][32] = '/';
        img[12][31] = '/';

        for(int i = 31; i <= 40; i++){
            img[i - 18][i] = '\\';
        }

        for(int i = 23; i <= 29; i++){
            img[i][41] = '|';
        }

        img[29][40] = '\\';
        img[28][39] = '\\';
        img[27][38] = '\\';
        img[26][37] = '\\';
        img[25][37] = '|';
        img[24][37] = '|';
        img[23][36] = '\\';
        
        for(int i = 23; i <= 30; i++){
            img[i][35] = '|';
        }

        img[31][34] = '/';
        img[32][33] = '/';
        img[33][32] = '/';
        img[34][31] = '/';

        for(int i = 12; i <= 30; i++){
            img[34][i] = '_';
        }
        // feet

        img[35][15] = '|';
        img[36][15] = '|';
        img[35][19] = '|';
        img[36][20] = '\\';
        img[37][16] = '\\';
        img[37][17] = '/';
        img[37][18] = '\\';
        img[37][19] = '/';
        img[37][20] = '\\';
        img[37][21] = '\\';

        img[35][24] = '|';
        img[36][24] = '|';
        img[35][28] = '|';
        img[36][29] = '\\';
        img[37][25] = '\\';
        img[37][26] = '/';
        img[37][27] = '\\';
        img[37][28] = '/';
        img[37][29] = '\\';
        img[37][30] = '\\';

        // inner body

        for(int i = 16; i <= 24; i++){
            img[4][i] = '_';
        }

        img[5][25] = '\\';
        img[5][26] = '_';
        img[6][27] = '\\';
        img[6][28] = '_';
        img[7][29] = '\\';
        img[8][30] = '\\';
        img[9][30] = '/';
        img[10][29] = '/';
        img[10][28] = '_';
        img[11][27] = '/';
        img[12][26] = '/';
        img[13][26] = '|';
        img[14][27] = '\\';
        img[15][28] = '\\';
        img[16][29] = '\\';
        img[17][30] = '\\';

        for(int i = 17; i <= 28; i++){
            img[i][30] = '|';
        }

        img[29][30] = '/';
        img[30][29] = '/';
        img[31][28] = '/';

        img[5][15] = '/';
        img[6][14] = '/';
        img[7][13] = '/';
        img[8][12] = '/';
        img[9][12] = '|';
        img[10][13] = '\\';
        img[11][14] = '\\';
        img[12][14] = '|';
        img[13][13] = '/';

        for(int i = 14; i <= 28; i++){
            img[i][12] = '|';
        }

        img[29][13] = '\\';
        img[30][14] = '\\';
        img[31][15] = '\\';

        for(int i = 16; i <= 27; i++){
            img[31][i] = '_';
        }

        // eye

        img[7][23] = '@';

        // signature
        img[39][4] = 'E';
        img[39][5] = 'Y';
        return img;
    }

//THIS IS VIOLA'S CODE FOR THE FISH ASCII ART
    static char[][] getFishv() {
        char[][] img = new char[FISHV_HEIGHT][FISHV_WIDTH];
        for (int y = 0; y < FISHV_HEIGHT; y++) {
            for (int x = 0; x < FISHV_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        img[0][2] = 'O';
        img[1][3] = 'O';
        img[2][2] = '/';
        img[2][3] = '\\';
        img[2][4] = '/';
        img[2][5] = '\\';
        img[3][1] = '/';
        img[3][3] = '@';
        img[3][6] = '\\';
        img[4][1] = '\\';
        img[4][6] = '/';
        img[5][2] = '\\';
        img[5][5] = '/';
        img[6][5] = '\\';
        img[6][2] = '/';
        img[7][1] = '/';
        img[7][6] = '\\';
        img[8][1] = '-';
        img[8][2] = '-';
        img[8][3] = '-';
        img[8][4] = '-';
        img[8][5] = '-';
        img[8][6] = '-';
        img[8][7] = 'V';
        img[8][8] = 'C';
        return img;
    }
 static char[][] getTurtle() {
        char[][] img = new char[TURTLE_HEIGHT][TURTLE_WIDTH];
        // fill with empty space
        for (int y = 0; y < TURTLE_HEIGHT; y++) {
            for (int x = 0; x < TURTLE_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // THIS IS VIOLA'S CODE FOR THE TURTLE ASCII ART
        // then fill individual characters
        img[0][4] = '_';
        img[0][5] = '_';
        img[0][6] = '_';
        img[1][3] = '/';
        img[1][4] = '@';
        img[1][6] = '@';
        img[1][7] = '\\';

        img[2][3] = '\\';
        img[2][7] = '/';
        img[3][1] = '_';
        img[3][2] = '_';
        img[3][3] = '/';
        img[3][4] = '/';
        img[3][5] = '|';
        img[3][6] = '\\';
        img[3][7] = '\\';
        img[3][8] = '_';

        img[4][0] = '/';
        img[4][2] = '/';
        img[4][3] = '\\';
        img[4][4] = '\\';
        img[4][5] = '|';
        img[4][6] = '/';
        img[4][7] = '/';
        img[4][8] = '\\';
        img[4][10] = '\\';

        img[5][0] = '\\';
        img[5][1] = '/';
        img[5][2] = '\\';
        img[5][3] = '/';
        img[5][4] = '/';
        img[5][5] = '|';
        img[5][6] = '\\';
        img[5][7] = '\\';
        img[5][8] = '/';
        img[5][9] = '\\';
        img[5][10] = '/';

        img[6][3] = '\\';
        img[6][4] = '\\';
        img[6][5] = '|';
        img[6][6] = '/';
        img[6][7] = '/';

        img[7][4] = '/';
        img[7][5] = '/';
        img[7][6] = '\\';
        img[7][7] = '\\';
        img[8][4] = '-';
        img[8][7] = '-';



        return img;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, int x) {
       

        AsciiArt[] images = { new AsciiArt(getBigFish()), new AsciiArt(getFish()), new AsciiArt(getBones()), new AsciiArt(getTurtle()), new AsciiArt(getFishv()), new AsciiArt(getPenguin()) };
        AsciiArt art = images[rand.nextInt(images.length)];
        AsciiArt widestArt = AsciiArt.getWidestArt(images);
        art.resize(widestArt.width);

        for (int iy = 0; iy < art.height; iy++) {
            for (int ix = 0; ix < art.width; ix++) {
                nextRows[iy][x + ix] = art.img[iy][ix];
            }
        }
    }

    private static void loadNextRowsWithImage(char[][] nextRows, char[][] img, int x) {
        for (int iy = 0; iy < img.length; iy++) {
            for (int ix = 0; ix < img[iy].length; ix++) {
                nextRows[iy][x + ix] = img[iy][ix];
            }
        }
    }
    

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }



    // rename your function here
    static char[][] getBigFish() {
        char[][] img = new char[BIGFISH_HEIGHT][BIGFISH_WIDTH];
        // fill with empty space
        for (int y = 0; y < BIGFISH_HEIGHT; y++) {
            for (int x = 0; x < BIGFISH_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //big fish
        //top 
         
        img[2][7] = '_';
        img[2][8] = '_'; 
        img[2][9] = '_';
        img[2][10] = '_';    
        img[1][11] = '|';   
        img[2][12] = '_';   
        img[2][13] = '_'; 
        img[2][14] = '_';   
        img[2][15] = '_';   
        img[2][16] = '_';   
        img[2][17] = '_';   

        img[3][4] = '\\';     
        img[3][6] = '/';  
        //eye
        img[3][17] = 'o';  

        img[3][18] = '\\';  

        img[4][5] = '>';  
        img[4][19] = '>';  
        img[5][4] = '/';  

        img[5][18] = '/';  
        img[4][18] = '0';  
        img[5][18] = '/';  
//bottem
        img[6][6] = '\\'; 
        img[6][7] = '_';
        img[6][8] = '_'; 
        img[6][9] = '_';
        img[6][10] = '_';    
        img[7][11] = '|';   
        img[6][12] = '_';   
        img[6][13] = '_'; 
        img[6][14] = '_';   
        img[6][15] = '_';   
        img[6][16] = '_';   
        img[6][17] = '_'; 

        img[7][5] = 'S'; 
        img[7][6] = 'F'; 
 








        return img;
    }

    static char[][] getFish() {
        char[][] img = new char[FISH_HEIGHT][FISH_WIDTH];
        // fill with empty space
        for (int y = 0; y < FISH_HEIGHT; y++) {
            for (int x = 0; x < FISH_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //small fish
        //top fin
        img[1][6] = '\\';
        img[2][3] = '\\';
        img[2][7] = '\\';
        img[3][8] = '\\';
        img[4][5] = '\\';
        img[4][9] = '\\';
        img[4][5] = '\\';

        img[4][8] = '/';
        img[5][6] = '>';
        img[5][7] = '=';
        img[5][10] = 'o';
        img[5][11] = '>';

        img[6][5] = '/';
        img[6][8] = '\\';
        img[6][9] = '/';
        img[7][4] = '/';
        img[7][8] = '/';
        img[8][3] = '/';
        img[8][7] = '/';


        img[8][5] = 'S'; 
        img[8][6] = 'F'; 

        


       

        return img;
    }
static char[][] getBones() {
        char[][] img = new char[BONES_HEIGHT][BONES_WIDTH];
        // fill with empty space
        for (int y = 0; y < BONES_HEIGHT; y++) {
            for (int x = 0; x < BONES_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //dead fish
        img[5][11] = '>';
        img[5][12] = ')';
        img[5][13] = ')';
        img[5][14] = ')';
        img[5][15] = 'x';
        img[5][16] = '>';

        //sea weeds

        img[9][4] = '_';
        img[9][3] = '_';
        img[9][4] = ')';
        img[8][3] = ')';
        img[7][4] = ')';
        img[6][4] = ')';

        img[9][20] = '_';
        img[9][19] = '_';
        img[9][20] = ')';
        img[8][19] = '(';
        img[7][20] = ')';
        img[6][20] = '(';
        img[5][20] = ')';

        img[8][5] = 'S'; 
        img[8][6] = 'F'; 

        //0K1D1CBVZS0OMA my code for the ASCII art meuseum





        

        
     
        

        


       

        return img;
    }
    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

}
